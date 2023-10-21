package al.logger.client.event.client.player;

import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;

public class EventRotationAnimation implements Event {
    private EntityLivingBase entity;
    private float rotationYawHead, renderYawOffset, renderHeadYaw, renderHeadPitch, partialTicks;

    public EventRotationAnimation(EntityLivingBase entity, float renderYawOffset, float rotationYawHead
            , float renderHeadYaw, float renderHeadPitch, float partialTicks) {
        this.entity = entity;
        this.rotationYawHead = rotationYawHead;
        this.renderYawOffset = renderYawOffset;
        this.renderHeadYaw = renderHeadYaw;
        this.renderHeadPitch = renderHeadPitch;
        this.partialTicks = partialTicks;
    }

    public EntityLivingBase getEntity() {
        return entity;
    }

    public void setEntity(EntityLivingBase entity) {
        this.entity = entity;
    }
    @ExportObfuscate(name = "getRenderYawOffset")
    public float getRenderYawOffset() {
        return renderYawOffset;
    }

    public void setRenderYawOffset(float renderYawOffset) {
        this.renderYawOffset = renderYawOffset;
    }
    @ExportObfuscate(name = "getRotationYawHead")
    public float getRotationYawHead() {
        return rotationYawHead;
    }

    public void setRotationYawHead(float rotationYawHead) {
        this.rotationYawHead = rotationYawHead;
    }
    @ExportObfuscate(name = "getRenderHeadYaw")
    public float getRenderHeadYaw() {
        return renderHeadYaw;
    }

    public void setRenderHeadYaw(float renderHeadYaw) {
        this.renderHeadYaw = renderHeadYaw;
    }
    @ExportObfuscate(name = "getRenderHeadPitch")
    public float getRenderHeadPitch() {
        return renderHeadPitch;
    }

    public void setRenderHeadPitch(float renderHeadPitch) {
        this.renderHeadPitch = renderHeadPitch;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}