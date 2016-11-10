package frontEnd.visitor;

public class ArrayType extends Type {
    // Type of elements in array
    private Type type;

    public ArrayType(Type type) {
        this.type = type;
    }

    public BaseType getBaseType() {
        Type t = this.type;
        while (!(t instanceof BaseType)) {
            t = type.reduce();
        }
        return (BaseType) t;
    }

    @Override
    public Type reduce() {
        // Syntactic analysis that occurs before semantic analysis should ensure
        // that we do not enter an infinite loop
        return this.type.reduce();
    }
}
