package frontEnd.tree;

import frontEnd.tree.Function.FunctionDec;
import frontEnd.tree.Stat.Stat;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.List;

public class Program extends ASTTree {
   
    private List<FunctionDec> functions;
    private Stat body;

    public Program(List<FunctionDec> functions, Stat body) {
        this.functions = functions;
        this.body = body;
    }

    public BaseType getType() {
	throw new UnsupportedOperationException("getType() is not supported in ProgNode");
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
        return true;
    }
}
