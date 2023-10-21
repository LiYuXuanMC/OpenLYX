package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Movement.speeds.matrix.Matrix;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.network.client.C0FPacketConfirmTransaction;
import al.logger.client.wrapper.LoggerMC.network.server.S12PacketEntityVelocity;
import al.logger.client.wrapper.LoggerMC.network.server.S27PacketExplosion;
import al.logger.client.wrapper.LoggerMC.network.server.S32PacketConfirmTransaction;

public class Velocity extends Module {
    public ModeValue modeValue = new ModeValue("Mode", VelocityMode.Normal, VelocityMode.values());
    public DoubleValue horizontal = new DoubleValue("Horizontal",100.0, -100.0, 0f,1);
    public DoubleValue vertical = new DoubleValue("Vertical",100.0, -100.0, 0f,1);

    public Velocity() {
        super("Velocity", "modify the velocity of the player", Category.Movement);
        this.setHazard(Hazard.HACK);
        this.addValues(modeValue,horizontal,vertical);
        horizontal.addCallBack(() -> modeValue.getValue() == VelocityMode.Modify);
        vertical.addCallBack(() -> modeValue.getValue() == VelocityMode.Modify);
    }

    @Listener
    public void onPacket(EventPacket eventPacket) {


        if (S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket())) {
            S12PacketEntityVelocity packet = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
            if (packet.getEntityID() == mc.getThePlayer().getEntityId()) {
                if (modeValue.getValue() == VelocityMode.Normal) {
                    packet.setMotionX(0);
                    packet.setMotionY((int) (packet.getMotionY() * 0.99905));
                    packet.setMotionZ(0);
                }
                if (modeValue.getValue() == VelocityMode.Cancel) {
                    eventPacket.cancel();
                }
                if (modeValue.getValue() == VelocityMode.Modify) {
                    packet.setMotionX((int) (packet.getMotionX() * horizontal.getValue() / 100f));
                    packet.setMotionY((int) (packet.getMotionY() * vertical.getValue() / 100f));
                    packet.setMotionZ((int) (packet.getMotionZ() * horizontal.getValue() / 100f));
                }
            }
        }

        if (modeValue.getValue() == VelocityMode.CancelS32){
            if (S32PacketConfirmTransaction.isS32PacketConfirmTransaction(eventPacket.getPacket())){
                eventPacket.cancel();
            }
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket())){
                S12PacketEntityVelocity packet = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
                if (packet.getEntityID() == mc.getThePlayer().getEntityId()){
                    eventPacket.cancel();
                }
            }
            if (S27PacketExplosion.isS27PacketExplosion(eventPacket.getPacket())){
                eventPacket.cancel();
            }
        }

        if (modeValue.getValue() == VelocityMode.CancelC0F){
            if (C0FPacketConfirmTransaction.isPacketConfirmTransaction(eventPacket.getPacket())){
                eventPacket.cancel();
            }
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket())){
                S12PacketEntityVelocity packet = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
                if (packet.getEntityID() == mc.getThePlayer().getEntityId()){
                    eventPacket.cancel();
                }
            }
            if (S27PacketExplosion.isS27PacketExplosion(eventPacket.getPacket())){
                eventPacket.cancel();
            }
        }

        if (modeValue.getValue() == VelocityMode.Matrix){
            if(S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket())) {
                if (Logger.getInstance().getModuleManager().getModule(Speed.class).isEnable() && Matrix.mode.getValue() == Matrix.Mode.HurtTime) {
                    return;
                }
                S12PacketEntityVelocity packet = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
                if (packet.getEntityID() == mc.getThePlayer().getEntityId()){
                    packet.setMotionX((int)(packet.getMotionX() * 0.36));
                    packet.setMotionZ((int)(packet.getMotionZ() * 0.36));
                    if (mc.getThePlayer().isOnGround()) {
                        packet.setMotionX((int) (packet.getMotionX() * 0.9));
                        packet.setMotionZ((int) (packet.getMotionZ() * 0.9));
                    }
                }
            }
        }

        if (modeValue.getValue() == VelocityMode.Hypixel){
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket())){
                S12PacketEntityVelocity packet = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
                if (packet.getEntityID() == mc.getThePlayer().getEntityId()) {
                    if (mc.getThePlayer().isOnGround()) {
                        packet.setMotionX(0);
                        packet.setMotionY(packet.getMotionY());
                        packet.setMotionZ(0);
                    } else {
                        eventPacket.cancel();
                    }
                }
            }
        }
    }

    enum VelocityMode {
        Normal,
        Cancel,
        Modify,
        CancelS32,
        CancelC0F,
        Matrix,
        Hypixel
    }
}
