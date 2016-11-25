package frontend.type;

import backend.Token;
import backend.Register;
import backend.StackLocation;
import backend.TokenSequence;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;

/* Class for the Array type
 * Contains the Array's Base Type
 * Functionality to check if other types are compatible with the array
 * Rule: type '[' ']'
 */

public class ArrayType extends BaseType {

	private BaseType baseType;
	protected final int VAR_SIZE = 4;

	public ArrayType(BaseType baseType) {
		this.baseType = baseType;
	}

	@Override
	public boolean isCompatible(BaseType other) {

		if (!(other instanceof ArrayType)) {
			if(!baseType.isCompatible(other)) {
				return false;
			}
			return true;
		}
		if (!(baseType == BaseType.NULL)) {
			ArrayType otherArray = (ArrayType) other;
			if ( otherArray.getBaseType() == BaseType.NULL) {
				return true;
			} else if (!baseType.isCompatible(otherArray.getBaseType())) {
				return false;
			}
			return true;
		}
		return true;
	}

	public BaseType getBaseType() {
		return baseType;
	}

	@Override
	public String toString() {
		return "array-" + baseType.toString();
	}

	@Override
	public Token passAsArg(Register r) {
		return new StorePreIndexToken(Register.sp, r, -VAR_SIZE  );
	}

	@Override
	public int getVarSize() {
		return VAR_SIZE;
	}

	@Override
	public StoreToken storeAssembly(Register dest, Register source) {
		return new StoreToken(dest, source);
	}

	@Override
	public TokenSequence printAssembly(Register register) {
		return new TokenSequence(new PrintReferenceToken(register));
	}

	@Override
	public TokenSequence storeAssembly(Register source, StackLocation pos) {
		int index = pos.getStackIndex();
		return new TokenSequence(
				new StoreToken(Register.sp, source, index));
	}

	@Override
	public LoadAddressToken loadAssembly(Register dest, Register source) {
		return new LoadAddressToken(dest, source);
	}
}
