package backEnd;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import intermediate.symboltable.SymbolTableStack;
import message.Message;
import message.MessageSender;
import message.MessageHandler;
import message.MessageObserver;

public abstract class BackEnd extends WACCParserBaseVisitor<Object> implements MessageSender {

    protected static SymbolTableStack symTabStack;
    protected static MessageHandler messageHandler = new MessageHandler();

    /**
     * Processes intermediate code using information in symbol table. The output
     * should be generated Assembly code for target machine.
     * @param symTabStack Symbol Table Stack.
     * @param ctx Root node of AST.
     * @throws Exception Exception thrown if error occurs during processing.
     */
    public abstract void process(SymbolTableStack symTabStack, WACCParser.ProgramContext ctx) throws Exception;

    @Override
    public void addMessageObserver(MessageObserver observer) {
        messageHandler.addMessageObserver(observer);
    }

    @Override
    public void removeMessageObserver(MessageObserver observer) {
        messageHandler.removeMessageObserver(observer);
    }

    @Override
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}
