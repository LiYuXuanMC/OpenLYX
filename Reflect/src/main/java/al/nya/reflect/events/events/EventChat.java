package al.nya.reflect.events.events;

import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import lombok.Getter;

public class EventChat extends Event{
    @Getter
    private IChatComponent message;
    public EventChat(IChatComponent chatComponent){
        this.message = chatComponent;
    }
}
