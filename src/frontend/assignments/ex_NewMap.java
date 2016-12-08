package frontend.assignments;

import backend.Register;
import backend.TokSeq;
import backend.tokens.move.MovRegToken;
import frontend.expressions.ExprNode;
import frontend.statements.ex_mapObject;
import frontend.type.BaseType;
import frontend.type.MapType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

import java.util.LinkedList;

public class ex_NewMap extends Assignable {

    private ex_mapObject mapObject;
    private BaseType type1;
    private BaseType type2;
    private final int MAP_SIZE = 1000;

    public ex_NewMap(BaseType type1, BaseType type2, ex_mapObject mapObject) {
        this.type1 = type1;
        this.type2 = type2;
        this.mapObject = mapObject;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        return true;
    }

    /*
    Method returns map type of type1 and type 2
     */
    @Override
    public BaseType getType() {
        return new MapType(type1, type2);
    }

    /*
    Method generates ARM code for a new map declaration
     */
    @Override
    public TokSeq assemblyCodeGenerating(Register dest) {
        TokSeq firstAlloc = mallocSequence(2, MAP_SIZE);
        MovRegToken movReg1 = new MovRegToken(dest, Register.R0);
        firstAlloc
                .append(movReg1);
        return firstAlloc;
    }

    /*
   Method returns map object
    */
    public ex_mapObject getMapObject() {
        return mapObject;
    }
}
