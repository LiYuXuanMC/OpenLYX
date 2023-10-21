package al.logger.client.event.client.render;

import al.logger.client.event.Event;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.render.entity.RendererLivingEntity;
import lombok.Getter;

public class EventPreRenderLiving implements Event {

    @Getter private final EntityLivingBase entity;
    @Getter private final RendererLivingEntity renderer;
    @Getter private final double x;
    @Getter private final double y;
    @Getter private final double z;
    @Getter private final float yaw;
    @Getter private final float partialTicks;

    public EventPreRenderLiving(EntityLivingBase Entity, RendererLivingEntity Renderer, double X, double Y, double Z, float Yaw, float PartialTicks){
        entity = Entity;
        renderer = Renderer;
        x = X;
        y = Y;
        z = Z;
        yaw = Yaw;
        partialTicks = PartialTicks;
    }

}