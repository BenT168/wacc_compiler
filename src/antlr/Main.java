package antlr;

import org.antlr.runtime.TokenSource;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by aa14415 on 01/11/16.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Error: One Argument Should be given.");
            System.exit(-1);
        }

        File file = new File(args[0]);
        if(!file.exists()) {
            System.out.println("Error: File inputed does not exist.");
            System.exit(-1);
        }

        FileInputStream fis = null;

        BasicParserBaseVisitor baseVisitor = new BasicParserBaseVisitor();

        try {
            fis = new FileInputStream(file);
            CharStream input = new ANTLRInputStream(fis);
            BasicLexer lexer = new BasicLexer(input);
            CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
            BasicParser parser = new BasicParser((TokenStream) tokens);
            BasicParser.ProgramContext tree = parser.program();

            fis.close();

            //ParseTreeWalker walker = new ParseTreeWalker();
            //walker.walk(baseVisitor, tree);
            //baseVisitor.visit(tree);

        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");

        }
    }
}
