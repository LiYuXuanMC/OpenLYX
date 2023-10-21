package com.reflectmc.reflect.event.events.player;

import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;

public class EventRotationAnimation extends Event {
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
    @ExportObfuscate(name = "Rot$getRenderYawOffset")
    public float getRenderYawOffset() {
        return renderYawOffset;
    }

    public void setRenderYawOffset(float renderYawOffset) {
        this.renderYawOffset = renderYawOffset;
    }
    @ExportObfuscate(name = "Rot$getRotationYawHead")
    public float getRotationYawHead() {
        return rotationYawHead;
    }

    public void setRotationYawHead(float rotationYawHead) {
        this.rotationYawHead = rotationYawHead;
    }
    @ExportObfuscate(name = "Rot$getRenderHeadYaw")
    public float getRenderHeadYaw() {
        return renderHeadYaw;
    }

    public void setRenderHeadYaw(float renderHeadYaw) {
        this.renderHeadYaw = renderHeadYaw;
    }
    @ExportObfuscate(name = "Rot$getRenderHeadPitch")
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