package frontEnd.visitor;

import antlr.WACCParser;

public class BinaryExprAST extends AST {
  private Type lhs;
  private Type rhs;
  private Type type = null;
  private WACCParser.ExprContext ctx;

  public BinaryExprAST(Type lhs, Type rhs, WACCParser.ExprContext ctx) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    Type booltmp = new BaseType(BaseTypeCode.BOOL);
    Type inttmp = new BaseType(BaseTypeCode.INT);


    if(ctx.MUL() != null || ctx.DIV() != null || ctx.MOD() != null
        || ctx.PLUS() != null || ctx.MINUS() != null) {
      boolean bothNotInt = (!(lhs.equals(inttmp)) || !(rhs.equals(inttmp)));
      if (bothNotInt) {
        System.err.println("Wrong argument types for binary operator.");
        System.err.println("Expected types : INT, INT ");
        System.err.println("Actual type : " + lhs.toString() + " , " + rhs.toString());
        System.exit(200);
      }
      type = new BaseType(BaseTypeCode.INT);
    } else if(ctx.LT() != null || ctx.LTE() != null || ctx.GT() != null || ctx.GTE() != null
        || ctx.EQ() != null || ctx.NEQ() != null) {
      if(!lhs.equals(rhs)) {
        System.err.println("Expected arguments to be the same type in Binary Operator");
        System.err.println("Actual type : " + lhs.toString() + " , " + rhs.toString());
        System.exit(200);
      }
      type = booltmp;
    } else if(ctx.AND() != null || ctx.OR() != null) {
      if(!lhs.equals(booltmp) || !rhs.equals(booltmp)) {
        System.err.println("Wrong argument types for binary operator.");
        System.err.println("Expected types : Bool, Bool ");
        System.err.println("Actual type : " + lhs.toString() + " , " + rhs.toString());
        System.exit(200);
      }
      type = booltmp;
    }
    return type;
  }
}
