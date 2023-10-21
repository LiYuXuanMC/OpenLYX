package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import est.builder.annotations.Clear;

@Clear(when = "Release")
public class Freeze extends Module {
    private double motionX = 0.0;
    private double motionY = 0.0;
    private double motionZ = 0.0;
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    public ModeValue mode = new ModeValue("Mode",BlockMode.C03,BlockMode.values());
    public Freeze() {
        super("Freeze", Category.Movement);
        addValues(mode);
    }

    @Override
    public void onEnable() {
        if (mode.getValue() == BlockMode.ALL){
            Logger.getInstance().notificationManager.addNotification(new Notification("Connect lost after 30s" , Notification.NotificationType.Error));
        }
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull()) {
            return;
        }
        this.x = thePlayer.getPosX();
        this.y = thePlayer.getPosY();
        this.z = thePlayer.getPosZ();
        this.motionX = thePlayer.getMotionX();
        this.motionY = thePlayer.getMotionY();
        this.motionZ = thePlayer.getMotionZ();
    }

    @Override
    public void onDisable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        thePlayer.setMotionX(motionX);
        thePlayer.setMotionY(motionY);
        thePlayer.setMotionZ(motionZ);
        thePlayer.setPositionAndRotation(x, y, z, thePlayer.getRotationYaw(), thePlayer.getRotationPitch());
    }

    @Listener
    public void onUpdate(EventLivingUpdate update){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        thePlayer.setMotionX(0.0);
        thePlayer.setMotionY(0.0);
        thePlayer.setMotionZ(0.0);
        thePlayer.setPositionAndRotation(x, y, z, thePlayer.getRotationYaw(), thePlayer.getRotationPitch());
    }

    @Listener
    public void onPacket(EventPacket evt){
        if (mode.getValue() == BlockMode.C03) {
            if (C03PacketPlayer.isPacketPlayer(evt.getPacket())) {
                evt.cancel();
            }
            if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(evt.getPacket())) {
                S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(evt.getPacket().getWrappedObject());
                x = s08.getX();
                y = s08.getY();
                z = s08.getZ();
                motionX = 0.0;
                motionY = 0.0;
                motionZ = 0.0;
            }
        } else if (mode.getValue() == BlockMode.ALL) {
            evt.cancel();
        }
    }


    public enum BlockMode{
        C03,
        ALL
    }
}
