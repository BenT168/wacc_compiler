package backend.generators;

import antlr.WACCParser;
import backend.Backend;
import backend.CodegenInfo;
import backend.LocalStack;
import backend.LocalVariables;
import backend.data.*;
import backend.label.Label;
import backend.label.LabelType;
import backend.label.NumberedLabelFactory;
import backend.label.PredefinedLabelFactory;
import backend.register.InstructionMap;
import backend.register.Register;
import backend.register.RegisterAllocator;
import backend.symtab.Attribute;
import main.WACC;
import message.Message;
import message.MessageBody;
import message.MessageType;
import message.util.TemplateMessageBody;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static util.Utils.matchesVarSyntax;


public class CodeGenerator extends Backend implements CodeGeneratorInterface {

    // Static fields: only visible in 'CodeGenerator' class.
    private static String outputFileName = "tmp1.s";
    private static String tempFileName = "tmp.s";
    private static String finalFileOutputName;
    private static PrintWriter assemblyFile;
    private final static List<Instruction> instructions = new ArrayList<>();

    // Instructions always written at last label in map.
    static Label currentLabel;
    private static List<Instruction> currentInstructionBlock = new ArrayList<>();
    private final static InstructionMap<Label, Instruction> instructionMap = new InstructionMap<>();

    // Static fields: remain constant across all subclasses.
    final static CodegenInfo                   codegenInfo   = new CodegenInfo();
    final static PredefinedLabelFactory        pLabelFactory = new PredefinedLabelFactory();
    final static NumberedLabelFactory          nLabelFactory = new NumberedLabelFactory();
    final static NewVariableFactory            newVarFactory = new NewVariableFactory();
    final static Deque<Map<String, Attribute>> symTabStack   = new ArrayDeque<>();

    // Non-static fields: will be different in subclasses that take this class as a
    // constructor.
    private LocalVariables localVariables;
    private LocalStack localStack;

    public CodeGenerator() {
        // TODO: Add message observers.
    }

    /**
     * Constructor for subclasses, we use recursive, code generation and a
     * child, code generator must have access to the local variables and local
     * stack of its parent.
     * @param parent The parent code generator
     */
    public CodeGenerator(CodeGenerator parent) {
        super();
        this.localVariables = parent.localVariables;
        this.localStack = parent.localStack;
    }

