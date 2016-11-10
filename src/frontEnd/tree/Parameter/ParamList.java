package frontEnd.tree.Parameter;

import frontEnd.ErrorHandling.Exception;
import frontEnd.ErrorHandling.IncompatibleTypesException;
import frontEnd.tree.ASTTree;
import frontEnd.tree.Type.BaseType;
import org.antlr.v4.runtime.ParserRuleContext;
import symbolTable.SymbolTable;

import java.util.ArrayList;
import java.util.Iterator;

public class ParamList extends ASTTree implements Iterable<Parameter> {

    ArrayList<Parameter> params;
	
    public ParamList() {
	this.params = new ArrayList<>();
    }
	
    public void add(Parameter pn) {
	params.add(pn);
    }
	
    public Parameter get(int i) {
	return params.get(i);
    }
	
    public int size() {
	return params.size();
    }
	
    @Override
    public boolean equals(Object other) {
	if(other instanceof ParamList) {
		ParamList pln = (ParamList) other;
		if(pln.size() != params.size()) {
			return false;
		}
		for (int i = 0; i < this.size(); i++) {
			if ( !this.get(i).equals(pln.get(i)) ) {
				return false;
			}
		}
		return true;
	}
	return false;
    }

    @Override
    public Iterator<Parameter> iterator() {
	return params.iterator();
    }

    public boolean check(SymbolTable st, ParserRuleContext ctx) {
	ArrayList<String> paramIdents = new ArrayList<String>();
	//Check whether there are duplicate arguments
	for (Parameter param : params) {
		if (paramIdents.contains(param.getIdent())) {
			new Exception(
			"A variable with identifier " + param.getIdent() + " was already declared", ctx);
			return false;
		}	
		}
		return true;
	}

	public BaseType getType() {
		throw new IncompatibleTypesException("ParamListNode has no type.");
	}
}
