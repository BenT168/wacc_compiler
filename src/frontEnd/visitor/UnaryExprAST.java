package frontEnd.visitor;

import antlr.WACCParser;

public class UnaryExprAST extends AST {

  private Type type = null;
  private Type argType;
  private WACCParser.ExprContext ctx;

  public UnaryExprAST(Type argType, WACCParser.ExprContext ctx) {
    this.argType = argType;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    Type booltmp = new BaseType(BaseTypeCode.BOOL);
    Type chartmp = new BaseType(BaseTypeCode.CHAR);
    Type inttmp = new BaseType(BaseTypeCode.INT);

    if(ctx.CHR() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        System.err.println("Wrong argument type for unary operator.");
        System.err.println("Expected type : INT ");
        System.err.println("Actual type : " + argType.toString());
        System.exit(200);
      }
      //return type = char
      type = chartmp;
    } else if(ctx.ORD() != null) {
      //argument type = char
      if(!argType.equals(chartmp)) {
        System.err.println("Wrong argument type for unary operator.");
        System.err.println("Expected type : Int ");
        System.err.println("Actual type : " + argType.toString());
        System.exit(200);
      }
      //return type = int
      type = inttmp;
    } else if(ctx.LEN() != null) {
      //argument type = array
      if(!(argType instanceof ArrayType)) {
        System.err.println("Wrong argument type for unary operator.");
        System.err.println("Expected type : Array ");
        System.err.println("Actual type : " + argType.toString());
        System.exit(200);
      }
      //return type = int
      type = inttmp;
    } else if(ctx.NOT() != null) {
      //argument type = bool
      if(!argType.equals(booltmp)) {
        System.err.println("Wrong argument type for unary operator.");
        System.err.println("Expected type : Bool ");
        System.err.println("Actual type : " + argType.toString());
        System.exit(200);
      }
      //return type = int
      type = booltmp;
    } else if(ctx.MINUS() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        System.err.println("Wrong argument type for unary operator.");
        System.err.println("Expected type : Int ");
        System.err.println("Actual type : " + argType.toString());
        System.exit(200);
      }
      //return type = int
      type = inttmp;
    }
    return type;
  }
}