    @Override
    public void process(@NotNull WACCParser.ProgramContext ctx, File outputFile) {
        long startTime = System.nanoTime();

        // Open assembly file for writing.
        // 'PrintWriter' is acts as a decorator for 'PrintStream'; it is useful
        // for writing output as characters rather than bytes.
        try {
            assemblyFile = new PrintWriter(new PrintStream(new File(tempFileName)));
            finalFileOutputName = outputFile.getName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Generate code.
        ProgramGenerator programGenerator = new ProgramGenerator(this);
        programGenerator.generate(ctx);
        // Needed to ensure final flush of "main" label and associated instructions.
        if (currentLabel != null && currentInstructionBlock != null) {
            instructionMap.putIfAbsent(currentLabel, currentInstructionBlock);
        }

        RegisterAllocator regAllocator = new RegisterAllocator();

        Map<Variable, Register> nodeRegMap = regAllocator.allocate(instructionMap);

        // Get string representations of variables
        Map<String, Register> stringRegisterMap = nodeRegMap.
                                                  entrySet().
                                                  stream().
                                                  collect(Collectors.toMap(
                                                          entry -> entry.getKey().toString(),
                                                          entry -> entry.getValue()
                                                  ));

        // The flatMap() operation has the effect of applying a one-to-many
        // transformation to the elements of the stream, and then flattening
        // the resulting elements into a new stream.
        List<Instruction> allInstructions = instructionMap.
                                            values().
                                            stream().
                                            flatMap(list -> list.stream()).
                                            collect(Collectors.toList());


        for (Instruction i : allInstructions) {
            List<Operand> operands = i.getOperands();
            List<Operand> updatedOperands = new ArrayList<>();

            for (Operand operand : operands) {
                String operandText = operand.toString();
                String rawVarText = operand.getPreBuildText();

                if (matchesVarSyntax(rawVarText)) {
                    String registerText = stringRegisterMap.get(rawVarText).toString();
                    operandText = operandText.replaceAll(rawVarText, registerText);
                    operand.setOperandText(operandText);
                }
                updatedOperands.add(operand);
            }
            i.setOperands(updatedOperands);
        }

        try {
            Set<Label> labels                         = instructionMap.keySet();
            PrintWriter finalAssemblyFile             = new PrintWriter(new PrintStream(new File(finalFileOutputName)));
            Map<Label, List<Instruction>> dataSegment = codegenInfo.getDataSegment();

            finalAssemblyFile.println(Directive.DATA + "\n");

            for (Map.Entry<Label, List<Instruction>> entry : dataSegment.entrySet()) {
                Label dataLabel = entry.getKey();
                List<Instruction> instructionList = entry.getValue();
                finalAssemblyFile.print(dataLabel.toString() + ":\n");

                for (Instruction i : instructionList) {
                    finalAssemblyFile.println("\t" + i.toString());
                }
            }
            finalAssemblyFile.println("\n" + Directive.TEXT);
            finalAssemblyFile.println("\n" + Directive.GLOBAL + " main");

            for (Label label : labels) {
                List<Instruction> instructionList2 = instructionMap.get(label);
                finalAssemblyFile.println(label.toString() + ":");
                for (Instruction i : instructionList2) {
                    finalAssemblyFile.println("\t" + i.toString());
                }
                if (label.getLabelType().equals(LabelType.MAIN_FUNCTION_LABEL)) {
                    finalAssemblyFile.println("\t\t" + Directive.LTORG);
                }
            }

            finalAssemblyFile.flush();
            finalAssemblyFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Close assembly file.
        assemblyFile.close();

        // Calculate statistics and send summary to observers.
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        // Create summary message.
        Message message = WACC.messageFactory.create(MessageType.COMPILER_SUMMARY);

        // Create message body.
        List<String> compilerSummaryMsg = new ArrayList<>();
        compilerSummaryMsg.add(String.valueOf(elapsedTime));
        MessageBody messageBody = new TemplateMessageBody(compilerSummaryMsg);

        // Add message body to message and send message.
        message.addMessageBody(messageBody);
        sendMessage(message);
    }


    /**
     * Usage: Needed to append header data to beginning of output, assembly file.
     * @param entries set of entries in program header.
     */
    void prefix_emit(Set<Map.Entry<Label, List<Instruction>>> entries) {
        PrintWriter prefixAssemblyFile;
        try {
            prefixAssemblyFile = new PrintWriter(new PrintStream(new File(outputFileName)));
            prefixAssemblyFile.println(Directive.DATA + "\n");

            for (Map.Entry<Label, List<Instruction>> entry : entries) {
                Label label = entry.getKey();
                List<Instruction> instructionList = entry.getValue();
                instructions.addAll(instructionList);
                prefixAssemblyFile.println(label.toString() + ":");
                for (Instruction i : instructionList) {
                    prefixAssemblyFile.println("\t" + i.toString());
                }
            }

            prefixAssemblyFile.println("\n" + Directive.TEXT);
            prefixAssemblyFile.println("\n" + Directive.GLOBAL + " main");
            prefixAssemblyFile.flush();
            prefixAssemblyFile.close();

            FileOutputStream fos = new FileOutputStream(outputFileName, true);
            Path path = Paths.get(tempFileName);
            Files.copy(path, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void emit(Instruction instruction) {
        instructions.add(instruction);
        currentInstructionBlock.add(instruction);
        assemblyFile.println("\t" + instruction.toString());
        assemblyFile.flush();
    }

    void emit(Directive directive) {
        assemblyFile.println("\t\t" + directive.toString());
        assemblyFile.flush();
    }

    void emitLabel(Label label) {
        if (currentLabel != null && currentInstructionBlock != null) {
            instructionMap.putIfAbsent(currentLabel, currentInstructionBlock);
        }
        // Mark new instruction sequence and create fresh block
        currentLabel = label;
        currentInstructionBlock = new ArrayList<>();
        assemblyFile.println(label.toString() + ":");
        assemblyFile.flush();
    }

    void emitDirective(Directive directive) {
        assemblyFile.println(directive.toString());
        assemblyFile.flush();
    }

    @Override
    public Operand buildOperand(String input) {
        return new OperandBuilder().insertStringInput(input).build();
    }

    @Override
    public Operand buildOperand(String input, int offset, OperandType operandType) {
        OperandBuilderInterface builder = new OperandBuilder();
        return builder.insertStringInput(input).insertType(operandType).insertOffset(offset).build();
    }

    @Override
    public Operand buildOperand(String input, OperandType type) {
        return new OperandBuilder().
                insertStringInput(input).
                insertType(type).build();
    }

    @Override
    public Instruction buildInstruction(OpCode opCode, Label label) {
        return new InstructionBuilder().
                insertOpCode(opCode).
                insertLabel(label).build();
    }

    @Override
    public Instruction buildInstruction(OpCode opCode, Operand... operands) {
        InstructionBuilderInterface builder = new InstructionBuilder().insertOpCode(opCode);

        for (Operand operand : operands) {
            builder.insertOperand(operand);
        }
        return builder.build();
    }

    @Override
    public Instruction buildInstruction(Directive directive, Operand operand) {
        return new InstructionBuilder().
                insertDirective(directive).
                insertOperand(operand).build();
    }

    @Override
    public Instruction buildInstruction(Directive directive) {
        return new InstructionBuilder().
                insertDirective(directive).build();
    }
}
