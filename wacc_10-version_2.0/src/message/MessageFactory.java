package message;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Functional interface using the factory-kit design pattern.
 * <br>Instance of {@link MessageFactory} created locally allows the client
 * to strictly define which objects an instance of the factory is allowed
 * to create.
 * <br>In the typical factory method pattern, we have an interface for our
 * factory; interfaces for all objects that that the factory can create; and
 * the relevant factory methods in the interface. Actual creation of concrete
 * objects is then deferred to the implementing classes of the factory
 * interface.
 * <br>In the following however, the {@link MessageBuilder} passed as the
 * argument to the static {@link MessageFactory#build(Consumer) <MessageBuilder>)}
 * method, should associate each {@link MessageType} to an implementing class of
 * the {@link Message} interface.
 * <br>The method 'create(MessageType)' will then return the
 * {@link Message} class associated with that {@link MessageType}.
 */
public interface MessageFactory {

    Message create(MessageType messageType);

    /**
     *
     * @param consumer Represents a functional interface that expects objects of
     *                 type {@link MessageBuilder} and returns no result.
     *                 Unlike most {@link FunctionalInterface}s which return results,
     *                 {@link Consumer} is a {@link FunctionalInterface} expected to operate via side effects.
     * @return The factory created with the specified {@link MessageBuilder}.
     */
    static MessageFactory build(Consumer<MessageBuilder> consumer) {
        Map<MessageType, Supplier<Message>> map = new HashMap<>();
        consumer.accept(map::put);
        return messageType -> map.get(messageType).get();
    }
}
