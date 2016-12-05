package frontend.type;

import antlr.WACCParser.TypeContext;
import backend.Register;
import backend.StackPos;
import backend.TokSeq;
import backend.Token;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.print.*;
import backend.tokens.store.StorePreIndexToken;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BaseType {

	public abstract boolean isCompatible(BaseType other);

	public abstract String toString();

	public abstract StoreToken storeAssembly(Register dest, Register source);

	public abstract LoadAddressToken loadAssembly(Register dest, Register source);

	public abstract Token passAsArg(Register r);

	public abstract int getVarSize();

	public abstract TokSeq printAssembly(Register r);

	public abstract TokSeq storeAssembly(Register register, StackPos pos);

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
			return new StorePreIndexToken("B", Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken("B", Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register r) {
			PrintBoolToken tok = new PrintBoolToken(r);
			return new TokSeq(tok);
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
			return new StorePreIndexToken(Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token intTok = new PrintIntToken(register);
			return new TokSeq(intTok);
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
			return new StorePreIndexToken("B", Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken("B", Register.sp, source, index));
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken("SB", dest, source);
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token charTok = new PrintCharToken(register);
			return new TokSeq(charTok);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token print = new PrintStringToken(register);
			return new TokSeq(print);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register r) {
			Token print = new PrintReferenceToken(r);
			return new TokSeq(print);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}

	};

	//------------------------EXTENSION-----------------------------------

	public static final BaseType BIN = new BaseType() {

		private final int VAR_SIZE = 8;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == BIN;
		}

		@Override
		public String toString() {
			return "bin";
		}

		@Override
		public Token passAsArg(Register r) {
			return new StorePreIndexToken(Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token binTok = new ex_PrintBinToken(register);
			return new TokSeq(binTok);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}
	};

	public static final BaseType OCT = new BaseType() {

		private final int VAR_SIZE = 8;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == OCT;
		}

		@Override
		public String toString() {
			return "oct";
		}

		@Override
		public Token passAsArg(Register r) {
			return new StorePreIndexToken(Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token octTok = new ex_PrintOctToken(register);
			return new TokSeq(octTok);
		}

		@Override
		public LoadAddressToken loadAssembly(Register dest, Register source) {
			return new LoadAddressToken(dest, source);
		}
	};

	public static final BaseType HEX = new BaseType() {

		private final int VAR_SIZE = 8;

		@Override
		public boolean isCompatible(BaseType other) {
			return other == HEX;
		}

		@Override
		public String toString() {
			return "hex";
		}

		@Override
		public Token passAsArg(Register r) {
			return new StorePreIndexToken(Register.sp, r, -VAR_SIZE);
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
		public TokSeq storeAssembly(Register source, StackPos pos) {
			int index = pos.getStackIndex();
			return new TokSeq(
					new StoreToken(Register.sp, source, index));
		}

		@Override
		public TokSeq printAssembly(Register register) {
			Token hexTok = new ex_PrintHexToken(register);
			return new TokSeq(hexTok);
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
			case "bin":
				return BIN;
			case "oct":
				return OCT;
			case "hex":
				return HEX;
			default:
				//matches any array
				Pattern arrayPattern = Pattern.compile("\\[\\]");
				Matcher arrayMatcher = arrayPattern.matcher(typeString);
				if (typeString.endsWith("[]")
						&& arrayMatcher.find()) {
					BaseType baseType = evalType(typeString.split(arrayRegexSplitter)[0]);
					ArrayType array = new ArrayType(baseType);
					while (arrayMatcher.find()) {
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

				//matches any list
				if(typeString.startsWith("list") || typeString.startsWith("linkedList") ||
						typeString.startsWith("arrayList")) {
					return getListType(typeString);

				}

				//matches any map
				if(typeString.startsWith("map") || typeString.startsWith("hashMap")) {
					return getMapType(typeString);
				}

				throw new SemanticErrorException("The type provided was not recognised: " + typeString);
		}
	}

	private static BaseType getListType(String typeString) {
		if(!isNested(typeString)) {
			return getList(typeString);
		} else {
			//Nested List
			char[] type = new char[typeString.length()];
			int pos = 0;
			boolean charSeen = false;
			int recurseCount = 0;

			//Loop through typeString and get inner list
			for(int i = 0; i < typeString.length() ; i++) {
				if(typeString.charAt(i) == '<') {
					recurseCount++;
					charSeen = true;
					if(recurseCount == 1) {
						continue;
					}
				}
				if(charSeen) {
					type[pos] = typeString.charAt(i);
					pos++;
				}
				if(typeString.charAt(i) == '>') {
					break;
				}
			}

			String recurseString = String.valueOf(type).trim();
			if(recurseString.startsWith("linkedList") || recurseString.startsWith("arrayList")) {
				ListType list = new ListType(getListType(recurseString));
				return new ListType(list);
			} else { // not actually a nested list (may be a list of maps)
				return getList(recurseString);
			}
		}
	}

	private static BaseType getList(String typeString) {
		if(typeString.startsWith("hashMap")) { // Map in a list
			BaseType baseType = evalType(typeString);
			return new ListType(baseType);
    	}
		String requiredString = typeString.substring(typeString.indexOf("<") + 1, typeString.indexOf(">"));
		BaseType baseType = evalType(requiredString);
		return new ListType(baseType);
	}

	private static boolean isNested(String typeString) {
		int count = 0;
		for(int i = 0; i < typeString.length(); i++) {
			if(typeString.charAt(i) == '<') {
				count++;
			}
		}

		//Check if list in map
		if(count > 1 && (typeString.startsWith("map") || typeString.startsWith("hashMap"))) {
			typeString = getInnerString(typeString);
			if(fstList(typeString) && sndList(typeString)) {
				//both lists
				return false;
			}
		}
		return count > 1;
	}

	private static boolean fstList(String s) {
		return getFirst(s).startsWith("linkedList") || getFirst(s).startsWith("arrayList");

	}

	private static boolean sndList(String s) {
		return getSnd(s).startsWith("linkedList") || getSnd(s).startsWith("arrayList");

	}


	private static boolean isNestedInnerMap(String typeString) {
		return typeString.startsWith("hashMap");
	}

	private static BaseType getMapType(String typeString) {
		if(!isNested(typeString)) {
			return getMap(typeString);
		} else { //Nested Maps
			typeString = getInnerString(typeString);
			if(!isNestedInnerMap(getFirst(typeString)) && isNestedInnerMap(getSnd(typeString))) {
				BaseType firstType = evalType(getFirst(typeString));
				return new MapType(firstType, getMapType(getSnd(typeString)));
			} else if(!isNestedInnerMap(getSnd(typeString)) && isNestedInnerMap(getFirst(typeString))) {
				BaseType sndType = evalType(getSnd(typeString));
				return new MapType(getMapType(getFirst(typeString)), sndType);
			} else { // both nested
				return new MapType(getMapType(getFirst(typeString)), getMapType(getSnd(typeString)));
			}
		}
	}

	private static BaseType getMap(String typeString) {
		String s = getInnerString(typeString);
		if(fstList(s) && sndList(s)) {
			BaseType firstType = evalType(getFirst(s));
			BaseType sndType = evalType(getSnd(s));
			return new MapType(firstType,sndType);
		}
		String firstString = typeString.substring(typeString.indexOf("<") + 1, typeString.indexOf(","));
		String sndString = typeString.substring(typeString.indexOf(",") + 1, typeString.indexOf(">"));
		BaseType firstType = evalType(firstString);
		BaseType sndType = evalType(sndString);
		return new MapType(firstType,sndType);
	}


	private static String getInnerString(String typeString) {
		String sub = "";
		if(typeString.startsWith("map")) {
			sub = typeString.substring(4, typeString.length() - 1);
		} else if(typeString.startsWith("hashMap")) {
			sub = typeString.substring(8, typeString.length() - 1);
		}
		return sub;
	}

	private static String getFirst(String typeString) {
		if(typeString.startsWith("hashMap")) { // nested
			int posOfComma = getCommaPosition(typeString);
			return typeString.substring(0, posOfComma);
		} else { // not nested
			return typeString.split(",")[0];
		}
	}

	private static int getCommaPosition(String typeString) {
		int  countOpenArrow = 0;
		int countClosedArrow = 0;
		int posOfComma = 1;
		for(int i = 0; i < typeString.length(); i++) {
			if(typeString.charAt(i) == '<') {
				countOpenArrow++;
			}
			if(typeString.charAt(i) == '>') { // done with open brackets
				countClosedArrow++;
				if(countClosedArrow == countOpenArrow) {
					break;
				}
			}
			posOfComma++;
		}
		return posOfComma;
	}

	private static String getSnd(String typeString) {
		if(typeString.startsWith("hashMap")) { //nested
			int posOfComma = getCommaPosition(typeString);
			return typeString.substring(posOfComma + 1);
		} else { // not nested
			String temp = typeString.split(",")[1];
			return getText(temp);
		}
	}

	private static String getText(String s) {
		int commaPos = s.indexOf(",") + 1;
		return s.substring(commaPos);
	}


}
