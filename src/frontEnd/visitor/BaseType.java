package frontEnd.visitor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseType)) {
            return false;
        }
        if (obj == this)
            return true;
        BaseType rhs = (BaseType) obj;
        return this.baseTypeCode == rhs.baseTypeCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.baseTypeCode);
    }
}
