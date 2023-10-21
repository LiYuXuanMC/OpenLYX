package al.logger.client.ui.builders;

import al.logger.client.ui.bases.components.Message;

public class MessageBuilder {
    private String rank;
    private String username;
    private String message;

    public MessageBuilder setRank(String rank) {
        this.rank = rank;
        return this;
    }

    public MessageBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public MessageBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message createMessage() {
        return new Message(rank, username, message);
    }
}