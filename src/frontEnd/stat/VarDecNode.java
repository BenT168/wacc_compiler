package frontEnd.stat;

import frontEnd.Node;
import frontEnd.type.Type;

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
      System.err.println("Type mismatch error: \nExpected: " + t1.toString() + "\nActual: " + t2.toString());
      System.exit(200);
    }
    return null;
  }
}
