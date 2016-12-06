package backend;

import antlr.WACCParserBaseVisitor;
import message.*;

/**
 * Enscapulates the general idea of our back-end component.
 */
public abstract class Backend extends WACCParserBaseVisitor<CodegenInfo> implements MessageSender {

    // Message handler delegate, visible to all subclasses within and without
    // the package.
    private static final MessageHandler messageHandler = new MessageHandler();

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
