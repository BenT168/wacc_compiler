package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Optimiser {

    private String code;
    private Map instructions = new HashMap<String, String>;

    public void optimise(String code) {
        this.code = code;
        analyse();
        removeRedundant();
    }

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

    private void removeRedundant() {

    }

    public static final Optimiser optimiser = new Optimiser() {
    };

}
