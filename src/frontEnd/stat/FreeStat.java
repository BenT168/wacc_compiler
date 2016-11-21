package frontEnd.stat;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
import frontEnd.type.PairType;
import frontEnd.type.Type;
import frontEnd.type.ArrayType;
import org.antlr.v4.runtime.misc.NotNull;

public class FreeStat extends Node {

  private Type t;
  private WACCParser.FreeContext ctx;

  public FreeStat(Type t, @NotNull WACCParser.FreeContext ctx) {
    this.t = t;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    // Nulls signify generic arrays/pairs.
    if (!(t instanceof PairType || t instanceof ArrayType)) {
      //Throw Semantic Error
      String msg = "In 'Free' statement:\n" +
              "Expecting type: PAIR or ARRAY\n" +
              "Actual type: " + t.toString();
      ThrowException.callSemanticException(line, column, msg);
    }
    return null;
  }
}
