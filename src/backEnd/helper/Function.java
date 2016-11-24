package backEnd.helper;

import java.util.ArrayList;

public class Function {

    private ArrayList<Variable> variables;
    private String name;
    private int paramCount;
    private int offsetFromParameters = 0;

    public Function(String name) {
        this.name = name;
        this.variables = new ArrayList<Variable>(2000);
        paramCount =  0;
    }

    public String getName() {
        return name;
    }

    public Variable getVariable(String name){
        for(int i = 0; i < variables.size(); i++ ){
            if(variables.get(i).getName().equals(name)){
                return variables.get(i);
            }
        }
        return null;

    }

    public void setScopeValueIfIdentPresent(int variableCount, String ident, int beginCount) {
        for (int i = 0; i < variableCount-1; i++) {
            if (variables.get(i).getName().equals(ident) && variables.get(i).getScopeCount() == 0) {
                variables.get(i).setScopeCount(beginCount);
                break;
            }
        }
    }

    public int getCumulativeVariableOffset(int indexOfVar) {
        int count = offsetFromParameters;
        if (indexOfVar > paramCount && indexOfVar == variables.size()-1) {
            return count;
        } else if (indexOfVar < paramCount){
            count += 4;
        }
        for (int i = indexOfVar+1; i < variables.size(); i++) {
            count += variables.get(i).getOffset();
        }
        return count;
    }

    public int getCorrectVariable(String ident) {
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getName().equals(ident) && variables.get(i).getScopeCount() == 0) {
                return i;
            }
        }
        return -1;
    }

    public void resetScopeCounts(int beginCount) {
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getScopeCount() == beginCount) {
                variables.get(i).setScopeCount(0);
            }
        }
    }

    public Variable getVariableUsingIndex(int index) {
        return variables.get(index);
    }

    public int getTotalOffset() {
        int count = 0;
        for (int i = paramCount; i < variables.size(); i++) {
            count += variables.get(i).getOffset();
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Function) {
            Function that = (Function)(o);
            if (name.equals(that.name)) {
                return true;
            } else {
                return false;
            }
        } else if (o instanceof String){
            if (name.equals(o)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addVariable(Variable v) {
        variables.add(v);
    }

    public void setParamCount(int paramCount) {
        this.paramCount = paramCount;
    }
}

