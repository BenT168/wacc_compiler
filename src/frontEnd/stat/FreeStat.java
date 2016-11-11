package frontEnd.stat;

import frontEnd.Node;
import frontEnd.exception.SemanticException;
import frontEnd.type.PairType;
import frontEnd.type.Type;
import frontEnd.type.ArrayType;

public class FreeStat extends Node {

  private Type t;

  public FreeStat(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    // Nulls signify generic arrays/pairs.
    if (!(t instanceof PairType || t instanceof ArrayType)) {
      throw new SemanticException("In 'Free' statement:\n" +
              "Expecting type: PAIR or ARRAY\n" +
              "Actual type: " + t.toString());

    }
    return null;
  }
}
