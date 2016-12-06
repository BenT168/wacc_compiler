package frontend.statements;

import antlr.WACCParser;
import backend.Register;
import backend.TokSeq;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import frontend.type.ListType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_AddElemListNode extends StatNode {


    private ex_listObject listObject;
    private ExprNode exprNode;

    public ex_AddElemListNode(ex_listObject listObject, ExprNode exprNode) {
        this.listObject = listObject;
        this.exprNode = exprNode;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        WACCParser.AddElemListContext addctx = (WACCParser.AddElemListContext) ctx;
        BaseType type = st.get(addctx.ident().getText()).getType();
        ListType lType;
        if(type instanceof ListType) {
            lType = (ListType) type;
        } else {
            throw new SemanticErrorException("Cannot perform addList to variable that is not a List Type");
        }
        ListType listType = new ListType(exprNode.getType());
        if (listType.isCompatible(lType)) {
            return true;
        }
        throw new SemanticErrorException("Type of expression added to list: " + listType.toString()
                + " does not match type of list: " + type.toString());
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        TokSeq tokSeq = new TokSeq();
        tokSeq
                .appendAll(exprNode.assemblyCodeGenerating(register.getNext()))
                .appendAll(
                new StoreToken(register, register.getNext(), listObject.getVar_offset()));
        return tokSeq;
    }



}
