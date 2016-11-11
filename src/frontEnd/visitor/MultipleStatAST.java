package frontEnd.visitor;

import antlr.WACCParser;
import java.util.List;

public class MultipleStatAST extends AST {
  private List<WACCParser.StatContext> stat;

  public MultipleStatAST(List<WACCParser.StatContext> stat) {
    this.stat = stat;
  }

  @Override
  public Object check() {
    boolean seenReturn = false;
    int pos = 0;

    // looking up return statements
    for(int i = 0; i < stat.size(); i++) {
      if(stat.getText().matches("return(.*)")) {
        seenReturn = true;
        pos = i;
      }
    }

    // if in the top-level scope there is any statement past the return statement
    // then that should cause an error
    if(seenReturn && pos != stat.size() - 1) {
      System.err.print("Statement after return. Unreachable statement.");
      System.exit(200);
    }
    return null;
  }
}
