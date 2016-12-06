package frontend.stat;

import frontend.Node;
import frontend.exception.SemanticException;
import frontend.type.Type;

public class VarDecNode extends Node {

  private Type t1;
  private Type t2;

  public VarDecNode(Type t1, Type t2) {
    this.t1 = t1;
    this.t2 = t2;
  }

  @Override
  public Object check() {
    if (!(t1.equals(t2))) {
      throw new SemanticException("Type mismatch error: \n" +
              "Expected: " + t1.toString() +
              "\nActual: " + t2.toString());
    }
    return null;
  }
}
