import antlr.BasicLexer;
import antlr.BasicParser;
import frontEnd.visitor.TypeCheckVisitor;
import frontEnd.visitor.myVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseCancellationException{

        /* Check if Argument is given, throw error if not the right number
         */
        if(args.length != 2) {
            System.out.println("Error: One Argument Should be given.");
            System.exit(-1);
        }

        File file = new File(args[1]);

        /* Check if file exists
         */
        if(!file.exists()) {
            System.out.println("Error: File inputed does not exist.");
            System.exit(-1);
        }

        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
            CharStream input = new ANTLRInputStream(fis);
            BasicLexer lexer = new BasicLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            BasicParser parser = new BasicParser(tokens);
            ParseTree tree = parser.program();

            //System.out.println(tree.toStringTree(parser));

            /*Check if there are any Syntatic errors
             */
            int syntaxErr = parser.getNumberOfSyntaxErrors();
            if(syntaxErr > 0) {
                System.exit(100);
            }

            TypeCheckVisitor visitor = new TypeCheckVisitor();

            visitor.visit(tree);
            //System.out.println(answer);

            //visitor.checkVariablesAreAdded();

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");
        }
    }
}
