package frontend.type;

import java.util.Objects;

public final class BaseType extends Type {

    private BaseTypeEnum baseTypeEnum;

    public BaseType(BaseTypeEnum baseTypeEnum) {
        this.baseTypeEnum = baseTypeEnum;
    }

    public BaseTypeEnum getTypeCode() {
        return baseTypeEnum;
    }

    @Override
    public Type reduce() {
        // Base type is non-reducible.
        return this;
    }

    @Override
    public String toString() {
        return baseTypeEnum.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseType)) {
            return false;
        }
        if (obj == this)
            return true;
        BaseType rhs = (BaseType) obj;
        return this.baseTypeEnum == rhs.baseTypeEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.baseTypeEnum);
    }
}
