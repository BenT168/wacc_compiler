package frontend.stat;

import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.BaseType;
import frontend.type.BaseTypeEnum;
import frontend.type.Type;

public class WhileStatNode extends Node {
  private Type t;
  private String expr;

  public WhileStatNode(Type t, String expr) {
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

