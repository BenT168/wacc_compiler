package frontEnd.tree.Type;

public class ArrayType extends BaseType {

	private BaseType elementType;
	private String[] exprs;

	public ArrayType(BaseType baseType) {
		this.elementType = baseType;
	}

	public boolean isCompatible(BaseType ext) {
		if (!(ext instanceof ArrayType)) {
			return elementType.isCompatible(ext);
		}
		if (!(elementType.equals(BaseType.NULL))){
			ArrayType other =(ArrayType) ext;
			return elementType.isCompatible(other.getType());
		}
		return true;
	}

	public BaseType getType() {
		return elementType;
	}

	@Override
	public String toString() {
		return elementType.toString() + "[ ]";
	}

}