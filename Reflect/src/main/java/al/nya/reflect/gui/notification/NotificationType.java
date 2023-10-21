package al.nya.reflect.gui.notification;


import java.awt.*;

public enum NotificationType {
    SUCCESS(new Color(6348946).getRGB()),
    INFO(new Color(6590631).getRGB()),
    ERROR(new Color(0xFF2F2F).getRGB());

    private final int color;

    NotificationType(int color) {
        this.color = color;
    }

    public final int getColor() {
        return this.color;
    }
}
