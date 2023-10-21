package al.nya.reflect.events.events;

import al.nya.reflect.events.annotation.Realized;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
@Realized
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
