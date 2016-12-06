package message;

import java.util.function.Supplier;

/**
 * {@link FunctionalInterface} for associating {@link MessageType}s to particular
 * {@link Message}s.
 */
public interface MessageBuilder {
    void associate(MessageType messageType, Supplier<Message> messageSupplier);
}
