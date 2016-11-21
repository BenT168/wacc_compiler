package frontEnd;

import antlr.WACCParser;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
import frontEnd.type.Type;
import org.antlr.v4.runtime.misc.NotNull;

public class Assignment extends Node {

  private Type t1;
  private Type t2;
  private WACCParser.AssignContext ctx;

  public Assignment(Type t1, Type t2, @NotNull WACCParser.AssignContext ctx) {
    this.t1 = t1;
    this.t2 = t2;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    if (!(t1.equals(t2))) {
      //Throw Semantic Error
      String msg = "Type mismatch error:\n" + "Expected: " + t1.toString() +
              "\nActual: " + t2.toString();
      ThrowException.callSemanticException(line, column, msg);
    }
    return null;
  }
}
