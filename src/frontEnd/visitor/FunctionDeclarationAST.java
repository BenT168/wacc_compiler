package frontEnd.visitor;

import antlr.WACCParser;

public class FunctionDeclarationAST extends AST {
 
  // Function return type
  private Type defined;
  // Actual return type
  private Type actual;
  private WACCParser.FuncContext ctx;

  public FunctionDeclarationAST(Type defined, Type actual, WACCParser.FuncContext ctx) {
    this.defined = defined;
    this.actual = actual;
    this.ctx = ctx;
  }

  @Override
  public Object check() {
      if(!(defined.equals(actual))) {
        System.err.print("Function: " + ctx.ident().getText()
            + " \nExpected return type: " + defined.toString() + " \nActual return type: " + actual.toString());
        System.exit(200);
      }
    return null;
  }
}
