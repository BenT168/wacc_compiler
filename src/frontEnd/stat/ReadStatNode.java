package frontEnd.stat;

import antlr.WACCParser;
import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.exception.ThrowException;
import frontEnd.type.Type;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import org.antlr.v4.runtime.misc.NotNull;

public class ReadStatNode extends Node {

  private Type t;
  private WACCParser.ReadContext ctx;

  public ReadStatNode(Type t, @NotNull WACCParser.ReadContext ctx) {
    this.t = t;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
    //Getting line and column number for error message
    int line = ctx.start.getLine();
    int column = ctx.start.getCharPositionInLine();

    Type temp1 = new BaseType(BaseTypeEnum.CHAR);
    Type temp2 = new BaseType(BaseTypeEnum.INT);
    if (!(t.equals(temp1) || t.equals(temp2))) {
      //Throw Semantic Error
      String msg = "In 'Read' statement:\n" +
              "Expecting type: " + temp1.toString()
              + " or " + temp2.toString() +
              "\nActual type: " + t.toString();
      ThrowException.callSemanticException(line, column, msg);
    }
    return null;
  }
}
