package util;

import backend.data.NewVariableFactory;

/**
 * Created by MarkAduol on 06-Dec-16.
 */
public class Utils {
    public static boolean matchesVarSyntax(String s) {
        return s.startsWith(NewVariableFactory.getVarPrefix());
    }

    public static String getPureOperandString(String operandText) {
        String pureOperandString = operandText;
        if (operandText.length() > 3 && matchesVarSyntax(operandText.substring(1,3))) {
            pureOperandString = operandText.substring(1,3);
        }
        return pureOperandString;
    }
}
