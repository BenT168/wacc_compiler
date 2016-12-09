package backend.register;

import backend.data.Instruction;
import backend.data.Variable;
import backend.label.Label;
import org.antlr.v4.runtime.misc.Array2DHashSet;
import util.MapUtil;

import java.util.*;

public class RegisterAllocator implements Allocator {

    private static List<Register> reservedRegs = Arrays.asList(Register.PC_REG, Register.LR_REG, Register.SP_REG, Register.R0_REG, Register.R1_REG);
    private List<Map<Integer, Set<Variable>>> livenessSets;
    private Map<Integer, Set<Variable>> liveInSets;
    private Map<Integer, Set<Variable>> liveOutSets;

    // The following variables are needed to keep track of 'liveOutSet'
    // accumulation in when calculating the 'liveOutSet' for every instruction
    // index. Without these top-level variables, we would unable to perform
    // proper register allocation for loops (possibly receiving a stack overflow
    // error during the method recursion).
    private List <Boolean> outSetCalculatedNodes;
    private Set<Variable> accumulatedOutSet;

    public RegisterAllocator() {
        this.livenessSets = new ArrayList<>();
        this.liveInSets = new HashMap<>();
        this.liveOutSets = new HashMap<>();
        this.outSetCalculatedNodes = new ArrayList<>();
        this.accumulatedOutSet = new HashSet<>();
    }

    public Map<Variable, Register> allocate(InstructionMap<Label, Instruction> groupedInstructions) {
        ControlFlowGraph cfg = new ControlFlowGraph(groupedInstructions);
        Map<Integer, Set<Variable>> killSets = new LinkedHashMap<>();

        for (int i = 0; i < cfg.getInstructions().size(); i++) {
            killSets.put(i, cfg.getKillSet(i));
        }

        this.livenessSets                       = buildLivenessSets(cfg);
        Map<Integer, Set<Variable>> liveOutSets = livenessSets.get(1);  // Index 1 for live out set; index 0 for live in set.
        InterferenceGraph interferenceGraph     = new InterferenceGraph(killSets, liveOutSets);

        // Stack of entries:
        // each entry contains a node and its list of adjacent nodes.
        Deque<Map.Entry<Variable, Set<Variable>>> nodeStack = new ArrayDeque<>();
        // Sort in ascending order of node degree.
        Map<Variable, Set<Variable>> sortedIG = MapUtil.sortBySetSize(interferenceGraph);
        for (Map.Entry<Variable, Set<Variable>> node : sortedIG.entrySet()) {
            nodeStack.addFirst(node);
            //System.out.println("Added to nodeStack: " + node.toString());
            sortedIG.remove(node);
        }

        // Create map of nodes to registers
        Map<Variable, Register> nodeRegMap = new HashMap<>();

        // Pop all nodes from stack and give each node a colour that is different
        // from all its connected nodes.
        for (int i = nodeStack.size()-1; i >= 0 ; i--) {

            List<Register> availableRegisters = new ArrayList<>();
            for (Register reg : EnumSet.allOf(Register.class)) {
                if (!reservedRegs.contains(reg)) {
                    availableRegisters.add(reg);
                }
            }
            Map.Entry<Variable, Set<Variable>> node = nodeStack.removeFirst();

            for (Variable adjNode : node.getValue()) {
                Register allocatedReg;
                try {
                    allocatedReg = availableRegisters.get(0);
                } catch (IndexOutOfBoundsException e) {
                    // TODO:
                    allocatedReg = Register.SPILLED_REG;
                }
                nodeRegMap.put(adjNode, allocatedReg);
                availableRegisters.remove(allocatedReg);
            }

            // MID: All adjacent nodes are coloured at this point.
            if (availableRegisters.isEmpty()) {
                // Mark for spilling
                //System.out.println("Spilled");
                nodeRegMap.put(node.getKey(), Register.SPILLED_REG);
            } else {
                // Allocate register
                Register allocatedReg = availableRegisters.get(0);
                nodeRegMap.put(node.getKey(), allocatedReg);
            }
        }
        return nodeRegMap;
    }

