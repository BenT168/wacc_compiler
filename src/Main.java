import antlr.WACCLexer;
import antlr.WACCParser;
import frontEnd.TypeCheckVisitor;
import frontEnd.exception.SemanticException;
import frontEnd.exception.SyntaxException;
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
            System.out.println("Error: File input does not exist.");
            System.exit(-1);
        }

        FileInputStream fis;

        try {
            fis = new FileInputStream(file);
            CharStream input = new ANTLRInputStream(fis);
            WACCLexer lexer = new WACCLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            WACCParser parser = new WACCParser(tokens);
            ParseTree tree = parser.program();


            /*Check if there are any Syntax errors
             */
            int syntaxErr = parser.getNumberOfSyntaxErrors();
            if(syntaxErr > 0) {
                System.out.println("SYNTAX ERROR: "
                        + parser.getNumberOfSyntaxErrors());
                System.exit(100);
            } else {

                /*Check if there are any Semantic errors*/
                try {
                    System.out.println("The visitor visits every nodes of AST");
                    TypeCheckVisitor visitor = new TypeCheckVisitor();
                    visitor.visit(tree);
                    System.out.println("====");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    if (e instanceof SemanticException) {
                        System.exit(200);
                    } else if (e instanceof SyntaxException) {
                        System.exit(100);
                    }
                }
            }

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");
        } catch (NullPointerException ee) {
            System.exit(0);
        }

        /* A successful compilation should return the exit status 0 */
        System.exit(0);
    }
}

