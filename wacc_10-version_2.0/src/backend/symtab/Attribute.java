package backend.symtab;

import backend.data.Variable;
import frontend.type.Type;

/**
 * Stores attributes for variables in our symbol table.
 */
public class Attribute {
    private Variable variable;
    private boolean isSourceVariable;
    private Type type;

    public Attribute(Variable variable, boolean isSourceVariable, Type type) {
        this.variable = variable;
        this.isSourceVariable = isSourceVariable;
        this.type = type;
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean isSourceVariable() {
        return isSourceVariable;
    }

    public Type getType() {
        return type;
    }
}
