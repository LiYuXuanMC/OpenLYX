package al.logger.client.event.client.player;

import al.logger.client.event.CancelableEvent;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.model.ModelBiped;

public class EmotePreEvent extends CancelableEvent {
    public ModelBiped biped;
    public Entity entity;
    public EmotePreEvent(ModelBiped biped , Entity entity) {
        this.biped = biped;
        this.entity = entity;
    }
    public Entity getEntity(){
        return entity;
    }

    public ModelBiped getBiped(){
        return biped;
    }

}
