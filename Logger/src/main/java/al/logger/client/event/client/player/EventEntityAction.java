package al.logger.client.event.client.player;

import al.logger.client.event.Event;
import lombok.Getter;
import lombok.Setter;

public class EventEntityAction implements Event {
    @Getter
    boolean sprinting;

    @Getter
    boolean sneaking;

    public EventEntityAction(boolean sprinting , boolean sneaking) {
        this.sprinting = sprinting;
        this.sneaking = sneaking;
    }


    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }

    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }
}
