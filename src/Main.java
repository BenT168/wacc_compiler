import antlr.BasicLexer;
import antlr.BasicParser;
import frontEnd.myVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseCancellationException{

        /* Check if Argument is given, throw error if not the right number
         */
        if(args.length != 1) {
            System.out.println("Error: One Argument Should be given.");
            System.exit(-1);
        }

        File file = new File(args[0]);

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

            System.out.println(tree.toStringTree(parser));

            /*Check if there are any Syntatic errors
             */
            int syntaxErr = parser.getNumberOfSyntaxErrors();
            if(syntaxErr > 0) {
                System.exit(100);
            }

            myVisitor visitor = new myVisitor();

            String answer = visitor.visit(tree);
            //System.out.println(answer);

            visitor.checkVariablesAreAdded();


            //TODO: Check if semantic error and exit with code 200

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");
        }
    }
}
