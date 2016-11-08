import frontEnd.tree.Identifier;
import frontEnd.tree.Type.Type;
import symbolTable.SymbolTable;

import java.lang.reflect.Parameter;

public class Function extends Identifier {
  private Type returnType;
  private Parameter parameters[];
  private SymbolTable symtab;
}
