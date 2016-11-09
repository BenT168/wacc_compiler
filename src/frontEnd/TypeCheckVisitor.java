package frontEnd;

import frontEnd.antlr.BasicParser;
import frontEnd.antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class TypeCheckVisitor extends BasicParserBaseVisitor<BasicParser.TypeContext> {

    @Override
    public BasicParser.TypeContext visitIntLiter(@NotNull BasicParser.IntLiterContext ctx) {
        return new BasicParser.TypeContext();
    }
}
