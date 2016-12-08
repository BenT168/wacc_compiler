package main;

import antlr.WACCLexer;
import antlr.WACCParser;
import backend.generators.CodeGenerator;
import backend.generators.CodeGeneratorInterface;
import message.MessageFactory;
import message.MessageType;
import message.util.CompilerMessage;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WACC {

    private WACCParser parser;
    private WACCLexer lexer;
    private CodeGeneratorInterface codeGenerator;
    private ParseTree parseTree;

    // Class needs to be in named package for this variable to be globally visible.
    public static final MessageFactory messageFactory = MessageFactory.build(builder -> {
        builder.associate(MessageType.COMPILER_SUMMARY, CompilerMessage::new);
    });

    public WACC(String filePath) {

        try {
            FileReader fileReader = new FileReader(filePath);
            CharStream charStream = new ANTLRInputStream(fileReader);
            lexer = new WACCLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new WACCParser(tokenStream);
        } catch (FileNotFoundException e) {
            System.err.println("File at path:" + filePath + "\nnot found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(File outputFile) {
        parseTree = parser.program();
        codeGenerator = new CodeGenerator();
        codeGenerator.process((WACCParser.ProgramContext) parseTree, outputFile);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Please supply at least 2 arguments.");
            System.exit(-1);
        }
        try {
            String filePath = args[1];
            WACC wacc = new WACC(filePath);

            String[] filePathParts = filePath.split("\\\\|\\.|/");
            String outputFilePath = filePathParts[filePathParts.length-2] + ".s";
            File outputFile = new File(outputFilePath);
            wacc.process(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
