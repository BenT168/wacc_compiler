package frontend.statements;

import frontend.expressions.ExprNode;

import java.util.LinkedList;

public class ex_listObject {

    private LinkedList<ExprNode> listElems = new LinkedList<>();
    private int offset = 0;
    private int var_offset = 0;

    /*
    Method that returns listElems
     */
    public LinkedList<ExprNode> getListElems() {
        return listElems;
    }

    /*
    Method to add exprnode to list when add elem called
     */
    public void add(ExprNode exprNode) {
        // when exprnode is added, pointer is moved to store the next elem at the right place
        offset++;
        var_offset = offset * exprNode.getType().getVarSize();
        // add elem to end of list
        listElems.addLast(exprNode);
    }

    /*
    Method returns var offset
     */
    public int getVar_offset() {
        return var_offset;
    }
}
