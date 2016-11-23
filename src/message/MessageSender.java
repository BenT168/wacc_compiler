package message;

/**
 * Classes implementing this interface have the capability to produce and emit
 * messages, which will be received by their respective observers
 * (i.e: 'MessageObserver' objects).
 */
public interface MessageSender {

    public void addMessageObserver(MessageObserver observer);

    public void removeMessageObserver(MessageObserver observer);

    public void sendMessage(Message message);
}
