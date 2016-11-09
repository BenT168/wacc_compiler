package frontEnd.tree.Type;

public class Scalar extends Type {

    private int min = 0;
    private int max = 0;
    private int value;

    public Scalar(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Scalar(int value) {
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

    @Override
    public boolean isCompatible(Type type) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void check() {

    }
}