    /**
     * @param cfg Control Flow Graph: graph is represented as map from integer to list of integers.
     *            Each integer key denotes a unique instruction index.
     * @return A pair of maps: first map is 'Liveness In' set, second map is 'Liveness Out' set,
     *         for every instruction index.
     */
    private List<Map<Integer, Set<Variable>>> buildLivenessSets(ControlFlowGraph cfg) {

        Map<Integer, List<Integer>> cfgGraph = cfg.getGraph();

        for (Integer node : cfgGraph.keySet()) {
            Set<Variable> emptyVarSet = Collections.emptySet();
            liveInSets.put(node, emptyVarSet);
            liveOutSets.put(node, emptyVarSet);
        }
        // To store intermediate results
        Map<Integer, Set<Variable>> currentInSets = new LinkedHashMap<>();
        Map<Integer, Set<Variable>> currentOutSets = new LinkedHashMap<>();

        // Sort keys in ascending order
        List<Integer> sortedNodes = new ArrayList<>(cfgGraph.keySet());
        Collections.sort(sortedNodes);

        do {
            // Reset the condition on nodes visited.
            outSetCalculatedNodes = resetNodes(sortedNodes.size());
            accumulatedOutSet = resetAccumulatedSet();
            for (int i = sortedNodes.size()-1; i >= 0; i--) {
                Integer node = sortedNodes.get(i);
                currentInSets.put(node, liveInSets.get(node));
                currentOutSets.put(node, liveOutSets.get(node));

                Set<Variable> newOutSet = getLiveOutSet(node, cfg);
                Set<Variable> newInSet = getLiveInSet(node, cfg, newOutSet, true);
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
     * Mutually recursive function with
     * {@link RegisterAllocator#getLiveOutSet(int, ControlFlowGraph)}.
     * The method returns the set of variables that are live at the start of instruction
     * 'node' - where instruction 'node' is the instuction indexed with the value 'node'.
     * @param node The index of the instruction in the list of all program instructions.
     * @param cfg Our control flow graph of instructions throughout the entire
     *            program. This is obtained by indexing all instructions, in
     *            order, via the use of our {@link InstructionIndexer} class.
     * @param newOutSet
     * @param outSetCalculated Indicates whether the live out set at index {@param node}
     *                         has already been calculated, and if so, whether parameter
     *                         {@param newOutSet} is this set.
     * @return The set of variables that are live at the start of the instruction 'node'.
     */
    private Set<Variable> getLiveInSet(int node, ControlFlowGraph cfg, Set<Variable> newOutSet, boolean outSetCalculated) {
        if (node == 0) {
            // The initial instruction has an empty 'Live In' set by definition.
            return Collections.emptySet();
        }
        Set<Variable> liveInSet = new HashSet<>();

        if (outSetCalculated) {
            liveInSet.addAll(newOutSet);
        } else {
            // We must calculate the 'live in' set.
            // We do not modify the input parameter in order to avoid side effects.
            Set<Variable> liveOutSet = getLiveOutSet(node, cfg);
            liveInSet.addAll(liveOutSet);
        }

        for (Variable v : cfg.getKillSet(node)) {
            liveInSet.remove(v);
        }
        liveInSet.addAll(cfg.getGenSet(node));

        return liveInSet;
    }

    /**
     * Mutually recursive function with
     * {@link RegisterAllocator#getLiveInSet(int, ControlFlowGraph, Set, boolean)}.
     * The method returns the set of variables that are live at the end of instruction
     * 'node' - where instruction 'node' is the instuction indexed with the value 'node'.
     * @param node The index of the instruction in the list of all program instructions.
     * @param cfg Our control flow graph of instructions throughout the entire
     *            program. This is obtained by indexing all instructions, in
     *            order, via the use of our {@link InstructionIndexer} class.
     * @return The set of variables that are live at the end of the instruction 'node'.
     */
    private Set<Variable> getLiveOutSet(int node, ControlFlowGraph cfg) {
        if (node == cfg.getGraph().size() - 1) {
            // The final instruction's 'Live Out' set is "effectively" empty,
            // since we are at the program end.
            return Collections.emptySet();
        }
        if (isOutSetCalculatedNode(node)) {
            return accumulatedOutSet;
        }
        outSetCalculatedNodes.add(node, true);
        Set<Variable> liveOutSet = new HashSet<>();
        // We need a way of accumulating the results from getLiveInSet in the for loop.
        for (Integer successor : cfg.getGraph().get(node)) {
            Set<Variable> liveInSet = getLiveInSet(successor, cfg, new HashSet<>(), isOutSetCalculatedNode(successor));
            liveOutSet.addAll(liveInSet);
            accumulatedOutSet.addAll(liveInSet);
        }
        return liveOutSet;
    }

    public List<Map<Integer, Set<Variable>>> getLivenessSets() {
        return livenessSets;
    }

    private boolean isOutSetCalculatedNode(int node) {
        return outSetCalculatedNodes.get(node);
    }

    private List<Boolean> resetNodes(int arraySize) {
        List<Boolean> result = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            result.add(false);
        }
        return result;
    }

    private Set<Variable> resetAccumulatedSet() {
        return new HashSet<>();
    }
}
