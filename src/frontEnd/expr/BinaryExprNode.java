package frontEnd.expr;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.ThrowException;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;

public class BinaryExprNode extends Node {
  private Type lhs;
  private Type rhs;
  private Type type = null;
  private WACCParser.ExprContext ctx;

  public BinaryExprNode(Type lhs, Type rhs, WACCParser.ExprContext ctx) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    Type booltmp = new BaseType(BaseTypeEnum.BOOL);
    Type inttmp = new BaseType(BaseTypeEnum.INT);


    if(ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null
        || ctx.PLUS() != null || ctx.MINUS() != null) {
      boolean bothNotInt = (!(lhs.equals(inttmp)) || !(rhs.equals(inttmp)));
      if (bothNotInt) {
        //Throw Semantic Error
        String msg = "Wrong argument types for binary operator.\n" +
                "Expected types : INT, INT\n" +
                "Actual type : " + lhs.toString() + ", " + rhs.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      type = new BaseType(BaseTypeEnum.INT);
    } else if(ctx.LT() != null || ctx.LTE() != null || ctx.GT() != null || ctx.GTE() != null
        || ctx.EQ() != null || ctx.NEQ() != null) {

      //Check if lhs or rhs is an array, if so throw a semantic error
      if(lhs.toString().length() > 5 && ctx.EQ() == null || ctx.NEQ() == null) {
        if(lhs.toString().substring(0,5).equals("ARRAY")) {
          String msg = "Cannot Perform a Boolean Operator on an Array";
          ThrowException.callSemanticException(line, column, msg);
          //Check if lhs is a pair, if so throw a semantic error
        } else if(lhs.toString().substring(0,4).equals("PAIR") && ctx.EQ() == null && ctx.NEQ() == null) {
          String msg = "Cannot Perform a Boolean Operator on an Pair";
          ThrowException.callSemanticException(line, column, msg);
        }

      }
      if(!lhs.equals(rhs)) {
        //Throw Semantic Error
        String msg = "Expected arguments to be the same type in Binary Operator\n "+
                "Actual type : " + lhs.toString() + ", " + rhs.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      type = booltmp;
    } else if(ctx.AND() != null || ctx.OR() != null) {
      if(!lhs.equals(booltmp) || !rhs.equals(booltmp)) {
        //Throw Semantic Error
        String msg = "Wrong argument types for binary operator.\n" +
                "Expected types : BOOL, BOOL\n"+
                "Actual type : " + lhs.toString() + ", " + rhs.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      type = booltmp;
    }
    return type;
  }
}
