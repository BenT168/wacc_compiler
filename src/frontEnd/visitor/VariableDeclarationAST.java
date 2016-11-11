package frontEnd.visitor;

public class VariableDeclarationAST extends AST {

  private Type t1;
  private String name;
  private Type t2;

  public VariableDeclarationAST(Type t1, String name, Type t2) {
    this.t1 = t1;
    this.name = name;
    this.t2 = t2;
  }

  @Override
  public Object check() {
    if (!(t1.equals(t2))) {
      System.err.println("Type mismatch error in variable: " + name + "\nExpected: " + t1.toString() + "\nActual: " + t2.toString());
      System.exit(200);
    }
    return null;
  }
}
