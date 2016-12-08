package frontend.statements;

import frontend.expressions.ExprNode;

import java.util.HashMap;
import java.util.Map;

public class ex_mapObject {

    private Map<ExprNode, ExprNode> mapElems = new HashMap<>();
    private int offset = 0;
    private int var_offset = 0;

    /*
   Method that returns mapElems
    */
    public Map<ExprNode, ExprNode> getMapElems() {
        return mapElems;
    }

    /*
    Method to add exprnode to map when add elem called
     */
    public void add(ExprNode exprNode1, ExprNode exprNode2) {
        // when exprnode is added, pointer is moved to store the next elem at the right place
        offset++;
        var_offset = offset * (exprNode1.getType().getVarSize() + exprNode2.getType().getVarSize());
        // add elem to end of list
        mapElems.put(exprNode1, exprNode2);
    }

    /*
    Method returns var offset
     */
    public int getVar_offset() {
        return var_offset;
    }
}
