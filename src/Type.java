import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;

/**
 * Created by aa14415 on 01/11/16.
 */
public class Type {

    private BasicParser bp;
    private String type;

    public Type(String type) {
        this.type = type;
    }

    String getType() {
        return bp.type().toString();
    }

}
