package frontend.type;

import backend.Token;
import backend.Register;
import backend.StackLocation;
import backend.TokenSequence;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;

/* Class for the PairLeaf Type
 * Contains type info for 1st and 2nd elements and functionality for NULLs
 * Functionality to check other types are compatible with the pair
 * Rule: 'pair' '(' pair-elem-type ',' pair-elem-type ')'
 */

public class PairType extends BaseType {

	private static PairType NULL_PAIR = new PairType(BaseType.NULL, BaseType.NULL);
	private BaseType fst;
	private BaseType snd;
	private final int VAR_SIZE = 4;

	public PairType(BaseType fst, BaseType snd) {
		this.fst = (fst instanceof PairType) ? NULL_PAIR : fst;
		this.snd = (snd instanceof PairType) ? NULL_PAIR : snd;
	}

	public BaseType getFstType() {
		return fst;
	}

	public BaseType getSndType() {
		return snd;
	}

	@Override
	public boolean isCompatible(BaseType other) {
		// NULL can be assigned to pairs
		if (other == BaseType.NULL) {
			return true;
		}

		if (!(other instanceof PairType)) {
			return false;
		}

		PairType otherPair = (PairType) other;

		return 	checkType(this.getFstType(), otherPair.getFstType())
				&& checkType(this.getFstType(), otherPair.getFstType());
	}

	// utility method for checking pair types
	private static boolean checkType(BaseType lhs, BaseType rhs) {
		boolean compatible = lhs == BaseType.NULL || lhs.isCompatible(rhs);
		if (!compatible) {
			throw new SemanticErrorException("The types " + lhs.toString() +
					" and " + rhs.toString() + "are incompatible.");
		}
		return compatible;
	}

	@Override
	public String toString() {
		return "pair("+fst.toString()+", "+snd.toString()+")";
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
