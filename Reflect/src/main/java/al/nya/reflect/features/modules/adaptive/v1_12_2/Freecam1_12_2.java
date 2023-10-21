package al.nya.reflect.features.modules.adaptive.v1_12_2;

import al.nya.reflect.features.modules.Visual.Freecam;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityOtherPlayerMP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Freecam1_12_2 extends Freecam {
    @Override
    public void onEnable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull()) return;
        thePlayer.setMotionX(0);
        thePlayer.setMotionY(0);
        thePlayer.setMotionZ(0);

        fakePlayer = new EntityOtherPlayerMP(mc.getTheWorld(), thePlayer.getGameProfile());
        //fakePlayer.clonePlayer(thePlayer, true);
        fakePlayer.setRotationYawHead(thePlayer.getRotationYaw());
        fakePlayer.copyLocationAndAnglesFrom(thePlayer);
        mc.getTheWorld().addEntityToWorld((int) -(Math.random() * 10000), fakePlayer);
        if (!clip.getValue()) thePlayer.setNoClip(true);
    }
}
