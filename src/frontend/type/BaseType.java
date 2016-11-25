package frontend.type;

import antlr.WACCParser.TypeContext;
import backend.StackLocation;
import backend.Token;
import backend.Register;
import backend.TokenSequence;
import backend.tokens.print.PrintReferenceToken;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.PrintBoolToken;
import backend.tokens.print.PrintCharToken;
import backend.tokens.print.PrintIntToken;
import backend.tokens.print.PrintStringToken;
import frontend.exception.SemanticErrorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BaseType {

	public abstract boolean isCompatible(BaseType other);
	public abstract String toString();


	/**
	 * @param dest The destination address of the store
	 * @param source The source address of the store
	 * @return Returns the assembly code to correctly store a given expression type
	 */
	public abstract StoreToken storeAssembly(Register dest, Register source);

	public abstract LoadAddressToken loadAssembly(Register dest, Register source);


	public abstract Token passAsArg(Register r);
	public abstract int getVarSize();
	public abstract TokenSequence printAssembly(Register r);
	public abstract TokenSequence storeAssembly(Register register, StackLocation pos);

	/*
	 * The following are here as they are definite types,
	 * with no fields or parameters. Any reference to basic
	 * types will be references to these objects.
	 */
	public static final BaseType BOOL = new BaseType() {

		private final int VAR_SIZE = 1;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == BOOL;
		}

		@Override
		public String toString() {
			return "bool";
		}

		@Override
		public Token passAsArg(Register r) {
			return new StorePreIndexToken("B", Register.sp, r, -VAR_SIZE );
		}

		@Override
		public int getVarSize() {
			return VAR_SIZE;
		}

		@Override
		public StoreToken storeAssembly(Register dest, Register source) {
			return new StoreToken("B", dest, source);
		}

		@Override
		public TokenSequence storeAssembly(Register source, StackLocation pos) {
			int index = pos.getStackIndex();
			return new TokenSequence(
					new StoreToken("B", Register.sp, source, index));
		}

		@Override
		public TokenSequence printAssembly(Register r) {
			PrintBoolToken tok = new PrintBoolToken(r);
			return new TokenSequence(tok);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken("SB", dest, source);
		}
	};
	public static final BaseType INT = new BaseType() {

		private final int VAR_SIZE = 4;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == INT;
		}

		@Override
		public String toString() {
			return "int";
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
		public TokenSequence storeAssembly(Register source, StackLocation pos) {
			int index = pos.getStackIndex();
			return new TokenSequence(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokenSequence printAssembly(Register register) {
			Token intTok = new PrintIntToken(register);
			return new TokenSequence(intTok);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}
	};
	public static final BaseType CHAR = new BaseType() {

		private final int VAR_SIZE = 1;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == CHAR;
		}

		@Override
		public String toString() {
			return "char";
		}

		@Override
		public Token passAsArg(Register r) {
			return new StorePreIndexToken("B", Register.sp, r, -VAR_SIZE  );
		}

		@Override
		public int getVarSize() {
			return VAR_SIZE;
		}

		@Override
		public StoreToken storeAssembly(Register dest, Register source) {
			return new StoreToken("B", dest, source);
		}

		@Override
		public TokenSequence storeAssembly(Register source, StackLocation pos) {
			int index = pos.getStackIndex();
			return new TokenSequence(
					new StoreToken("B", Register.sp, source, index));
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken("SB", dest, source);
		}

		@Override
		public TokenSequence printAssembly(Register register) {
			Token charTok = new PrintCharToken(register);
			return new TokenSequence(charTok);
		}

	};
	public static final BaseType STRING = new ArrayType(CHAR) {

		@Override
		public boolean isCompatible(BaseType other) {
			return other == STRING;
		}

		@Override
		public String toString() {
			return "string";
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
		public TokenSequence storeAssembly(Register source, StackLocation pos) {
			int index = pos.getStackIndex();
			return new TokenSequence(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokenSequence printAssembly(Register register) {
			Token print = new PrintStringToken(register);
			return new TokenSequence(print);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}

	};
	public static final BaseType NULL = new BaseType() {

		@Override
		public String toString() {
			return "WACC-null";
		}

		@Override
		public boolean isCompatible(BaseType other) {
			return true;
		}

		@Override
		public Token passAsArg(Register r) {
			throw new UnsupportedOperationException("Cannot store a variable of type NULL");
		}

		@Override
		public int getVarSize() {
			return 0;
		}

		@Override
		public StoreToken storeAssembly(Register dest, Register source) {
			return new StoreToken(dest, source);
		}

		@Override
		public TokenSequence storeAssembly(Register source, StackLocation pos) {
			int index = pos.getStackIndex();
			return new TokenSequence(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokenSequence printAssembly(Register r) {
			Token print = new PrintReferenceToken(r);
			return new TokenSequence(print);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}

	};


	public static BaseType evalType(TypeContext ctx) {
		return evalType(ctx.getText());
	}

	public static final String pairRegexSplitter = " *[\\)\\(,] *";
	public static final String arrayRegexSplitter = "[\\[\\]]";
	// Utility method for converting a WACCParser.TypeContext into a BaseType
	public static BaseType evalType(String typeString) {
		switch (typeString) {
		case "int":
			return INT;
		case "bool":
			return BOOL;
		case "char":
			return CHAR;
		case "string":
			return STRING;
		default:
			//matches any array
			Pattern arrayPattern = Pattern.compile("\\[\\]");
			Matcher arrayMatcher = arrayPattern.matcher(typeString);
			if (typeString.endsWith("[]")
					&& arrayMatcher.find()) {
				BaseType baseType = evalType(typeString.split(arrayRegexSplitter)[0]);
				ArrayType array = new ArrayType(baseType);
				while(arrayMatcher.find()) {
					array = new ArrayType(array);
				}
				return array;
			}

			//matches any pair
			if (typeString.startsWith("pair")) {
				// Extract inner types
				String[] innerTypes = typeString.split(pairRegexSplitter);
				String fstString = innerTypes[1];
				String sndString = innerTypes[2];

				// Pairs of pairs have type `pair(null, null)`
				BaseType fstType = fstString.equals("pair") ? BaseType.NULL : evalType(fstString);
				BaseType sndType = sndString.equals("pair") ? BaseType.NULL : evalType(sndString);


				return new PairType(fstType, sndType);
			}

			throw new SemanticErrorException( "The type provided was not recognised: " + typeString);
		}
	}


}