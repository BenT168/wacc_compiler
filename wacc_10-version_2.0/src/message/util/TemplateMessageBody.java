package message.util;

import message.MessageBody;

import java.util.List;

public class TemplateMessageBody implements MessageBody {

    private List<String> messageParts;

    public TemplateMessageBody(List<String> messageParts) {
        this.messageParts = messageParts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        messageParts.forEach(sb::append);
        return sb.toString();
    }
}
