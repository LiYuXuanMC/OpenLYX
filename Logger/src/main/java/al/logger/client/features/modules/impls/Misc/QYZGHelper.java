package al.logger.client.features.modules.impls.Misc;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.utils.scoreboard.Scoreboard;

public class QYZGHelper extends Module {

    OptionValue savemap = new OptionValue("SaveMap", false);
    public QYZGHelper() {
        super("QYZGHelper",  Category.Misc);
        addValues(savemap);
    }
    @Listener
    public void onPacket(EventPacket e){
        Packet packet = new Packet(e.getPacket().getWrappedObject());

    }
}
