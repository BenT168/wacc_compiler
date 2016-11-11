package frontEnd.tree.Type;

import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class ArrayType extends BaseType {

	private BaseType elementType;

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
