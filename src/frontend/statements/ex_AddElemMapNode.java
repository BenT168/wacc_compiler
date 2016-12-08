package frontend.statements;


import antlr.WACCParser;
import backend.Register;
import backend.TokSeq;
import backend.tokens.store.StoreToken;
import frontend.exception.SemanticErrorException;
import frontend.expressions.ExprNode;
import frontend.type.BaseType;
import frontend.type.MapType;
import org.antlr.v4.runtime.ParserRuleContext;
import symboltable.SymbolTable;

public class ex_AddElemMapNode extends StatNode {

    private ex_mapObject mapObject;
    private ExprNode key;
    private ExprNode value;

    public ex_AddElemMapNode(ex_mapObject mapObject, ExprNode key, ExprNode value) {
        this.mapObject = mapObject;
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean check(SymbolTable st, ParserRuleContext ctx) {
        WACCParser.AddElemMapContext addctx = (WACCParser.AddElemMapContext) ctx;
        BaseType type = st.get(addctx.ident().getText()).getType();
        MapType mType;
        if(type instanceof MapType) {
            mType = (MapType) type;
        } else {
            throw new SemanticErrorException("Cannot perform addList to variable that is not a List Type");
        }
        MapType mapType = new MapType(key.getType(), value.getType());
        if (mapType.isCompatible(mType)) {
            return true;
        }
        throw new SemanticErrorException("Type of expression added to list: " + mapType.toString()
                + " does not match type of list: " + type.toString());
    }

    @Override
    public TokSeq assemblyCodeGenerating(Register register) {
        TokSeq tokSeq = new TokSeq();
        tokSeq
                .appendAll(key.assemblyCodeGenerating(register.getNext()))
                .appendAll(value.assemblyCodeGenerating(register.getNext()))
                .appendAll(
                        new StoreToken(register, register.getNext(), mapObject.getVar_offset()));
        return tokSeq;
    }


}
