package backend.register;

import backend.data.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by MarkAduol on 04-Dec-16.
 */
public class InterferenceGraph extends HashMap<Variable, Set<Variable>> {

    private Map<Integer, Set<Variable>> killSets;
    private Map<Integer, Set<Variable>> liveOutSets;

    public InterferenceGraph(Map<Integer, Set<Variable>> killSets, Map<Integer, Set<Variable>> liveOutSets) {
        super();
        this.killSets = killSets;
        this.liveOutSets = liveOutSets;
        buildGraph();
    }

    private void buildGraph() {
        assert killSets.size() == liveOutSets.size();
        for (int i = 0; i < killSets.size(); i++) {
            Set<Variable> killSet = killSets.get(i);
            // Only ever one destination operand.
            assert killSet.size() == 1;
            Set<Variable> liveOutSet = liveOutSets.get(i);
            Set<Variable> interferenceSet = new HashSet<>();
            for (Variable v : liveOutSet) {
                if (!killSet.contains(v)) {
                    interferenceSet.add(v);
                }
            }
            if (killSet.size() >= 1) {
                Variable lhs = killSet.stream().findFirst().get();
                this.put(lhs, interferenceSet);
            }
        }
    }

}
