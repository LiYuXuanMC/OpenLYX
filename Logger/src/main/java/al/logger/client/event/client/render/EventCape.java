package al.logger.client.event.client.render;

import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import lombok.Getter;
import lombok.Setter;

public class EventCape implements Event {
    @Setter
    private boolean hasCape;
    @Setter
    private ResourceLocation capeLocation;
    @Getter
    private final EntityPlayer player;
    public EventCape(EntityPlayer player,boolean hasCape){
        this.player = player;
        this.hasCape = hasCape;
    }
    public EventCape(EntityPlayer player,ResourceLocation resourceLocation){
        this.player = player;
        this.capeLocation = resourceLocation;
        if (capeLocation.isNull()){
            capeLocation = null;
            hasCape = false;
        }
    }
    @ExportObfuscate(name = "hasCape")
    public boolean isHasCape() {
        return hasCape;
    }
    @ExportObfuscate(name = "getCapeLocation")
    public ResourceLocation getCapeLocation() {
        return capeLocation == null ? new ResourceLocation((Object) null) : capeLocation;
    }
}
