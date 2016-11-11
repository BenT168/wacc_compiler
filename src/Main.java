import antlr.WACCLexer;
import antlr.WACCParser;
import frontEnd.ErrorHandling.Exception;
import frontEnd.ErrorHandling.IntOverflowException;
import frontEnd.ErrorHandling.UnresolvedExpectationException;
import frontEnd.visitor.myVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException{


		CommandLine cmd = parseFlags(args);
		
		// We set the logging options as appropriate
	   // myVisitor.semanticError.setOptions(cmd);
		
		// Get WACC source File
	    String filePath = cmd.getOptionValue("f");
            InputStream input = getInputStream(filePath);	
	    WACCLexer lexer = getLexer(input);
            CommonTokenStream tokens = tokenise(lexer);
            WACCParser parser = new WACCParser(tokens);
            ParseTree tree = getParseTree(tokens);

            //System.out.println(tree.toStringTree(parser));

            /*Check if there are any Syntatic errors
             */

            myVisitor visitor = checkSemanticIntegrity(tree);

            //System.out.println(answer);

            //visitor.checkVariablesAreAdded();

            if ( !visitor.terminate() ) {
                exitSemanticFailure();
            }

    }

    private static WACCLexer getLexer(InputStream input) throws IOException {
        // Create ANTLR Input stream
        WACCLexer lexer = null;
        try {
            ANTLRInputStream antrlInput = new ANTLRInputStream(input);
            lexer = new WACCLexer(antrlInput);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            exitSyntaxError();
        }
        return lexer;
    }

    private static CommonTokenStream tokenise(WACCLexer lexer) {
        CommonTokenStream tokens = null;
        try {
            tokens = new CommonTokenStream(lexer);
        }
        catch (Exception e) {
            e.printStackTrace();
            exitSemanticFailure();
        }
        return tokens;
    }

    private static myVisitor checkSemanticIntegrity(ParseTree tree) {
        myVisitor semantic = new myVisitor(tree);
        try {
            semantic.init();
        } catch (UnresolvedExpectationException e) {
            e.printStackTrace();
            exitSyntaxError();
        } catch (IntOverflowException e) {
            e.printStackTrace();
            exitSyntaxError();
        } catch (Exception e) {
            e.printStackTrace();
            exitSemanticFailure();
        }
        return semantic;
    }

    private static ParseTree getParseTree(CommonTokenStream tokens) {
        WACCParser parser = null;
        ParseTree tree = null;
        try {
            // Create Parser from Tokens
            parser = new WACCParser(tokens);
            // Set tree to null for the moment
            tree = parser.program();
        } catch (Exception e) {
            e.printStackTrace();
            exitSyntaxError();
        }

        if(parser.getNumberOfSyntaxErrors() > 0) {
            exitSyntaxError();
        }
        return tree;
    }

    private static InputStream getInputStream(String waccFilePath)
            throws FileNotFoundException {
        // Initialise InputStream to the file or System.in if null.
        InputStream waccInput;
        if(waccFilePath != null)
            waccInput = new FileInputStream(waccFilePath);
        else
            waccInput = System.in;
        return waccInput;
    }
    
    private static CommandLine parseFlags(String[] args) {
		// create Options object
		Options options = new Options();
		// add v option
		options.addOption("v", false, "verbose");
		options.addOption("d", false, "debug mode");
		options.addOption("f", true, "source file");
		
		CommandLineParser flagsParser = (CommandLineParser) new PatternOptionBuilder();
		CommandLine cmd = null;
		try {
			 cmd = flagsParser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("There were problems parsing the flags");
			e.printStackTrace();
		}
		return cmd;
	}

    private static void exitSyntaxError() {
        System.exit(100);
    }

    private static void exitSemanticFailure() {
        System.exit(200);
    }
}
