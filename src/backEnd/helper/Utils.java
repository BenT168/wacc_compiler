package backEnd.helper;

import antlr.WACCParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class Utils {
    public static final String INT = "int";
    public static final String BOOL = "bool";
    public static final String CHAR = "char";
    public static final String STRING = "string";

    public static int getNumOfBytesForTypeArrayLiter(ParseTree ctx) {
        if (ctx.getChild(0) instanceof WACCParser.BaseTypeContext) {
            String baseType = ctx.getChild(0).getText();
            if (baseType.equals(INT)) {
                return 4;
            } else if (baseType.equals(BOOL)) {
                return 1;
            } else if (baseType.equals(CHAR)) {
                return 1;
            } else if (baseType.equals(STRING)) {
                return 4;
            }
        } else if (ctx.getChild(0) instanceof WACCParser.PairTypeContext) {
            // TODO: NUMBER OF BYTES FOR PAIR
            return 4;
        } else if (ctx.getChild(0) instanceof WACCParser.PairElemTypeContext) {
            return 4;
        } else {
            return getNumOfBytesForTypeArrayLiter(ctx.getChild(0));
        }
        // ERROR CANT FIND TYPE
        return -1;
    }

    public static int getNumOfBytesForType(ParseTree ctx) {
        if (ctx.getChild(0) instanceof WACCParser.BaseTypeContext) {
            String baseType = ctx.getChild(0).getText();
            if (baseType.equals(INT)) {
                return 4;
            } else if (baseType.equals(BOOL)) {
                return 1;
            } else if (baseType.equals(CHAR)) {
                return 1;
            } else if (baseType.equals(STRING)) {
                return 4;
            }
        } else if (ctx.getChild(0) instanceof WACCParser.PairTypeContext) {
            // TODO: NUMBER OF BYTES FOR PAIR
            return 4;
        } else if (ctx.getChild(0) instanceof WACCParser.PairElemTypeContext) {
            return 4;
        } else {
            // array type
            return 4;
        }
        // ERROR CANT FIND TYPE
        return -1;
    }

    public static int getNumOfBytesForArray(WACCParser.TypeContext ctx) {
        if (!(ctx.getChild(0) instanceof WACCParser.BaseTypeContext) &&
                !(ctx.getChild(0) instanceof WACCParser.PairTypeContext)) {
            return 4 + getNumOfBytesForArray((WACCParser.TypeContext) ctx.getChild(0));
        } else if (ctx.getChild(0).getText().equals("string")) {
            return 1;
        } else {
            return getNumOfBytesForType((WACCParser.TypeContext) ctx);
        }
    }

    public static int getNumOfBytesForExpr(ParseTree ctx, Function currentFunction) {
        if(ctx instanceof WACCParser.IdentContext){
            return currentFunction.getVariable(ctx.getText()).getOffset();
        } else if (ctx instanceof WACCParser.IdentContext) {
            return currentFunction.getVariable(ctx.getText()).getOffset();
        } else if (ctx instanceof WACCParser.BoolLiterContext || ctx
                instanceof WACCParser.CharLiterContext) {
            return 1;
        } else {
            //TODO: Need to take into account the other expressions, binary oper, unary oper and brackets shit
            return 4;
        }
    }



}

