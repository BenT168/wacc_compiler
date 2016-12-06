package frontend.stat;

import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.BaseType;
import frontend.type.BaseTypeEnum;
import frontend.type.Type;

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
