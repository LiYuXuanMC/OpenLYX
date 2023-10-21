package al.logger.client.event.client;

import al.logger.client.event.Event;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import lombok.Getter;
import lombok.Setter;

public class EventNameTag implements Event {

    @Getter
    @Setter
    private String renderName;

    @Getter
    @Setter
    private EntityLivingBase entity;

    public EventNameTag(String renderName, EntityLivingBase entity){
        this.renderName = renderName;
        this.entity = entity;
    }

}

