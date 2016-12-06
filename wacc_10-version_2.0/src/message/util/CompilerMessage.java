package message.util;

import message.Message;
import message.MessageBody;

public class CompilerMessage implements Message {

    // Message content
    private MessageBody messageBody;

    public CompilerMessage() {
    }

    @Override
    public void addMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public MessageBody getMessageBody() {
        return messageBody;
    }
}
