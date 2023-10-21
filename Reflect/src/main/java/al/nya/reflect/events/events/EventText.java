package al.nya.reflect.events.events;

import lombok.Getter;
import lombok.Setter;

public class EventText extends Event{
    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private float x;
    @Getter
    @Setter
    private float y;
    @Getter
    @Setter
    private int color;
    @Getter
    @Setter
    private boolean dropShadow;
    @Getter
    private final boolean render;
    /**
     * Cancel only
     */
    @Getter
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
}
