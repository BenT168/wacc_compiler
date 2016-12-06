package frontend.statements;

import frontend.expressions.ExprNode;

import java.util.HashMap;
import java.util.Map;

public class ex_mapObject {

    private Map<ExprNode, ExprNode> mapElems = new HashMap<>();
    private int offset = 0;
    private int var_offset = 0;

    public Map<ExprNode, ExprNode> getMapElems() {
        return mapElems;
    }

    public void add(ExprNode exprNode1, ExprNode exprNode2) {
        offset++;
        var_offset = offset * (exprNode1.getType().getVarSize() + exprNode2.getType().getVarSize());
        mapElems.put(exprNode1, exprNode2);
    }

    public int getVar_offset() {
        return var_offset;
    }
}
