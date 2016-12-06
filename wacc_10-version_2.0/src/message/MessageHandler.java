package message;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private Message message;
    private List<MessageObserver> observers;

    public MessageHandler() {
        this.observers = new ArrayList<>();
    }

    public void addMessageObserver(MessageObserver observer) {
        observers.add(observer);
    }

    public void removeMessageObserver(MessageObserver observer) {
        observers.remove(observer);
    }

    public void sendMessage(Message message) {
        this.message = message;
        notifyObservers();
    }

    private void notifyObservers() {
        for (MessageObserver o : observers) {
            o.receiveMessage(message);
        }
    }
}
