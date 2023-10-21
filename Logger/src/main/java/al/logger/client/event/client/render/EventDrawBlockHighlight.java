package al.logger.client.event.client.render;

import al.logger.client.Logger;
import al.logger.client.event.Event;

public class EventDrawBlockHighlight  implements Event {


    public void n(){
        Logger.getInstance().getEventBus().callEvent(new EventDrawBlockHighlight());
    }
}
