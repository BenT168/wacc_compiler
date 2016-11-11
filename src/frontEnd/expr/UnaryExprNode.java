package frontEnd.expr;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.type.ArrayType;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;

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
