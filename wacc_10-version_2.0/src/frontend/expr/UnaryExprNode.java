package frontend.expr;

import antlr.WACCParser;
import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.ArrayType;
import frontend.type.BaseType;
import frontend.type.BaseTypeEnum;
import frontend.type.Type;

public class UnaryExprNode extends Node {

  private Type type = null;
  private Type argType;
  private WACCParser.ExprContext ctx;

  public UnaryExprNode(Type argType, WACCParser.ExprContext ctx) {
    this.argType = argType;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    Type booltmp = new BaseType(BaseTypeEnum.BOOL);
    Type chartmp = new BaseType(BaseTypeEnum.CHAR);
    Type inttmp = new BaseType(BaseTypeEnum.INT);

    if(ctx.CHR() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        throw new SemanticException("Wrong argument type for unary operator.\n" +
                "Expected type : INT \n" + "Actual type : " + argType.toString());
      }
      //return type = char
      type = chartmp;
    } else if(ctx.ORD() != null) {
      //argument type = char
      if(!argType.equals(chartmp)) {
        throw new SemanticException("Wrong argument type for unary operator.\n" +
                "Expected type : Int \n"+ "Actual type : " + argType.toString());
      }
      //return type = int
      type = inttmp;
    } else if(ctx.LEN() != null) {
      //argument type = array
      if(!(argType instanceof ArrayType)) {
        throw new SemanticException("Wrong argument type for unary operator.\n" +
                "Expected type : Array \n" +
                "Actual type : " + argType.toString());
      }
      //return type = int
      type = inttmp;
    } else if(ctx.NOT() != null) {
      //argument type = bool
      if(!argType.equals(booltmp)) {
        throw new SemanticException("Wrong argument type for unary operator.\n" +
                "Expected type : Bool \n" +
                "Actual type : " + argType.toString());
      }
      //return type = int
      type = booltmp;
    } else if(ctx.MINUS() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        throw new SemanticException("Wrong argument type for unary operator.\n" +
                "Expected type : Int \n" +
                "Actual type : " + argType.toString());
      }
      //return type = int
      type = inttmp;
    }
    return type;
  }
}
