import antlr.WACCParser;
import frontEnd.TypeCheckVisitor;
import frontEnd.exception.MyErrorListener;
import frontEnd.exception.SemanticException;
import frontEnd.exception.SyntaxException;
import frontEnd.exception.ThrowException;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

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

            /*Get Parser after lexing */
            WACCParser parser = CallLexerAndParser.start(fis, file);

            /* Add my listener so error message prints out "Syntax Error ..."*/
            parser.removeErrorListeners();
            parser.addErrorListener(MyErrorListener.INSTANCE);

            /*Start parsing from program */
            ParseTree tree = parser.program();


            /*Check if there are any Syntax errors */
            int syntaxErr = parser.getNumberOfSyntaxErrors();
            if(syntaxErr > 0) {
               System.exit(100);
            } else {
                //Visit the tree
                TypeCheckVisitor visitor = new TypeCheckVisitor();
                visitor.visit(tree);
            }

            fis.close();

            /*Check what error has been thrown in ThrowException and exit with proper code*/
            if(ThrowException.semanticExceptionThrown) {
                System.exit(200);
            }
            if(ThrowException.syntaxExceptionThrown) {
                System.exit(100);
            }

        //Catching all the exceptions
        } catch (IOException e) {
            System.out.println("Error: InputStream does not work.");
        } catch (NullPointerException ee) {
            System.exit(0);
        } catch (SemanticException e) {
            System.err.println(e.getMessage());
            System.exit(200);
        } catch (SyntaxException e) {
            System.err.println(e.getMessage());
            System.exit(100);
        }


        /* A successful compilation should return the exit status 0 */
        System.exit(0);
    }
}

