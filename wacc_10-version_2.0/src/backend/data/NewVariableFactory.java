package backend.data;

public class NewVariableFactory implements NewVariableFactoryInterface {
    // Keeps track of number of variables created.
    private int varCount;
    private static final String PREFIX = "t";

    public NewVariableFactory() {
        this.varCount = 0;
    }

    @Override
    public Variable createNewVar() {
        String varString = PREFIX + String.valueOf(varCount);
        varCount++;
        return new Variable(varString);
    }

    public static String getVarPrefix() {
        return PREFIX;
    }
}
