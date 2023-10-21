package al.logger.client.ui.managers;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.components.Message;
import al.logger.client.ui.builders.MessageDateFormatBuilder;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArraySet;

public class MessageManager {


    @Getter
    private CopyOnWriteArraySet<ComponentBase> messages = new CopyOnWriteArraySet<>();
    public long lastMessageTime = 0;

    public void sendMessage(String message, int type) {
        Logger.getInstance().loggerWS.sendIRCMsgToServer(message, type);
    }

    public void addMessage(Message message) {
        if (lastMessageTime + 120000 < System.currentTimeMillis()) {
            messages.add(new MessageDateFormatBuilder().setTimestamp(lastMessageTime).createMessageDateFormat());
        }
        lastMessageTime = System.currentTimeMillis();
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

}
