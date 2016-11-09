package frontEnd.tree.Type;

public class Array extends Type {

	private Type elementType;

	public Array(Type baseType) {
		this.elementType = baseType;
	}

	public boolean isCompatible(Type ext) {
		if (!(ext instanceof Array)) {
			return elementType.isCompatible(ext);
		}
		if (!(elementType.equals(Type.NULL))){
			Array other =(Array) ext;
			return elementType.isCompatible(other.getElementType());
		}

		return true;

	}

	public Type getElementType() {
		return elementType;
	}

	@Override
	public String toString() {
		return "array-" + elementType.toString();
	}

	@Override
	public void check() {

	}
}