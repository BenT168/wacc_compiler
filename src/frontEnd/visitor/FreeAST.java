package frontEnd.visitor;

public class FreeAST extends AST {

  private Type t;

  public FreeAST(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    // Nulls signify generic arrays/pairs.
    if (!(t instanceof PairType || t instanceof ArrayType)) {
      System.err.println("In 'Free' statement:\nExpecting type: PAIR or ARRAY\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
