package frontEnd.tree.Type;

import antlr.WACCParser;
import frontEnd.ErrorHandling.InvalidTypeException;
import frontEnd.tree.ASTTree;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseType extends ASTTree {

    //private final String type;

    public abstract boolean isCompatible(BaseType type);
    public abstract String toString();

//    public Type(String type) {
//        if(type.compareTo("bool") == 0 || type.compareTo("int") == 0 || type.compareTo("char") == 0 ||
//                type.compareTo("string") == 0) {
//            this.type = type;
//        } else {
//            System.err.println("Error: " + type + " is not a valid type.");
//            System.exit(-1);
//        }
//    }

    public static final BaseType BOOL = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "bool";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == BOOL;
        }

    } ;

    public static final BaseType INT = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "int";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == INT;
        }

    } ;

    public static final BaseType CHAR = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "char";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == CHAR;
        }

    } ;

    public static final BaseType STRING = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "string";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return type == STRING;
        }

    } ;

    public static final BaseType NULL = new BaseType() {

        @Override
        public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
            return true;
        }

        @Override
        public String toString() {
            return "null";
        }

        @Override
        public boolean isCompatible(BaseType type) {
            return true;
        }

    } ;

    public static BaseType evalType(WACCParser.TypeContext ctx) {
		return evalType(ctx.getText());
	}	

	public static final String pairRegexSplitter = " *[\\)\\(,] *";
	public static final String arrayRegexSplitter = "[\\[\\]]";
	// Utility method for converting a WACCParser.TypeContext into a WACCType
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

			throw new InvalidTypeException("The type provided was not recognised: " + typeString);
		}
	}
}

