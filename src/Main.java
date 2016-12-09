import antlr.WACCLexer;
import antlr.WACCParser;
import backend.Optimiser;
import frontend.exception.SemanticErrorException;
import frontend.exception.SyntaxErrorException;
import main.CodeGenerator;
import main.TypeCheckVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		// Parse the flags given in the command line arguments
		CommandLine cmd = parseFlags(args);

		// We set the logging options as appropriate
		TypeCheckVisitor.dbh.setOptions(cmd);

		// Get WACC source File
		String waccFilePath = cmd.getOptionValue("f");
		InputStream waccInput = (waccFilePath != null) ? new FileInputStream(waccFilePath) : System.in;

		try {
			ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);

			WACCLexer lexer = new WACCLexer(antrlInput);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create Parser from Tokens
			WACCParser parser = new WACCParser(tokens);

			// Set Tree to null for the moment
			ParseTree tree = parser.program();

			if(parser.getNumberOfSyntaxErrors() > 0) {
				System.exit(100);
			}

			TypeCheckVisitor semantic = new TypeCheckVisitor(tree);
			semantic.init();

			// Check that the Error Listener has not recorded any exceptions
			if ( !semantic.terminate() ) {
				System.exit(200);
			}

			CodeGenerator codeGenerator = new CodeGenerator(semantic.getProgTree());
			codeGenerator.init();
			String compilerOutput = codeGenerator.toString();
			if (cmd.hasOption("s")) {
				System.out.println(compilerOutput);
			} else {
				createAssemblyFile(codeGenerator.toString(), waccFilePath);
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
			output.write(Optimiser.optimiser.optimise(assemblyString));
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
