package message;

/**
 * Created by MarkAduol on 05-Dec-16.
 */
public interface Message {
    void addMessageBody(MessageBody messageBody);

    MessageBody getMessageBody();
}
