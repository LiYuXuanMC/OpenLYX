package cc.systemv.rave.feature.component;

import cc.systemv.rave.event.Priority;
import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.event.events.EventPacket;
import cc.systemv.rave.event.events.EventPullBack;
import cc.systemv.rave.utils.InstanceAccess;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class PullBackComponent extends InstanceAccess {
    public PullBackComponent() {

    }
    @Listener(priority = Priority.Highest)
    public void onPacket(EventPacket eventPacket){
        if (eventPacket.getPacket() instanceof S08PacketPlayerPosLook){
            rave.getEventBus().callEvent(new EventPullBack());
        }
    }
}
