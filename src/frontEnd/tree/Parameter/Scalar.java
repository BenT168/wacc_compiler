package frontEnd.tree.Parameter;

import frontEnd.tree.Type.BaseType;

public class Scalar<T> extends BaseType {

    private int min = 0;
    private int max = 0;
    private T value;

    public Scalar(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public Scalar(T value, int min, int max) {
        this.min = min;
        this.max = max;
        if (isAcceptableValue(value)) {
            this.value = value;
        } else {
            System.err.println("Error: The value " + value + " is not within the range.");
            System.exit(-1);
        }
    }

    private boolean isAcceptableValue(T value) {
        int numericalValue = (int) value;
        return numericalValue <= min && numericalValue >= max;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean isCompatible(BaseType type) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

}


