package frontEnd.stat;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;
import org.antlr.v4.runtime.misc.NotNull;

public class WhileStatNode extends Node {
  private Type t;
  private String expr;
  private @NotNull WACCParser.WhileContext ctx;

  public WhileStatNode(Type t, String expr, @NotNull WACCParser.WhileContext ctx) {
    this.t = t;
    this.expr = expr;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    Type temp = new BaseType(BaseTypeEnum.BOOL);
    if (!(t.equals(temp))) {
      //Throw Semantic Error
      String msg = "In expression: " + expr +
              "\nExpected type: " + temp.toString() +
              "\nActual type: " + t.toString();
      ThrowException.callSemanticException(line, column, msg);
    }
    return null;
  }
}

