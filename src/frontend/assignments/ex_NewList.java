package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovRegToken;
import frontend.expressions.ExprNode;
import frontend.statements.ex_listObject;
import frontend.type.BaseType;
import frontend.type.ListType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class ex_NewList extends Assignable {

    private BaseType type;
    private ex_listObject listObject;
    private final int MEMORY_SIZE = 1000;

    public ex_NewList(BaseType type, ex_listObject listObject) {
        this.listObject = listObject;
        this.type = type;
    }

    public ex_listObject getListObject() {
        return listObject;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        return true;
    }

    @Override
    public BaseType getType() {
        return new ListType(type);
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq firstAlloc = mallocSequence(1, MEMORY_SIZE);
        MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
        firstAlloc
                .append(movReg1);
        return firstAlloc;
    }
}
