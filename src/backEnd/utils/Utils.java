package backEnd.utils;

import antlr.WACCParser;
import backEnd.OpCode;

import static backEnd.utils.LHSRHSNodeType.*;

final public class Utils {

    private Utils() {}

    public static ExprContext getExprContext(WACCParser.ExprContext ctx) {
       if (ctx.intLiter() != null)
           return ExprContext.INT_LITER;
        else if (ctx.boolLiter() != null)
            return ExprContext.BOOL_LITER;
        else if (ctx.charLiter() != null)
            return ExprContext.CHAR_LITER;
        else if (ctx.stringLiter() != null)
            return ExprContext.STR_LITER;
        else if (ctx.ident() != null)
            return ExprContext.IDENT;
        else if (ctx.pairLiter() != null)
            return ExprContext.PAIR_LITER;
        else if (ctx.expr().size() == 1)
            return ExprContext.UNARY_EXPR;
        else /* ctx.expr.size() == 2 */
            return ExprContext.BINARY_EXPR;
    }

    public static OpCode getUnaryOpCode(WACCParser.ExprContext ctx) {
        if (ctx.MINUS() != null)
            return OpCode.SUB;
        else if (ctx.ORD() != null)
            return OpCode.SUB;
        else if (ctx.CHR() != null)
            return OpCode.SUB;
        else if (ctx.NOT() != null)
            return OpCode.LOAD_IMM;
        else /* ctx.LEN() != null */
            return OpCode.LOAD_IMM;
    }

    public static OpCode getBinaryOpCode(WACCParser.ExprContext ctx) {
        if (ctx.MUL() != null)
            return OpCode.MUL;
        else if (ctx.PLUS() != null)
            return OpCode.ADD;
        else if (ctx.MINUS() != null)
            return OpCode.SUB;
        else if (ctx.MOD() != null)
            return OpCode.MOD;
        else if (ctx.DIV() != null)
            return OpCode.DIV;
        else if (ctx.GT() != null)
            return OpCode.GT;
        else if (ctx.GTE() != null)
            return OpCode.GTE;
        else if (ctx.LTE() != null)
            return OpCode.LTE;
        else if (ctx.LT() != null)
            return OpCode.LT;
        else if (ctx.AND() != null)
            return OpCode.AND;
        else if (ctx.OR() != null)
            return OpCode.OR;
        else if (ctx.NEQ() != null)
            return OpCode.NEQ;
        else /* ctx.EQ() != null */
            return OpCode.EQ;
    }

    public static LHSRHSNodeType getAssignRHSContext(WACCParser.AssignRHSContext ctx) {
        if (ctx.expr().size() == 1)
            return EXPR;
        else if (ctx.arrayLiter() != null)
            return ARRAY_LITER;
        else if (ctx.expr().size() == 2)
            return NEWPAIR;
        else if (ctx.CALL() != null)
            return CALL;
        else
            return PAIR_ELEM;
    }


    public static LHSRHSNodeType getAssignLHSContext(WACCParser.AssignLHSContext ctx) {
        if (ctx.ident() != null)
            return IDENT;
        else if (ctx.pairElem() != null)
            return PAIR_ELEM;
        else /* ctx.arrayElem() != null */
            return ARRAY_ELEM;
    }
}
