package backend.register;

import backend.data.Instruction;
import backend.data.OpCode;
import backend.data.Variable;
import backend.label.Label;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by MarkAduol on 04-Dec-16.
 */
public class ControlFlowGraph {
    private List<Instruction> instructions;
    private List<IndexedInstruction> indexedInstructions;
    // All instructions indexed in order.
    private InstructionMap<Label, IndexedInstruction> indexedInstructionMap;
    // Key: Instruction node represented as instruction index.
    // Value: Reachable instruction nodes from key node.
    private Map<Integer, List<Integer>> graph;

    public ControlFlowGraph(InstructionMap<Label, Instruction> instructionMap) {
        this.indexedInstructionMap = new InstructionMap<>();
        this.instructions = new ArrayList<>();
        this.indexedInstructions = new ArrayList<>();
        this.graph = new HashMap<>();

        // We use 3 operations for clarity - rather than one complicated
        // for-loop.

        this.instructions = instructionMap.
                            values().
                            stream().
                            flatMap(list -> list.stream()).
                            collect(Collectors.toList());


        InstructionIndexer indexer = new InstructionIndexer();
        this.indexedInstructions = instructionMap.
                                   values().
                                   stream().
                                   flatMap(list -> list.stream().
                                           map(instr -> indexer.createIndexedInstuction(instr))).
                                   collect(Collectors.toList());

        for (Label l : instructionMap.keySet()) {
            List<Instruction> instrs = instructionMap.get(l);
            List<IndexedInstruction> indexed_instrs = new ArrayList<>();
            for (Instruction i : instrs) {
                IndexedInstruction indexed_i = indexer.createIndexedInstuction(i);
                indexed_instrs.add(indexed_i);
            }
            this.indexedInstructionMap.put(l, indexed_instrs);
        }

        // Instructions will be indexed from first in list to last.
        // Initialise graph nodes.
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instr = instructions.get(i);
            initialiseGraphNode(i, instr);
        }
    }

    public Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    private void initialiseGraphNode(Integer index, Instruction instr) {
        List<Integer> outEdges = new ArrayList<>();
        if (isBranchNode(instr)) {
            if (!instr.getOpCode().equals(OpCode.B) && index < instructions.size()-1) {
                // Conditional branch
                outEdges.add(index+1);
            }
            Label branchLabel = instr.getLabeL();
            assert branchLabel != null;
            Integer branchIndex = indexedInstructionMap.get(branchLabel).get(0).getIndex();
            outEdges.add(branchIndex);
        } else if (!isPopNode(instr) && index < instructions.size()-1) {
            // Assuming only pop instruction is POP {pc} at end of function.
            outEdges.add(index+1);
        }
        graph.put(index, outEdges);
    }

    private boolean isBranchNode(Instruction instruction) {
        // Removed: OpCode.BL
        EnumSet<OpCode> enumSet = EnumSet.of(OpCode.B, OpCode.BEQ, OpCode.BG, OpCode.BGE, OpCode.BLEQ);
        for (OpCode opCode : enumSet) {
            if (instruction.getOpCode().equals(opCode))
                return true;
        }
        return false;
    }

    private boolean isPopNode(Instruction instruction) {
        return instruction.getOpCode().equals(OpCode.POP);
    }

    public Set<Variable> getKillSet(Integer index) {
        return instructions.get(index).getKillSet();
    }

    public Set<Variable> getGenSet(Integer index) {
        return instructions.get(index).getGenSet();
    }

    public Instruction getInstruction(Integer index) {
        return instructions.get(index);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString();
    }
}
