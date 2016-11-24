package frontEnd.expr;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
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
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    Type booltmp = new BaseType(BaseTypeEnum.BOOL);
    Type chartmp = new BaseType(BaseTypeEnum.CHAR);
    Type inttmp = new BaseType(BaseTypeEnum.INT);

    if(ctx.CHR() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        //Throw Semantic Error
        String msg = "Wrong argument type for unary operator.\n" +
                "Expected type : INT \n" + "Actual type : " + argType.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      //return type = char
      type = chartmp;
    } else if(ctx.ORD() != null) {
      //argument type = char
      if(!argType.equals(chartmp)) {
        //Throw Semantic Error
        String msg = "Wrong argument type for unary operator.\n" +
                "Expected type : INT \n" + "Actual type : " + argType.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      //return type = int
      type = inttmp;
    } else if(ctx.LEN() != null) {
      //argument type = array
      if(!(argType instanceof ArrayType)) {
        //Throw Semantic Error
        String msg = "Wrong argument type for unary operator.\n" +
                "Expected type : ARRAY \n" + "Actual type : " + argType.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      //return type = int
      type = inttmp;
    } else if(ctx.NOT() != null) {
      //argument type = bool
      if(!argType.equals(booltmp)) {
        //Throw Semantic Error
        String msg = "Wrong argument type for unary operator.\n" +
                "Expected type : BOOL \n" + "Actual type : " + argType.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      //return type = int
      type = booltmp;
    } else if(ctx.MINUS() != null) {
      //argument type = int
      if(!argType.equals(inttmp)) {
        //Throw Semantic Error
        String msg = "Wrong argument type for unary operator.\n" +
                "Expected type : INT \n" + "Actual type : " + argType.toString();
        ThrowException.callSemanticException(line, column, msg);
      }
      //return type = int
      type = inttmp;
    }
    return type;
  }
}
