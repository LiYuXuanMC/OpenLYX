package al.logger.client.utils.player;

import al.logger.client.Logger;
import al.logger.client.features.modules.impls.World.Timer;
import al.logger.client.wrapper.LoggerMC.Minecraft;

public class ClientUtils {
    public static void resetTimer(){
        Timer timer = (Timer) Logger.getInstance().getModuleManager().getModule(Timer.class);
        if (timer.isEnable()){
            Minecraft.getMinecraft().getTimer().setTimerSpeed(timer.speed.getValue().floatValue());
        } else {
            Minecraft.getMinecraft().getTimer().setTimerSpeed(1F);
        }
    }
}
