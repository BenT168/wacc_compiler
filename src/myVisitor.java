import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import org.antlr.v4.runtime.misc.NotNull;


public class myVisitor extends BasicParserBaseVisitor<String> {

    @Override
    public String visitStat(@NotNull BasicParser.StatContext ctx) {
        int exitValue;
        switch(ctx.getText().substring(0,4)) {
            case "skip" :
            case "exit" :
                exitValue = Integer.parseInt(visitExpr(ctx.expr()));
                System.out.println(exitValue);
                System.exit(exitValue);
             break;
        }
        return visitChildren(ctx);
    }


    @Override
    public String visitExpr(@NotNull BasicParser.ExprContext ctx) {
        return ctx.getText();
    }

}
