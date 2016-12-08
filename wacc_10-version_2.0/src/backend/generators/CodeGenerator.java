package backend.generators;

import antlr.WACCParser;
import backend.Backend;
import backend.CodegenInfo;
import backend.LocalStack;
import backend.LocalVariables;
import backend.data.*;
import backend.label.Label;
import backend.label.NumberedLabelFactory;
import backend.label.PredefinedLabelFactory;
import backend.register.InstructionMap;
import backend.register.Register;
import backend.register.RegisterAllocator;
import backend.symtab.Attribute;
import main.WACC;
import message.Message;
import message.MessageType;
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
    private static String outputFileName;
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
            assemblyFile = new PrintWriter(new PrintStream(outputFile));
            outputFileName = outputFile.getName();
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
        // Testing
        Set<Label> labels = instructionMap.keySet();
        for (Label l : labels) {
            List<Instruction> instructions = instructionMap.get(l);
            System.out.println(l.toString());
            for (Instruction i : instructions) {
                System.out.println("\t" + i.toString());
            }
        }
        // End

        // Close assembly file.
        assemblyFile.close();

        // Calculate statistics and send summary to observers.
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        Message message = WACC.messageFactory.create(MessageType.COMPILER_SUMMARY);
        // Stub to show usage
        // message.addMessageBody(MessageBody);
        sendMessage(message);
    }


    /**
     * Usage: Needed to append header data to beginning of output, assembly file.
     * @param entries set of entries in program header.
     */
    void prefix_emit(Set<Map.Entry<Label, List<Instruction>>> entries) {
        PrintWriter prefixAssemblyFile;
        try {
            prefixAssemblyFile = new PrintWriter(new PrintStream(new File("tmp.s")));
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

            FileOutputStream fos = new FileOutputStream("tmp.s", true);
            Path path = Paths.get(outputFileName);
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
    public Operand buildOperand(String input, int offset, boolean isOffset) {
        OperandBuilderInterface builder = new OperandBuilder().insertStringInput(input);
        Operand operand;
        if (isOffset) {
            operand = builder.
                    insertType(OperandType.MEM_ADDR_WITH_OFFSET_OPERAND).
                    insertOffset(offset).build();
        } else {
            operand = builder.
                    insertType(OperandType.SHIFT_OPERAND).
                    insertOffset(offset).build();
        }
        return operand;
    }

    @Override
    public Operand buildOperand(String input, OperandType type) {
        return new OperandBuilder().
                insertStringInput(input).
                insertType(type).build();
    }

    @Override
    public Operand buildOperand(String input, OperandType type, int callOffset) {
        if (!type.equals(OperandType.CALL_OPERAND)) {
            System.err.println("Type must be " + OperandType.CALL_OPERAND.toString());
        }
        return new OperandBuilder().
                insertStringInput(input).
                insertOffset(callOffset).
                insertType(OperandType.CALL_OPERAND).build();
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
