package message;

public class Message {

    // What kind of message is it?
    private MessageType messageType;
    // Will typically be 'String'.
    private Object messageBody;

    public Message(MessageType messageType, Object messageBody) {
        this.messageType = messageType;
        this.messageBody = messageBody;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Object getMessageBody() {
        return messageBody;
    }
}
