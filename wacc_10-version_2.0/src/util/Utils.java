package util;

import backend.data.NewVariableFactory;

/**
 * Created by MarkAduol on 06-Dec-16.
 */
public class Utils {
    public static boolean matchesVarSyntax(String s) {
        return s.startsWith(NewVariableFactory.getVarPrefix());
    }
}
