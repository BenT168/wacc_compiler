package frontEnd.expr;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
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
    Type booltmp = new BaseType(BaseTypeEnum.BOOL);
    Type inttmp = new BaseType(BaseTypeEnum.INT);


    if(ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null
        || ctx.PLUS() != null || ctx.MINUS() != null) {
      boolean bothNotInt = (!(lhs.equals(inttmp)) || !(rhs.equals(inttmp)));
      if (bothNotInt) {
        throw new SemanticException("Wrong argument types for binary operator.\n" +
                "Expected types : INT, INT\n" +
                "Actual type : " + lhs.toString() + " , " + rhs.toString());
      }
      type = new BaseType(BaseTypeEnum.INT);
    } else if(ctx.LT() != null || ctx.LTE() != null || ctx.GT() != null || ctx.GTE() != null
        || ctx.EQ() != null || ctx.NEQ() != null) {
      if(!lhs.equals(rhs)) {
        throw new SemanticException("Expected arguments to be the same type in Binary Operator\n "+
                "Actual type : " + lhs.toString() + " , " + rhs.toString());
      }
      type = booltmp;
    } else if(ctx.AND() != null || ctx.OR() != null) {
      if(!lhs.equals(booltmp) || !rhs.equals(booltmp)) {
        throw new SemanticException("Wrong argument types for binary operator.\n" +
                "Expected types : Bool, Bool\n "+
                "Actual type : " + lhs.toString() + " , " + rhs.toString());
      }
      type = booltmp;
    }
    return type;
  }
}
