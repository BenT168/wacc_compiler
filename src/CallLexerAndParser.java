import antlr.WACCLexer;
import antlr.WACCParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CallLexerAndParser {

    public static WACCParser start(FileInputStream fis, File file) throws IOException {
        CharStream input = new ANTLRInputStream(fis);
        WACCLexer lexer = new WACCLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new WACCParser(tokens);
    }
}
