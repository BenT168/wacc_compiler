package frontEnd.stat;

import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.type.BaseType;
import frontEnd.type.BaseTypeEnum;
import frontEnd.type.Type;

public class ExitStat extends Node {
  private Type t;

  public ExitStat(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    Type temp = new BaseType(BaseTypeEnum.INT);
    if (!(t.equals(temp))) {
      new SemanticException("In 'Exit' statement:\nExpecting type: " + temp.toString() + "\nActual: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
