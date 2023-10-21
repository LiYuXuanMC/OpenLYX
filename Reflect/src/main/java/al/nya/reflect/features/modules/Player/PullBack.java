package al.nya.reflect.features.modules.Player;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;

public class PullBack extends Module {
    public PullBack() {
        super("PullBack",ModuleType.Player);
    }
    @Override
    public void onEnable(){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if(!thePlayer.isNull() && !this.mc.getTheWorld().isNull()){
            thePlayer.getSendQueue().addToSendQueue(new C04PacketPlayerPosition(thePlayer.getPosX(), thePlayer.getPosY() + 11, thePlayer.getPosZ(), false));
        }
        this.setEnable(false);
    }
}
