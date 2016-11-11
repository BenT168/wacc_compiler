package frontEnd.stat;

import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;

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
      throw new SemanticException("In expression: " + expr +
              "\nExpected type: " + temp.toString() +
              "\nActual type: " + t.toString());
    }
    return null;
  }
}
