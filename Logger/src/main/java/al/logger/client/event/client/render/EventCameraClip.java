package al.logger.client.event.client.render;

import al.logger.client.event.CancelableEvent;
import al.logger.client.wrapper.LoggerMC.render.EntityRenderer;
import lombok.Getter;

public class EventCameraClip extends CancelableEvent {
    @Getter
    private final EntityRenderer renderer;
    @Getter
    private final float partialTicks;
    public EventCameraClip(EntityRenderer renderer, float partialTicks) {
        this.partialTicks = partialTicks;
        this.renderer = renderer;
    }
}
