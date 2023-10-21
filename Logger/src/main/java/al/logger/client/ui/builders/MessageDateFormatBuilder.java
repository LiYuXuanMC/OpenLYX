package al.logger.client.ui.builders;

import al.logger.client.ui.bases.components.MessageDateFormat;

public class MessageDateFormatBuilder {
    private long timestamp;

    public MessageDateFormatBuilder setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MessageDateFormat createMessageDateFormat() {
        return new MessageDateFormat(timestamp);
    }
}