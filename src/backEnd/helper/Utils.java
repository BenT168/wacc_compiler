package backEnd.helper;

import antlr.WACCParser;
import jdk.nashorn.internal.objects.NativeUint8Array;
import org.antlr.v4.runtime.tree.ParseTree;

public class Utils {
    public static final String INT = "int";
    public static final String BOOL = "bool";
    public static final String CHAR = "char";
    public static final String STRING = "string";
    public static final String DEFAULT_RETURN_REG_VALUE = "0";

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
            return 4;
        }
    }

    public static boolean isUnaryOper(WACCParser.ExprContext ctx) {
        return (ctx.NOT() != null || ctx.MINUS() != null || ctx.LEN() != null || ctx.ORD() != null||
                ctx.CHR() != null);
    }

    public static boolean isBinaryOper(ParseTree parseTree) {
        WACCParser.ExprContext ctx = (WACCParser.ExprContext) parseTree;
        return (ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null || ctx.PLUS() != null||
                ctx.MINUS() != null || ctx.LT() != null || ctx.LTE() != null || ctx.GT() != null || ctx.GTE() != null||
                ctx.EQ() != null || ctx.NEQ() != null || ctx.AND() != null ||ctx.OR() != null);
    }

    public static boolean isLiter(WACCParser.ExprContext ctx) {
        if(ctx.getText().charAt(0) == '-') {
            return true;
        }
        return(ctx.intLiter() != null || ctx.stringLiter() != null || ctx.pairLiter() != null
        || ctx.arrayElem() != null || ctx.boolLiter()!= null || ctx.charLiter() != null);
    }

    public static boolean isIdent(WACCParser.ExprContext ctx) {
        return ctx.ident() != null;
    }

    public static Variable getVariable(WACCParser.DeclareContext ctx) {
        int o = Utils.getNumOfBytesForType(ctx.type());
        Variable v = new Variable(ctx.getChild(1).getText(), o, ctx.type());
        if(isLiter(ctx.assignRHS().expr(0))) {
            v.setValue(ctx.assignRHS().expr(0).getText());
        }
        return v;
    }

    public static int getDepth(WACCParser.TypeContext type) {
        int count = 1;
        if (type.getChild(0) instanceof WACCParser.BaseTypeContext ||
                type.getChild(0) instanceof WACCParser.PairTypeContext) {
            return count;
        } else {
            count++;
        }
        return count;

    }

    public static ParseTree getActualTypeOfArray(WACCParser.TypeContext ctx) {
        if (!(ctx.getChild(0) instanceof  WACCParser.BaseTypeContext || ctx.getChild(0) instanceof WACCParser.PairTypeContext)) {
            return getActualTypeOfArray((WACCParser.TypeContext) ctx.getChild(0));
        } else {
            return ctx;
        }
    }



}

