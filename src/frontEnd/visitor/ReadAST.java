package frontEnd.visitor;

public class ReadAST extends AST {

  private Type t;

  public ReadAST(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    if (!(t.equals(new BaseType(BaseTypeCode.CHAR)) || t.equals(new BaseType(BaseTypeCode.INT)))) {
      System.err.println("In 'Read' statement:\nExpecting type: 'INT' or 'CHAR' in expression\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
