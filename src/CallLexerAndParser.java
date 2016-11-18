import antlr.WACCLexer;
import antlr.WACCParser;
import frontEnd.exception.MyErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CallLexerAndParser {

    public static WACCParser start(FileInputStream fis, File file) throws IOException {
        CharStream input = new ANTLRInputStream(fis);
        WACCLexer lexer = new WACCLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(MyErrorListener.INSTANCE);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new WACCParser(tokens);
    }
}
