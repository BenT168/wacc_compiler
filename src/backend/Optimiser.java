package backend;

import java.util.*;

public class Optimiser {

    private String code;
    Map<String, String> instructionMap = new HashMap<>();


    /* takes as argument unoptimised generated assembly and returns its optimised version */
    public String optimise(String code) {
        this.code = code;
        analyse();
        removeRedundant();
        updateCode();
        return code;
    }

    /* analyses assembly code to separate and store instructions into hashmap */
    private void analyse() {
        List<String> instructions = new ArrayList<>();
        String[] lines = code.split("\n");

        for (String line : lines) {
            if (!(line.startsWith(".") || line.endsWith(":"))) {
                instructions.add(line);
            }
        }
        for (String instruction : instructions) {
            String[] splitInstr = instruction.split(" ");
            instructionMap.put(splitInstr[0], splitInstr[1]);
        }
    }

    /* cleans assembly code from redundancies and updates code local variable */
    private void removeRedundant() {
        Iterator<Map.Entry<String, String>> it = instructionMap.entrySet().iterator();
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
                instructionMap.remove(current);
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
                instructionMap.remove(current);
                return true;
            }
        }
        return false;
    }

    private void updateCode() {
        Iterator<Map.Entry<String, String>> it = instructionMap.entrySet().iterator();
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
