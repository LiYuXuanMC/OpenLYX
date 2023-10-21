package al.nya.reflect.events.events;

import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.render.entity.RenderLivingEntity;
import lombok.Getter;

public class EventPreRenderLiving extends Event {

    @Getter private final EntityLivingBase entity;
    @Getter private final RenderLivingEntity renderer;
    @Getter private final double x;
    @Getter private final double y;
    @Getter private final double z;
    @Getter private final float yaw;
    @Getter private final float partialTicks;

    public EventPreRenderLiving(EntityLivingBase Entity,RenderLivingEntity Renderer, double X,double Y,double Z,float Yaw,float PartialTicks){
        entity = Entity;
        renderer = Renderer;
        x = X;
        y = Y;
        z = Z;
        yaw = Yaw;
        partialTicks = PartialTicks;
    }

}