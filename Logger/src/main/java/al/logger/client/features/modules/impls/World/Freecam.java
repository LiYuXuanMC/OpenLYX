package al.logger.client.features.modules.impls.World;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityOtherPlayerMP;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C06PacketPlayerPositionLook;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;

public class Freecam extends Module {
    public DoubleValue speed = new DoubleValue("Speed", 2.0, 0.8, 1.0, 0.1);
    public OptionValue clip = new OptionValue("Clip", true);

    public EntityOtherPlayerMP fakePlayer = null;

    public Freecam() {
        super("Freecam", Category.World);
        addValues(speed,clip);
    }
    @Override
    public void onEnable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();

        if (thePlayer.isNull()) return;
        thePlayer.setMotionX(0);
        thePlayer.setMotionY(0);
        thePlayer.setMotionZ(0);

        fakePlayer = new EntityOtherPlayerMP(mc.getTheWorld(), thePlayer.getGameProfile());
        fakePlayer.clonePlayer(thePlayer, true);
        fakePlayer.setRotationYawHead(thePlayer.getRotationYaw());
        fakePlayer.copyLocationAndAnglesFrom(thePlayer);
        mc.getTheWorld().addEntityToWorld((int) -(Math.random() * 10000), fakePlayer);
        if (!clip.getValue()) thePlayer.setNoClip(true);
    }

    @Override
    public void onDisable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull() || fakePlayer == null) return;
        thePlayer.setPositionAndRotation(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ(), thePlayer.getRotationYaw(), thePlayer.getRotationPitch());
        mc.getTheWorld().removeEntityFromWorld(fakePlayer.getEntityId());
        fakePlayer = null;
        thePlayer.setNoClip(false);
    }

    @Listener
    public void onUpdate(EventLivingUpdate update) {
        Double speed = this.speed.getValue();
        EntityPlayerSP player = mc.getThePlayer();
        if (!clip.getValue()) player.setNoClip(true);
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
    public void onPacket(EventPacket event) {
        Packet packet = event.getPacket();
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            event.cancel();
        }
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)) {
            S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(packet.getWrappedObject());
            fakePlayer.setPosition(s08.getX(), s08.getY(), s08.getZ());
            fakePlayer.setRotationYaw(s08.getYaw());
            fakePlayer.setRotationPitch(s08.getPitch());
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C06PacketPlayerPositionLook(s08.getX(), s08.getY(), s08.getZ(), fakePlayer.getRotationYaw(), fakePlayer.getRotationPitch(), fakePlayer.isOnGround()));
            event.cancel();
        }
    }
}
