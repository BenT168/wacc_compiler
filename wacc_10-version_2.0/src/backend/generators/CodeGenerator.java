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
import backend.register.ControlFlowGraph;
import backend.register.InstructionMap;
import backend.register.Register;
import backend.register.RegisterAllocator;
import main.WACC;
import message.Message;
import message.MessageType;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static util.Utils.matchesVarSyntax;


public class CodeGenerator extends Backend implements CodeGeneratorInterface {

    // Static fields: only visible in 'CodeGenerator' class.
    private static PrintWriter assemblyFile;
    private final static List<Instruction> instructions = new ArrayList<>();
    // Instructions always written at last label in map.
    private static Label currentLabel;
    private static List<Instruction> currentInstructionBlock;
    private final static InstructionMap<Label, Instruction> instructionMap = new InstructionMap<>();
    // Static fields: remain constant across all subclasses.
    final static CodegenInfo codegenInfo = new CodegenInfo();
    final static PredefinedLabelFactory pLabelFactory = new PredefinedLabelFactory();
    final static NumberedLabelFactory nLabelFactory = new NumberedLabelFactory();
    final static NewVariableFactory newVarFactory = new NewVariableFactory();
    final static Deque<Map<String, Variable>> symTabStack = new ArrayDeque<>();
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
    public void process(@NotNull WACCParser.ProgramContext ctx) {
        long startTime = System.nanoTime();

        // Open assembly file for writing.
        // 'PrintWriter' is acts as a decorator for 'PrintStream'; it is useful
        // for writing output as characters rather than bytes.
        try {
            assemblyFile = new PrintWriter(new PrintStream(new File("tmp.s")));
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

        // TEMPORARY TESTING CODE
        ControlFlowGraph cfg = new ControlFlowGraph(instructionMap);

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

        List<Map<Integer, Set<Variable>>> livenessSets = regAllocator.getLivenessSets();

        for (Integer node : cfg.getGraph().keySet()) {
            System.out.println("\nNode: " + String.valueOf(node));
            for (Integer i : cfg.getGraph().get(node)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Successor ").append(String.valueOf(i)).
                        append(": ").
                        append(cfg.getInstruction(i).toString());
                for (Map m : livenessSets) {
                    sb.append(m.get(i).toString());
                }
                sb.append(" Gen Set: ").append(cfg.getGenSet(i)).append(" Kill Set: ").append(cfg.getKillSet(i));
                System.out.println(sb.toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Variable, Register> allocation : nodeRegMap.entrySet()) {
            sb.append(" Variable: ").append(allocation.getKey().toString());
            sb.append(" Register: ").append(allocation.getValue().toString());
            System.out.println(sb.toString());
        }
        // END

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
            for (Operand oper : operands) {
                String operandText = oper.toString();
                if (matchesVarSyntax(operandText)) {
                    String registerText = stringRegisterMap.get(operandText).toString();
                    oper.setOperandText(registerText);
                }
                updatedOperands.add(oper);
            }
            i.setOperands(updatedOperands);
        }
        // Testing
        for (Instruction i : allInstructions) {
            System.out.println(i.toString());
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
            prefixAssemblyFile = new PrintWriter(new PrintStream(new File("tmp1.s")));
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

            FileOutputStream fos = new FileOutputStream("tmp1.s", true);
            Path path = Paths.get("tmp.s");
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
}
