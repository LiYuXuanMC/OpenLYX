package al.logger.client.ui.builders;

import al.logger.client.ui.bases.components.Notification;

public class NotificationBuilder {
    private String message;
    private Notification.NotificationType type;

    public NotificationBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotificationBuilder setType(Notification.NotificationType type) {
        this.type = type;
        return this;
    }

    public Notification createNotification() {
        return new Notification(message, type);
    }
}