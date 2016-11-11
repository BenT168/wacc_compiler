package frontEnd.visitor;

public class ReadAST extends AST {

  private Type t;

  public ReadAST(Type t) {
    this.t = t;
  }

  @Override
  public Object check() {
    Type temp1 = new BaseType(BaseTypeCode.CHAR);
    Type temp2 = new BaseType(BaseTypeCode.INT);
    if (!(t.equals(temp1) || t.equals(temp2))) {
      System.err.println("In 'Read' statement:\nExpecting type: " + temp1.toString()
              + " or " + temp2.toString() + "\nActual type: " + t.toString());
      System.exit(200);
    }
    return null;
  }
}
