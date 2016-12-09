package backend;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Optimiser {

    private String code;
    private Map<String, String> instructions = new LinkedHashMap<String, String>();

    /* takes as argument unoptimised generated assembly and returns its optimised version */
    public String optimise(String code) {
        this.code = code;
        analyse();
        removeRedundant();
        updateCode();
        return this.code;
    }

    /* analyses assembly code to separate and store instructions into hashmap */
    private void analyse() {
        String delims = "\n";
        StringTokenizer tokens = new StringTokenizer(code, delims);
        delims = "";
        while (tokens.hasMoreElements()) {
            StringTokenizer pieces = new StringTokenizer(tokens.nextToken(), delims);
            String instr = pieces.nextToken();
            String ops = " ";
            while (pieces.hasMoreElements()) {
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
                if (it.hasNext()) current = it.next();
            }
    }

    /* checks for and removes subsequent identical instructions */
    private boolean removeDuplicates(Map.Entry<String, String> current, Map.Entry<String, String> next) {
        if (next.getKey().equals(current.getKey())) {
            if (next.getValue().equals(current.getValue())) {
                instructions.remove(current);
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
                instructions.remove(current);
                return true;
            }
        }
        return false;
    }

    private void updateCode() {
        Iterator<Map.Entry<String, String>> it = instructions.entrySet().iterator();
        code = "";
        while (it.hasNext()) {
            Map.Entry<String, String> current = it.next();
            code = code.concat(current.getKey());
            code = code.concat(current.getValue());
            code = code.concat("\n");
        }
    }

    public static final Optimiser optimiser = new Optimiser() {
    };

}
