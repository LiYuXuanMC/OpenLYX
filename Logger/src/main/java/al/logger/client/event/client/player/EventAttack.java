package al.logger.client.event.client.player;

import al.logger.client.event.CancelableEvent;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;

public class EventAttack extends CancelableEvent {
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
