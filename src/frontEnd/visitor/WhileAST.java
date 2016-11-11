package frontEnd.visitor;

public class WhileAST extends AST {
  private Type t;
  private String expr;

  public WhileAST(Type t, String expr) {
    this.t = t;
    this.expr = expr;
  }

  @Override
  public Object check() {
    Type temp = new BaseType(BaseTypeCode.BOOL);
    if (!(t.equals(temp))) {
      System.err.println("In expression: " + expr + "\nExpected type: " + temp.toString() + "\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}

