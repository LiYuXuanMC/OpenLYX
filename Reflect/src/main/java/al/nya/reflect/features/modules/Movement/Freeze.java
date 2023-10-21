package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;

public class Freeze extends Module {
    private double motionX = 0.0;
    private double motionY = 0.0;
    private double motionZ = 0.0;
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    public ModeValue mode = new ModeValue("Mode",BlockMode.C03,BlockMode.values()){
        @Override
        public void onValueChange(){
            if (getValue() == BlockMode.ALL && isEnable()){
                NotificationPublisher.queue("Freeze","Connect lost after 30s",30000, NotificationType.ERROR);
            }
        }
    };
    public Freeze() {
        super("Freeze", ModuleType.Movement);
        addValue(mode);
    }

    @Override
    public void onEnable() {
        if (mode.getValue() == BlockMode.ALL){
            NotificationPublisher.queue("Freeze","Connect lost after 30s",30000, NotificationType.ERROR);
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

    @EventTarget
    public void onUpdate(EventUpdate update){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        thePlayer.setMotionX(0.0);
        thePlayer.setMotionY(0.0);
        thePlayer.setMotionZ(0.0);
        thePlayer.setPositionAndRotation(x, y, z, thePlayer.getRotationYaw(), thePlayer.getRotationPitch());
    }

    @EventTarget
    public void onPacket(EventPacket evt){
        if (mode.getValue() == BlockMode.C03) {
            if (C03PacketPlayer.isPacketPlayer(evt.getPacket())) {
                evt.setCancel(true);
            }
            if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(evt.getPacket())) {
                S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(evt.getPacket().getWrapObject());
                x = s08.getX();
                y = s08.getY();
                z = s08.getZ();
                motionX = 0.0;
                motionY = 0.0;
                motionZ = 0.0;
            }
        } else if (mode.getValue() == BlockMode.ALL) {
            evt.setCancel(true);
        }
    }


    public enum BlockMode{
        C03,
        ALL
    }
}
