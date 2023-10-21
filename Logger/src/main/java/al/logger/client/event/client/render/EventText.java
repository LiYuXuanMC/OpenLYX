package al.logger.client.event.client.render;

import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import lombok.Getter;
import lombok.Setter;

public class EventText extends CancelableEvent {
    @Setter
    private String text;
    @Setter
    private float x;
    @Setter
    private float y;
    @Setter
    private int color;
    @Setter
    private boolean dropShadow;
    @Getter
    private final boolean render;
    /**
     * Cancel only
     */
    @Setter
    private int returnValue;
    public EventText(String text, float x, float y, int color, boolean dropShadow, boolean render){
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.dropShadow = dropShadow;
        this.render = render;
        this.returnValue = 0;
    }

    @ExportObfuscate(name = "getX")
    public float getX() {
        return x;
    }
    @ExportObfuscate(name = "getY")
    public float getY() {
        return y;
    }
    @ExportObfuscate(name = "getColor")
    public int getColor() {
        return color;
    }
    @ExportObfuscate(name = "getText")
    public String getText() {
        return text;
    }
    @ExportObfuscate(name = "getReturnValue")
    public int getReturnValue() {
        return returnValue;
    }
    @ExportObfuscate(name = "isDropShadow")
    public boolean isDropShadow() {
        return dropShadow;
    }
}
