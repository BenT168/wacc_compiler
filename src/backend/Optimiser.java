package backend;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Optimiser {

    private String code;
    private Map instructions = new LinkedHashMap<String, String>();

    /* takes as argument unoptimised generated assembly and returns its optimised version */
    public String optimise(String code) {
        this.code = code;
        analyse();
        removeRedundant();
        return code;
    }

    /* analyses assembly code to separate and store instructions into hashmap */
    private void analyse() {
        String delims = "\n";
        StringTokenizer tokens = new StringTokenizer(code, delims);
        while (tokens.hasMoreElements()) {
            delims = " ";
            StringTokenizer pieces = new StringTokenizer(code, delims);
            String instr = pieces.nextToken();
            String ops = "";
            int numberOfOps = pieces.countTokens();
            while (numberOfOps > 0) {
                ops = ops.concat(pieces.nextToken());
            }
            instructions.put(instr, ops);
        }
    }

    /* cleans assembly code from redundancies and updates code local variable */
    private void removeRedundant() {
        Iterator<Map.Entry<String, String>> it = instructions.entrySet().iterator();
        Map.Entry<String, String> current = it.next();
            while (it.hasNext()) {
                Map.Entry<String, String> next = it.next();
                boolean done = removeDuplicates(current, next);
                if (!done) done = removeSTRandLDR(current, next);
                current = it.next();
            }
    }

    /* checks for and removes subsequent identical instructions */
    private boolean removeDuplicates(Map.Entry<String, String> current, Map.Entry<String, String> next) {
        if (next.getKey().equals(current.getKey())) {
            if (next.getValue().equals(current.getValue())) {
                instructions.remove(next);
                return true;
            }
        }
        return false;
    }

    /* checks for and removes STR and LDR instructions performed from the same source to the same destination */
    private boolean removeSTRandLDR(Map.Entry<String, String> current, Map.Entry<String, String> next) {
        if (current.getKey().equals("STR") && next.getKey().equals("LDR")
                || next.getKey().equals("STR") && current.getKey().equals("LDR")) {
            if (current.getValue().equals(next.getValue())) {
                instructions.remove(next);
                return true;
            }
        }
        return false;
    }

    public static final Optimiser optimiser = new Optimiser() {
    };

}