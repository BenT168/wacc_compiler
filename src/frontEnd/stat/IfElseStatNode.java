package frontEnd.stat;

import frontEnd.Node;
import frontEnd.type.Type;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;

public class IfElseStatNode extends Node {
  private Type t;
  private String expr;

  public IfElseStatNode(Type t, String expr) {
    this.t = t;
    this.expr = expr;
  }

  @Override
  public Object check() {
    Type temp = new BaseType(BaseTypeEnum.BOOL);
    if (!(t.equals(temp))) {
      System.err.println("In expression: " + expr + "\nExpected type: " + temp.toString() + "\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
