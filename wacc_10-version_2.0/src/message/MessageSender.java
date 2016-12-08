package message;

/**
 * Classes implementing this interface have the capability to produce and emit
 * messages, which will be received by their respective observers
 * (i.e: 'MessageObserver' objects).
 */
public interface MessageSender {

    void addMessageObserver(MessageObserver observer);

    void removeMessageObserver(MessageObserver observer);

    void sendMessage(Message message);
}
