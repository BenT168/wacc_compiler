package backend.register;

import backend.data.Instruction;
import backend.data.OpCode;
import backend.data.Variable;
import backend.label.Label;
import util.MapUtil;

import java.util.*;
import java.util.stream.Collectors;

public class RegisterAllocator implements Allocator {
    private InstructionMap<Label, Instruction> groupedInstructions;
    private InstructionMap<Label, Instruction> allocatedInstructions;
    private List<Map<Integer, Set<Variable>>> livenessSets;
    private static final int AVAILABLE_REGISTERS = 12;

    private Map<Integer, Set<Variable>> liveInSets;
    private Map<Integer, Set<Variable>> liveOutSets;

    public RegisterAllocator() {
        this.groupedInstructions = new InstructionMap<>();
        this.allocatedInstructions = new InstructionMap<>();
        this.livenessSets = new ArrayList<>();
        this.liveInSets = new HashMap<>();
        this.liveOutSets = new HashMap<>();
    }

    public Map<Variable, Register> allocate(InstructionMap<Label, Instruction> groupedInstructions) {
        this.groupedInstructions = groupedInstructions;
        ControlFlowGraph cfg = new ControlFlowGraph(groupedInstructions);
        Map<Integer, Set<Variable>> killSets = new HashMap<>();
        for (int i = 0; i < cfg.getInstructions().size(); i++) {
            killSets.put(i, cfg.getKillSet(i));
        }
        System.out.println("Control Flow Graph: " + cfg.toString());
        this.livenessSets = getLivenessSets(cfg);
        Map<Integer, Set<Variable>> liveOutSets = livenessSets.get(1);
        InterferenceGraph interferenceGraph = new InterferenceGraph(killSets, liveOutSets);
        System.out.println("Interference Graph: " + interferenceGraph.toString());

        // Stack of entries:
        // each entry contains a node and its list of adjacent nodes.
        Deque<Map.Entry<Variable, Set<Variable>>> nodeStack = new ArrayDeque<>();
        // Sort in ascending order of node degree.
        MapUtil.sortBySetSize(interferenceGraph);
        for (Map.Entry<Variable, Set<Variable>> node : interferenceGraph.entrySet()) {
            nodeStack.addFirst(node);
            //System.out.println("Added to nodeStack: " + node.toString());
            interferenceGraph.remove(node);
        }

        // Create map of nodes to registers
        Map<Variable, Register> nodeRegMap = new HashMap<>();

        // Pop all nodes from stack and give each node a colour that is different
        // from all its connected nodes.
        for (int i = nodeStack.size()-1; i >= 0 ; i--) {
            Map.Entry<Variable, Set<Variable>> node = nodeStack.removeFirst();
            // MID: All adjacent nodes are coloured at this point.
            EnumSet<Register> availableRegisters = EnumSet.allOf(Register.class);
            for (Variable adjNode : node.getValue()) {
                //System.out.println("Removed from " + node.toString() + ": " + adjNode.toString());
                availableRegisters.remove(nodeRegMap.get(adjNode));
            }
            if (availableRegisters.isEmpty()) {
                // Mark for spilling
            } else {
                // Allocate register
                Register allocatedReg = availableRegisters.stream().findFirst().get();
                nodeRegMap.put(node.getKey(), allocatedReg);
            }
            //System.out.println("Node-Register Map: " + nodeRegMap.toString());
        }
        return nodeRegMap;
    }

    /**
     * @param cfg Control Flow Graph: graph is represented as map from integer to list of integers.
     *            Each integer key denotes a unique instruction index.
     * @return A pair of maps: first map is 'Liveness In' set, second map is 'Liveness Out' set,
     *         for every instruction index.
     */
    private List<Map<Integer, Set<Variable>>> getLivenessSets(ControlFlowGraph cfg) {

        Map<Integer, List<Integer>> cfgGraph = cfg.getGraph();

        for (Integer node : cfgGraph.keySet()) {
            Set<Variable> emptyVarSet = Collections.emptySet();
            liveInSets.put(node, emptyVarSet);
            liveOutSets.put(node, emptyVarSet);
        }
        // To store intermediate results
        Map<Integer, Set<Variable>> currentInSets = new HashMap<>();
        Map<Integer, Set<Variable>> currentOutSets = new HashMap<>();

        do {
            for (Integer node : cfgGraph.keySet()) {
                currentInSets = liveInSets;
                currentOutSets = liveOutSets;

                Set<Variable> newInSet = getLiveInSet(node, cfg);
                Set<Variable> newOutSet = getLiveOutSet(node, cfg);
                liveInSets.put(node, newInSet);
                liveOutSets.put(node, newOutSet);
            }
        } while (!(currentInSets.equals(liveInSets) && currentOutSets.equals(liveOutSets)));
        List<Map<Integer, Set<Variable>>> results = new ArrayList<>();
        results.add(liveInSets);
        results.add(liveOutSets);
        return results;
    }

    /**
     * Mutually recursive function with {@link RegisterAllocator#getLiveOutSet(int, ControlFlowGraph)}
     * @param node
     * @param cfg
     * @return
     */
    private Set<Variable> getLiveInSet(int node, ControlFlowGraph cfg) {
        Set<Variable> liveInSet = new HashSet<>();

        liveInSet.addAll(liveOutSets.get(node));
        for (Variable v : cfg.getKillSet(node)) {
            liveInSet.remove(v);
        }
        liveInSet.addAll(cfg.getGenSet(node));
        return liveInSet;
    }

    /**
     * Mutually recursive function with {@link RegisterAllocator#getLiveInSet(int, ControlFlowGraph)}
     * @param node
     * @param cfg
     * @return
     */
    private Set<Variable> getLiveOutSet(int node, ControlFlowGraph cfg) {
        Set liveOutSet = new HashSet();
        for (Integer successor : cfg.getGraph().get(node)) {
            Set<Variable> liveInSet = getLiveInSet(successor, cfg);
            liveOutSet.addAll(liveInSet);
        }
        return liveOutSet;
    }

    public List<Map<Integer, Set<Variable>>> getLivenessSets() {
        return livenessSets;
    }
}
