package frontEnd.tree.Type;

public class Array extends Type {
  private Type elementType;
  private int elements;

  public Array(Type baseType) {
	this.elementType = baseType;
  }

  public boolean isCompatible(Type ext) {
	if (!(ext instanceof Array)) {
		if (!elementType.isCompatible(ext)) {
			return false;
		}
		return true;
	}

}
