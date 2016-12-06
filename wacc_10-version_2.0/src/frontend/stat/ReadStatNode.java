package frontend.stat;

import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.Type;
import frontend.type.BaseType;
import frontend.type.BaseTypeEnum;

public class ReadStatNode extends Node {

  private Type t;

  public ReadStatNode(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    Type temp1 = new BaseType(BaseTypeEnum.CHAR);
    Type temp2 = new BaseType(BaseTypeEnum.INT);
    if (!(t.equals(temp1) || t.equals(temp2))) {
      throw new SemanticException("In 'Read' statement:\n" +
              "Expecting type: " + temp1.toString()
              + " or " + temp2.toString() +
              "\nActual type: " + t.toString());
    }
    return null;
  }
}
