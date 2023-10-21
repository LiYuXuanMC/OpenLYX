package al.logger.client.event.client.render;

import al.logger.client.event.Event;
import al.logger.client.wrapper.LoggerMC.entity.AbstractClientPlayer;
import al.logger.client.wrapper.LoggerMC.render.entity.RenderPlayer;
import lombok.Getter;
import net.minecraft.entity.player.EntityPlayer;

public class EventPreRenderPlayer implements Event {
    @Getter
    public AbstractClientPlayer entity;
    @Getter
    public RenderPlayer renderer;
    double x , y ,z;

    public float  partialTicks;
    public EventPreRenderPlayer(Object playerS, Object rendererS, float tick, double x, double y, double z) {
        this.entity = new AbstractClientPlayer(playerS);
        this.renderer = new RenderPlayer(rendererS);
        this.x = x;
        this.y = y;
        this.z = z;
        this.partialTicks = tick;
    }
}
