package frontEnd.visitor;

public class ExitAST extends AST {
  private Type t;

  public ExitAST(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    Type temp = new BaseType(BaseTypeCode.INT);
    if (!(t.equals(temp))) {
      System.err.println("In 'Exit' statement:\nExpecting type: " + temp.toString() + "\nActual: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
