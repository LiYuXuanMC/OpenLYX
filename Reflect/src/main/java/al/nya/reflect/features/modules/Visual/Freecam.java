package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityOtherPlayerMP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C06PacketPlayerPositionLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;

public class Freecam extends Module {
    public DoubleValue speed = new DoubleValue("Speed", 2.0, 0.8, 1.0, "0.0");
    public OptionValue clip = new OptionValue("Clip", true);

    public EntityOtherPlayerMP fakePlayer = null;

    public Freecam() {
        super("Freecam", "灵魂出窍", ModuleType.Visual);
        addValues(speed, clip);
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

    @EventTarget
    public void onUpdate(EventUpdate update) {
        Double speed = this.speed.getValue();
        EntityPlayerSP player = Minecraft.getMinecraft().getThePlayer();
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

    @EventTarget
    public void onPacket(EventPacket event) {
        Packet packet = event.getPacket();
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            event.setCancel(true);
        }
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)) {
            S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(packet.getWrapObject());
            fakePlayer.setPosition(s08.getX(), s08.getY(), s08.getZ());
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C06PacketPlayerPositionLook(s08.getX(), s08.getY(), s08.getZ(), fakePlayer.getRotationYaw(), fakePlayer.getRotationPitch(), fakePlayer.isOnGround()));
            event.setCancel(true);
        }
    }
}
