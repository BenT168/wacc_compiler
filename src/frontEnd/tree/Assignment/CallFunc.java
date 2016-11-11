package frontEnd.tree.Assignment;

import frontEnd.ErrorHandling.IllegalCallException;
import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.ErrorHandling.UndeclaredIdentifierException;
import frontEnd.tree.Function.FunctionDec;
import frontEnd.tree.Parameter.ParamList;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

public class CallFunc extends Assignment {

    private FunctionDec func;
    private ArgList args;
    private String ident;
    private BaseType retType;

    public CallFunc(FunctionDec func, ArgList args) {
        this.func = func;
        this.args = args;
        this.ident = func.getFuncName();
        this.retType = func.getType();
    }

    @Override
    public boolean check(SymbolTable symbolTable, ParserRuleContext ctx) {
      if (!symbolTable.containsRecursive(ident)) {
			new UndeclaredIdentifierException("Function " + ident + " has not been delcared", ctx);
			return false;
		}
		ParamList params = func.getParams();
		if(!(args.size() == params.size())) {
			new IllegalCallException("The function call to " + ident + " has an incorrect number of arguments", ctx);
			return false;
		}
		if(!(args.compareToParamList(params))) {
			new IncompatibleTypesException("Arguments in call to " + ident + " have incorrect types.", ctx);
			return false;
		}
		return true;
    }

    public BaseType getType() {
	return retType;
    }



}
