package backEnd.helper;

import antlr.WACCParser;

public class Variable {

    private String name;
    private int offset;
    private WACCParser.TypeContext type;
    private int scopeCount;

    public Variable(String name, int offset, WACCParser.TypeContext type) {
        this.name = name;
        this.offset = offset;
        this.type = type;
        scopeCount = 0;
    }

    public WACCParser.TypeContext getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setScopeCount(int beginCount) {
        this.scopeCount = beginCount;
    }

    public int getScopeCount() {
        return scopeCount;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) {
            Variable v = (Variable)o;
            return name.equals(v.getName());
        } else {
            return false;
        }
    }

}
