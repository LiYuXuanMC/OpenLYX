package al.logger.client.features.modules.impls.Movement.fly.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;

public class VanillaFly extends Module {
    OptionValue antikick = new OptionValue("AntiKick"  ,true);

    DoubleValue speed = new DoubleValue("Speed", 10, 1, 2, 0.1);
    private int packets;
    public VanillaFly(){
        super("Vanilla"  , Category.Movement);
        packets = 0;
        addValues(antikick , speed);
    }


    @Override
    public void onDisable() {
        packets = 0;
        super.onDisable();
    }
    @Listener
    public void onUpdate(EventUpdate e){
        if (mc.getThePlayer().isNull() || mc.getTheWorld().isNull()){
            return;
        }
        Double speed = this.speed.getValue();
        EntityPlayerSP player = Minecraft.getMinecraft().getThePlayer();
        player.setJumpMovementFactor(0.4f);
        player.setMotionX(0.0);
        player.setMotionY(0.0);
        player.setMotionZ(0.0);
        player.setJumpMovementFactor(player.getJumpMovementFactor() * (speed.floatValue() * 3.0f));
        if (mc.getGameSettings().getKeyBindJump().isKeyDown()) {
            player.setMotionY(player.getMotionY() + speed);
        }
        if (mc.getGameSettings().getKeyBindSneak().isKeyDown()) {
            player.setMotionY(player.getMotionY() - speed);
        }

    }

    @Listener
    public void onPacket(EventPacket e) {
        Packet packet = new Packet(e.getPacket().getWrappedObject());
        if (C03PacketPlayer.isPacketPlayer(packet) && antikick.getValue()) {
            packets++;
            if (packets == 40) {
                MovementUtils.handleVanillaKickBypass();
                packets = 0;
            }
        }
        super.onPacket(e);
    }
}
