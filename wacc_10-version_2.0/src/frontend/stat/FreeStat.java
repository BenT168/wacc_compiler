package frontend.stat;

import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.PairType;
import frontend.type.Type;
import frontend.type.ArrayType;

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
