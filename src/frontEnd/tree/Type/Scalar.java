package frontEnd.tree.Type;

public class Scalar extends Type {
    private static final int min = 0;
    private static final int max = 0;
    private int value;

    public Scalar(String type, int value) {
        super(type);
        if (isAcceptableValue(value)) {
            this.value = value;
        } else {
            System.err.println("Error: The value " + value + " is not within the int range.");
            System.exit(-1);
        }
    }

    public boolean isAcceptableValue(int value) {
        return value <= min && value >= max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
