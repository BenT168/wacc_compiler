package frontEnd.tree.AST;

import frontEnd.tree.Type.Identifier;
import frontEnd.tree.Type.Type;
import frontEnd.tree.Type.Variable;

public class VariableDeclAST extends AST {

    private String typeName;

    public VariableDeclAST(String typeName, String varname, Variable variable) {
        this.typeName = typeName;
        varName = varname;
        this.variable = variable;

    }

    @Override
    public void check() {
        Identifier varFromAll = ST.lookUpAll(typeName);
        Identifier var = ST.lookUp(varName);

        if(varFromAll == null) {
            semanticError.semanticErrorCase(typeName, "unknownType");
        } else if(! (varFromAll instanceof Type)) {
            semanticError.semanticErrorCase(typeName, "notAType");
        } else if(var == null) {
            semanticError.semanticErrorCase(varName, "alreadyDeclared");
        } else {
            variable = new Variable(varFromAll);
            ST.add(varName, variable);
        }
    }
}