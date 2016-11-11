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
      throw new SemanticException("In 'Exit' statement:\n "+
                "Expecting type: " + temp.toString() + "\n Actual: " + t.toString());

    }
    return null;
  }
}
