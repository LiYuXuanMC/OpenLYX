package com.reflectmc.reflect.event.events.player;

import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;

public class EventAttack extends Event {
    private EntityPlayer playerIn;
    private Entity targetEntity;
    public EventAttack(EntityPlayer playerIn, Entity targetEntity){
        this.playerIn = playerIn;
        this.targetEntity = targetEntity;
    }

    public Entity getTargetEntity() {
        return targetEntity;
    }

    public EntityPlayer getPlayerIn() {
        return playerIn;
    }
}
