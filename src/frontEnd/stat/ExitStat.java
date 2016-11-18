package frontEnd.stat;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;
import org.antlr.v4.runtime.misc.NotNull;

public class ExitStat extends Node {
  private Type t;
  private WACCParser.ExitContext ctx;

  public ExitStat(Type t, @NotNull WACCParser.ExitContext ctx) {
    this.t = t;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    Type temp = new BaseType(BaseTypeEnum.INT);
    if (!(t.equals(temp))) {
      //Throw Semantic Error
      String msg = "In 'Exit' statement:\n "+
              "Expecting type: " + temp.toString() + "\n Actual: " + t.toString();
      ThrowException.callSemanticException(line, column, msg);
    }
    return null;
  }
}
