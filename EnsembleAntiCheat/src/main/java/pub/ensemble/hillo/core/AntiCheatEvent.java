package pub.ensemble.hillo.core;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import pub.ensemble.hillo.CoreMod;

public class AntiCheatEvent {

    private int oldAuthCount;
    private long updateTimeStamp;
    private int callCount;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (callCount > 10) {
            System.exit(0);
            return;
        }
        if (updateTimeStamp == 0) {
            updateTimeStamp = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - updateTimeStamp > 2000) {
            updateTimeStamp = System.currentTimeMillis();
            int newAuthCount = CoreMod.eac.getHYCCode();
            if (newAuthCount == oldAuthCount) {
                callCount++;
            } else {
                callCount-=5;
                if(callCount < 0){
                    callCount = 0;
                }
            }
            this.oldAuthCount = newAuthCount;
        }
    }


}
