import antlr.WACCLexer;
import antlr.WACCParser;
import frontEnd.visitor.TypeCheckVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputFile = null;
        if (args.length > 0) {
            inputFile = args[0];
        }
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }


        ANTLRInputStream input = new ANTLRInputStream(is);

        /* Create a lexer that feeds off of input CharStream */
        WACCLexer lexer = new WACCLexer(input);

        /* Create a buffer of tokens pulled from the lexer */
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        /* Create a parser that feeds off the tokens buffer */
        WACCParser parser = new WACCParser(tokens);

        /* Generate AST, begin parsing at the program rule */
        WACCParser.ProgramContext tree = parser.program();

        /* Checking Syntax */
        if (parser.getNumberOfSyntaxErrors() == 0) {

            /* Checking Semantics */
            try {
                System.out.println("The visitor visits every nodes of AST");
                TypeCheckVisitor visitor = new TypeCheckVisitor();
                System.out.println("====");
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    System.exit(0);
                } else {
                    System.exit(200);
                }
            }
            /*!visitor.semanticError*/

        } else {
            System.out.println("SYNTAX ERROR: "
                    + parser.getNumberOfSyntaxErrors());

            /* A compilation that fails due to one or
                more syntax errors should return the exit status 100 */
            System.exit(100);
        }

        /* A successful compilation should return the exit status 0 */
        System.exit(0);
    }
}




        /* Check if Argument is given, throw error if not the right number
         */

        /*
        if(args.length != 2) {
            System.out.println("Error: One Argument Should be given.");
            System.exit(-1);
        }

        File file = new File(args[1]);

        /* Check if file exists
         */
        /*
        if(!file.exists()) {
            System.out.println("Error: File inputed does not exist.");
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


            /*Check if there are any Syntatic errors
             */
        /*
            int syntaxErr = parser.getNumberOfSyntaxErrors();
            if(syntaxErr > 0) {
                System.exit(100);
            }

            TypeCheckVisitor visitor = new TypeCheckVisitor();
            visitor.visit(tree);

            fis.close();

        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");
        } catch (NullPointerException ee) {
            System.exit(0);
        }
    }
}
*/