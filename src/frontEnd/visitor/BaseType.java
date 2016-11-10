package frontEnd.visitor;

public final class BaseType extends Type {

    private BaseTypeCode baseTypeCode;

    public BaseType(BaseTypeCode baseTypeCode) {
        this.baseTypeCode = baseTypeCode;
    }

    public BaseTypeCode getTypeCode() {
        return baseTypeCode;
    }

    @Override
    public Type reduce() {
        // Base type is non-reducible.
        return this;
    }

    @Override
    public String toString() {
        return baseTypeCode.toString();
    }
}
