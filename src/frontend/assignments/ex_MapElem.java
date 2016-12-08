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
import frontend.type.MapType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class ex_MapElem extends ExprNode implements AssignLHS {

    private Variable var;
    private ExprNode expr;
    private MapType mapType;

    public ex_MapElem(ExprNode expr, Variable var) {
        this.expr = expr;
        this.mapType = (MapType) var.getType();
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

    @Override
    public BaseType getType() {
      return null;
    }

    /*
    Method Returns type of key
     */
    public BaseType getKey() {
        return mapType.getKey();
    }

    /*
    Method Returns type of value
     */
    public BaseType getValue() {
        return mapType.getValue();
    }


    /*
     Method generates ARM code for a map elem
     */
    @Override
    public TokSeq assemblyCodeStoring(Register dest) {
        return mapElemCommonAssembly(dest.getNext())
                .append(getKey().storeAssembly(dest.getNext(), dest))
                .append(getValue().storeAssembly(dest.getNext(), dest));

    }


    /*
    Methods used for generating ARM code
     */
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq out = new TokSeq();
        TokSeq mapAccess = mapElemCommonAssembly(dest);
        LoadAddressToken loadResultOne = getKey().loadAssembly(dest, dest);
        LoadAddressToken loadResultTwo = getValue().loadAssembly(dest, dest);
        out
                .appendAll(mapAccess)
                .append(loadResultOne)
                .append(loadResultTwo);


        return out;
    }

    /* private Method which takes the bulk of generating ARM code
    */
    private TokSeq mapElemCommonAssembly(Register dest) {
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
                new CheckArrayBoundsToken(),
                new AddImmToken(dest, dest, Integer.toString(4))));

        //Check that key is an int
        if(mapType.getKey().getVarSize() == BaseType.INT.getVarSize()) {
            exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
        }
        //Check that value is an int
        if(mapType.getValue().getVarSize() == BaseType.INT.getVarSize()) {
            exprSeq.append(new AddToken(dest, dest, dest.getNext(), "LSL #2"));
        } else if(getKey().getVarSize() != BaseType.INT.getVarSize() &&
                getValue().getVarSize() != BaseType.INT.getVarSize()) {
            exprSeq.append(new AddToken(dest, dest, dest.getNext()));
        }
        out.appendAll(exprSeq);

        return out;
    }

    @Override
    public TokSeq loadAddr(Register dest) {
        TokSeq seq = var.assemblyCodeGenerating(dest);
        TokSeq mapIndex = expr.assemblyCodeGenerating(dest.getNext());
        seq.appendAll(mapIndex);
        seq.appendAll(
                new LoadAddressToken(dest, dest),
                new AddToken(dest, dest, dest.getNext()));
        return seq;
    }


    public String getIdent() {
        return var.getIdent();
    }

    @Override
    public int weight() {
        int max = 0;
        int exprWeight = expr.weight();
        max = exprWeight > max ? exprWeight : max;
        return max;
    }


}
