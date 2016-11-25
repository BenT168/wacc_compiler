import antlr.WACCLexer;
import antlr.WACCParser;
import frontend.exception.SemanticErrorException;
import frontend.exception.SyntaxErrorException;
import main.TypeCheckVisitor;
import main.WACCCompiler;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {

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

		// Parse the flags given in the command line arguments
		CommandLine cmd = parseFlags(args);

		// We set the logging options as appropriate
		TypeCheckVisitor.dbh.setOptions(cmd);

		// Get WACC source File
		String waccFilePath = cmd.getOptionValue("f");
		InputStream waccInput = (waccFilePath != null) ? new FileInputStream(waccFilePath) : System.in;
		WACCParser parser = null;
		ParseTree tree = null;

		try {
			ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);

			WACCLexer lexer = new WACCLexer(antrlInput);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			try {
				/* Create Parser from Tokens */
				parser = new WACCParser(tokens);
				/*Start parsing from program */
				tree = parser.program();
			} catch (Exception e) {
				System.err.println(e.toString());
				System.exit(200);
			}

			/* Check if there are any Syntax errors */
			if(parser.getNumberOfSyntaxErrors() > 0) {
				System.exit(100);
			}

			/* Visit the tree */
			TypeCheckVisitor visitor = new TypeCheckVisitor(tree);
			visitor.init();

			/* Check if the semantic checking will terminate */
			if ( !visitor.terminate() ) {
				System.exit(200);
			}

			WACCCompiler compiler = new WACCCompiler(visitor.getProgTree());
			compiler.init();
			String compilerOutput = compiler.toString();
			if (cmd.hasOption("s")) {
				System.out.println(compilerOutput);
			} else {
				createAssemblyFile(compiler.toString(), waccFilePath);
			}

		} catch (IOException e) {
			throw e;
		} catch (SyntaxErrorException e) {
			System.err.println(e.toString());
			System.exit(100);
		} catch (SemanticErrorException e) {
			System.err.println(e.toString());
			System.exit(200);
		}

	}

	private static void createAssemblyFile(String assemblyString, String waccFilePath) {
		// Extract the path from the waccFilePath string
		Path p = Paths.get(waccFilePath);
		// Get the filename and replace the extension
		String assemblyFilename = p.getFileName().toString().replace(".wacc", ".s");
		// Write to file
		try {
	          File file = new File(assemblyFilename);
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write(assemblyString);
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }
	}


	private static CommandLine parseFlags(String[] args) {
		// create Options object
		Options options = new Options();
		// add v option
		options.addOption("v", false, "verbose");
		options.addOption("d", false, "debug mode");
		options.addOption("f", true, "source file");
		options.addOption("s", false, "force printing assembly to std-out");

		CommandLineParser flagsParser = new PosixParser();
		CommandLine cmd = null;
		try {
			 cmd = flagsParser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("There were problems parsing the flags");
			System.err.println(e.toString());
		}
		return cmd;
	}

}
