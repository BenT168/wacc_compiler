package frontend.statements;

import frontend.expressions.ExprNode;

import java.util.LinkedList;

public class ex_listObject {

    private LinkedList<ExprNode> listElems = new LinkedList<>();
    private int offset = 0;
    private int var_offset = 0;

    public LinkedList<ExprNode> getListElems() {
        return listElems;
    }

    public void add(ExprNode exprNode) {
        offset++;
        var_offset = offset * exprNode.getType().getVarSize();
        listElems.addLast(exprNode);
    }

    public int getVar_offset() {
        return var_offset;
    }
}
