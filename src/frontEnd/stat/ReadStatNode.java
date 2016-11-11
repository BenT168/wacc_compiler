package frontEnd.stat;

import frontEnd.Node;
import frontEnd.type.Type;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;

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
      System.err.println("In 'Read' statement:\nExpecting type: " + temp1.toString()
              + " or " + temp2.toString() + "\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
