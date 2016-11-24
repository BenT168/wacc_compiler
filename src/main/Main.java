package main;

import WACCExceptions.IntOverflowException;
import WACCExceptions.UnresolvedExpectationException;
import antlr.WACCLexer;
import antlr.WACCParser;
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
		InputStream waccInput = getInputStream(waccFilePath);
		
		WACCLexer lexer = getLexer(waccInput);
		
		// Tokenise the input stream
		CommonTokenStream tokens = tokenise(lexer);
		
		// Build the tokens AST
		ParseTree tree = getParseTree(tokens);
		
		// Check for semantic errors
		TypeCheckVisitor sc = checkSemanticIntegrity(tree);
		
		// Check that the Error Listener has not recorded any exceptions
		if ( !sc.terminate() ) {
			exitSemanticFailure();
		}
		
		WACCCompiler compiler = new WACCCompiler(sc.getProgTree());
		compiler.init();
		String compilerOutput = compiler.toString();
		if (cmd.hasOption("s")) {
			System.out.println(compilerOutput);
		} else {
			createAssemblyFile(compiler.toString(), waccFilePath);
		}
	}

	/** Utility method to extract the assembly filename and write to file
	 * @param assemblyString A String containing the assembly output
	 * @param waccFilePath The String containing the path of the source file. It works with just the filename.
	 */
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

	/**
	 * @param waccInput
	 * 		The InputStream containing the WACC program
	 * @return
	 * 		A WACCLexer
	 * @throws IOException
	 */
	
	private static WACCLexer getLexer(InputStream waccInput) throws IOException {
		// Create ANTLR Input stream
		WACCLexer lexer = null;
		try {
			ANTLRInputStream antrlInput = new ANTLRInputStream(waccInput);
			lexer = new WACCLexer(antrlInput);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			System.err.println(e.toString());
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
			System.err.println(e.toString());
			exitSemanticFailure();
		}
		return tokens;
	}

	/**
	 * @param tree
	 * 		An ANTLR.ParseTree that represents a WACC program
	 * @return
	 * 		The Semantic Checker object
	 */
	private static TypeCheckVisitor checkSemanticIntegrity(ParseTree tree) {
		TypeCheckVisitor semantic = new TypeCheckVisitor(tree);
		try {
			semantic.init();
		} catch (UnresolvedExpectationException e) {
			System.err.println(e.toString());
			exitSyntaxError();
		} catch (IntOverflowException e) {
			System.err.println(e.toString());
			exitSyntaxError();
		} catch (Exception e) {
			System.err.println(e.toString());
			exitSemanticFailure();
		}
		return semantic;
	}

	/**
	 * @param tokens
	 * 		The tokens of the WACC program
	 * @return
	 * 		An ANTLR AST representing the program
	 */
	private static ParseTree getParseTree(CommonTokenStream tokens) {
		WACCParser parser = null;
		ParseTree tree = null;
		try {
			// Create Parser from Tokens
			parser = new WACCParser(tokens);
			// Set tree to null for the moment
			tree = parser.program();
		} catch (Exception e) {
			System.err.println(e.toString());
			exitSyntaxError();
		}
		
		if(parser.getNumberOfSyntaxErrors() > 0) {
			exitSyntaxError();
		}
		return tree;
	}

	/**
	 * @param waccFilePath
	 * 		The path of the File to be compiled
	 * @return
	 * 		The InputStream 
	 * @throws FileNotFoundException
	 * 		If the filePath provided is not found.
	 */
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

	/**
	 * @param args
	 * 		The arguments array passed to the command line
	 * @return
	 * 		A CommandLine object that contains all the flag values.
	 */
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
	

	private static void exitSyntaxError() {
		System.exit(100);
	}

	private static void exitSemanticFailure() {
		System.exit(200);
	}
}
