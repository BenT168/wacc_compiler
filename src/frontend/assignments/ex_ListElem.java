package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.check.CheckArrayBoundsToken;
import backend.tokens.load.LoadAddressToken;
import backend.tokens.move.MovRegToken;
import backend.tokens.operator.AddImmToken;
import backend.tokens.operator.AddToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.expressions.Variable;
import frontend.type.BaseType;
import frontend.type.ListType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;
import java.util.LinkedList;

public class ex_ListElem extends ExprNode implements  AssignLHS {

    private Variable var;
    private ExprNode expr;
    private ListType listType;

    public ex_ListElem(ExprNode expr, Variable var) {
        this.expr = expr;
        this.listType = (ListType) var.getType();
        this.var = var;
    }

    /*
     Method checks that expr type is an int
     */
    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx ) {
        if(!(expr.getType() == BaseType.INT)) {
            throw new SemanticErrorException("List position can only be found using an IntLeaf", ctx);
        }
        return true;
    }

    /*
    Method returns list type of type
     */
    @Override
    public BaseType getType() {
        BaseType type = listType;
        type = ((ListType) type).getBaseType();
        return type;
    }

    /*
    Method generates ARM code for a list elem
     */
    @Override
    public TokSeq assemblyCodeStoring(Register dest) {
        return listElemCommonAssembly(dest.getNext())
                .append(listType.getBaseType().storeAssembly(dest.getNext(), dest));
    }


    /*
    Methods used for generating ARM code
     */
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq out = new TokSeq();
        TokSeq listAccess = listElemCommonAssembly(dest);
        LoadAddressToken loadResult = listType.getBaseType().loadAssembly(dest, dest);
        out
                .appendAll(listAccess)
                .append(loadResult);

        return out;
    }

    /* private Method which takes the bulk of generating ARM code
     */
    private TokSeq listElemCommonAssembly(Register dest) {
        int posOnStack = var.getPosition().getStackIndex();
        //Creates token sequence to add instructions
        TokSeq out = new TokSeq();

        AddImmToken addTok = new AddImmToken(dest, Register.sp, Integer.toString(posOnStack));
        out.append(addTok);

        //Generate ARM code for expr
        TokSeq exprSeq = expr.assemblyCodeGenerating(dest.getNext());
        exprSeq.appendAll( new TokSeq(
                new LoadAddressToken(dest, dest),
                new MovRegToken(Register.R0, dest.getNext()),
                new MovRegToken(Register.R1, dest),
                new AddImmToken(dest, dest, Integer.toString(4))));

        //Check that expr is an int
        if(listType.getBaseType().getVarSize() == BaseType.INT.getVarSize()) {
            exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
        } else {
            exprSeq.append(new AddToken(dest, dest, dest.getNext()));
        }
        out.appendAll(exprSeq);

        return out;
    }

    @Override
    public TokSeq loadAddr(Register dest) {
        TokSeq seq = var.assemblyCodeGenerating(dest);
        TokSeq listIndex = expr.assemblyCodeGenerating(dest.getNext());
        seq.appendAll(listIndex);
        seq.appendAll(
                new LoadAddressToken(dest, dest),
                new AddToken(dest, dest, dest.getNext()));
        return seq;
    }


    public String getIdent() {
        return var.getIdent();
    }

    /*
    Methods returns max weight of expr
     */
    @Override
    public int weight() {
        int max = 0;
        int exprWeight = expr.weight();
        max = exprWeight > max ? exprWeight : max;
        return max;
    }



}
