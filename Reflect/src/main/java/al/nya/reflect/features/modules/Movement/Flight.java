package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.block.*;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.util.Random;

public class Flight extends Module {
    public static ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    public DoubleValue speed = new DoubleValue("Speed", 10, 1, 2, "0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Vanilla;
        }
    };
    public DoubleValue timerBoost = new DoubleValue("TimerBoost", 5.0, 1.0, 1.0, "0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Hypixel;
        }
    };
    public OptionValue antiKick = new OptionValue("AntiKick", true) {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Vanilla;
        }
    };
    private BlockData blockData;
    private int startY, stage, slot;
    private boolean place, flag, onGround, reverse;
    private double[] startPos;
    private int packets;

    public static boolean isHypixelMode() {
        return mode.getValue() == Mode.Hypixel;
    }

    public Flight() {
        super("Flight", ModuleType.Movement);
        addValues(mode, speed, timerBoost, antiKick);
        packets = 0;
    }

    public void onDisable() {
        super.onDisable();

        if (mode.getValue() == Mode.Hypixel) {
            ClientUtil.resetTimer();
        }
        packets = 0;
    }

    @EventTarget
    @SuppressWarnings("unused")
    public void onPlayerPre(EventPreUpdate event) {

        if (mode.getValue() == Mode.Hypixel) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (!place) {
                if (slot != -1) {
                    if (thePlayer.getPosY() >= startY + 1) {
                        place = true;
                        thePlayer.getInventory().currentItem(slot);
                    }
                } else {
                    thePlayer.setMotionY(0);
                    stage++;
                }
            }

            switch (stage) {
                case 1:
                    thePlayer.setPosition(thePlayer.getPosX(), MathHelper.floor_double(event.getY()) + .125, thePlayer.getPosZ());
                    break;
                case 3:
                    damage();
                    event.setY(MathHelper.floor_double(event.getY()) - .0625);
                    break;
            }

            double distance;

            if (flag) {

                distance = thePlayer.getDistance(startPos[0], startPos[1], startPos[2]);

                if (MathHelper.floor_double(distance) % 5 == 0) {
                    if (!reverse) {
                        onGround = !onGround;
                        reverse = true;
                    }
                } else {
                    reverse = false;
                }

                mc.getTimer().setTimerSpeed(timerBoost.getValue().floatValue());
                thePlayer.setCameraYaw(.09F);
            } else {
                if (stage > 3) event.setCancel(true);
                event.setPitch(90);
            }

            event.setOnGround(flag ? onGround : new Random().nextBoolean());
        }
    }

    @EventTarget
    @SuppressWarnings("unused")
    public void onPostUpdate(EventPostUpdate e) {
        if (mode.getValue() == Mode.Hypixel) {
            if (place) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                thePlayer.swingItem();
                mc.getPlayerController().onPlayerRightClick(thePlayer, mc.getTheWorld(), thePlayer.getInventory().getMainInventory()[thePlayer.getInventory().currentItem()], blockData.getBlockPos(), blockData.getEnumFacing(), blockData.getVec());
                place = false;
                stage = 0;
                slot = -1;
            }
        }
    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (mode.getValue() == Mode.Hypixel) {
            if (e.getEventType() == EventType.SendPacket) return;
            Packet packet = e.getPacket();
            if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                NetworkManager netHandler = thePlayer.getSendQueue().getNetworkManager();
                S08PacketPlayerPosLook playerPosLook = new S08PacketPlayerPosLook(packet.getWrapObject());
                if (!flag) {
                    flag = true;
                    startPos = new double[]{
                            playerPosLook.getX(),
                            playerPosLook.getY(),
                            playerPosLook.getZ()
                    };

                    e.setCancel(true);
                    netHandler.sendPacketNoEvent(new C04PacketPlayerPosition(playerPosLook.getX(), playerPosLook.getY(), playerPosLook.getZ(), false));
                    netHandler.sendPacketNoEvent(new C05PacketPlayerLook(playerPosLook.getYaw(), playerPosLook.getPitch(), false));
                    thePlayer.setPosition(playerPosLook.getX(), playerPosLook.getY(), playerPosLook.getZ()); // no rotate
                }
            }
        }
        if (mode.getValue() == Mode.Vanilla) {
            if (e.getEventType() != EventType.SendPacket) return;
            Packet packet = e.getPacket();
            if (C03PacketPlayer.isPacketPlayer(packet) && antiKick.getValue()) {
                packets++;
                if (packets == 40) {
                    MovementUtils.handleVanillaKickBypass();
                    packets = 0;
                }
            }
        }
    }

    @EventTarget
    @SuppressWarnings("unused")
    public void onMove(EventMove e) {
        if (mode.getValue() == Mode.Hypixel) {
            if (flag)
                PlayerUtil.setSpeed(e, MovementUtils.getBaseMoveSpeed());
            else {
                PlayerUtil.setSpeed(e, 0);
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (mode.getValue() == Mode.Hypixel) {
            if (!(Reflect.USER.isBeta())){
                ClientUtil.printChat(ClientUtil.Level.WARNING,"Access Denied");
                setEnableNoNotification(false);
                return;
            }
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (!thePlayer.isOnGround()) {
                NotificationPublisher.queue("Flight", "You must stand on a block.", NotificationType.ERROR);
                setEnableNoNotification(false);
                return;
            }

            slot = getBlockSlot();

            if (slot == -1) {
                NotificationPublisher.queue("Flight", "You need at least 1 block.", NotificationType.ERROR);
                setEnableNoNotification(false);
                return;
            }

            if (!searchData(new BlockPos(thePlayer))) {
                NotificationPublisher.queue("Flight", "FAILED", NotificationType.ERROR);
                setEnableNoNotification(false);
                return;
            }

            startY = MathHelper.floor_double(thePlayer.getPosY());

            thePlayer.setPosition(MathHelper.floor_double(thePlayer.getPosX()) + .5, thePlayer.getPosY(), MathHelper.floor_double(thePlayer.getPosZ()) + .5);

            place = false;
            stage = 0;
            flag = false;
            onGround = true;
            reverse = false;
            startPos = null;

            thePlayer.jump();
        }
    }

    private boolean isReplaceable(BlockPos pos) {
        WorldClient world = mc.getTheWorld();
        return BlockTallGrass.isBlockTallGrass(world.getBlockState(pos).getBlock()) || world.getBlockState(pos).getBlock().getMaterial().isReplaceable();
    }


    private boolean isSolid(BlockPos pos) {
        WorldClient world = mc.getTheWorld();
        Block block = world.getBlockState(pos).getBlock();
        return (block.getMaterial().isSolid() || !block.isTranslucent() || block.isVisuallyOpaque() || BlockLadder.isBlockLaddeer(block) || BlockCarpet.isBlockCarpet(block) || BlockSnow.isBlockSnow(block) || BlockSkull.isBlockSkull(block)) && !block.getMaterial().isLiquid() && !(BlockContainer.isBlockContainer(block));
    }

    private boolean searchData(BlockPos blockPos) { // search Data as Scaffold.
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (!isReplaceable(blockPos)) return false;

        Vec3 eyesPos = new Vec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() + thePlayer.getEyeHeight(), thePlayer.getPosZ());

        BlockData findData = null;

        for (Enum<?> side : EnumFacing.values()) {

            final BlockPos aroundBlock = blockPos.offset(side);

            if (!isSolid(aroundBlock)) continue;

            final Vec3 dirVec = new Vec3(EnumFacing.getDirectionVec(side));

            for (double xSearch = .1; xSearch < .9; xSearch += .1) {
                for (double ySearch = .1; ySearch < .9; ySearch += .1) {
                    for (double zSearch = .1; zSearch < .9; zSearch += .1) {

                        final Vec3 posVec = new Vec3(blockPos).addVector(xSearch, ySearch, zSearch);
                        final Vec3 hitVec = posVec.add(new Vec3(dirVec.getXCoord() * .5, dirVec.getYCoord() * .5, dirVec.getZCoord() * .5));
                        final double distanceSqPosVec = eyesPos.squareDistanceTo(posVec);

                        if (eyesPos.squareDistanceTo(hitVec) > 18 || distanceSqPosVec > eyesPos.squareDistanceTo(posVec.add(dirVec)) || !theWorld.rayTraceBlocks(eyesPos, hitVec, false, true, false).isNull())
                            continue;

                        final float[] rotation = RotationUtils.getRotationFromEyeHasPrev(hitVec.getXCoord(), hitVec.getYCoord(), hitVec.getZCoord());
                        final Vec3 rotationVector = RotationUtils.getVectorForRotation(rotation);
                        final Vec3 vector = eyesPos.addVector(rotationVector.getXCoord() * 4, rotationVector.getYCoord() * 4, rotationVector.getZCoord() * 4);
                        final MovingObjectPosition obj = theWorld.rayTraceBlocks(eyesPos, vector, false, false, true);

                        if (!(obj.getTypeOfHit() == MovingObjectType.BLOCK) && obj.getBlockPos().equals(aroundBlock))
                            continue;

                        findData = new BlockData(aroundBlock, EnumFacing.getOpposite(side), hitVec);
                    }
                }
            }
        }

        if (findData == null) return false;
        blockData = findData;

        return true;
    }

    private int getBlockSlot() {
        int slot = -1;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = thePlayer.getInventory().getMainInventory()[i];
            if (itemStack.isNull() || !(ItemBlock.isItemBlock(itemStack.getItem()))) continue;
            if (!Scaffold.isValid(itemStack.getItem()))
                continue;
            slot = i;
            break;
        }
        return slot;
    }

    @EventTarget
    @SuppressWarnings("unused")
    public void onUpdate(EventUpdate update) {
        if (mode.getValue() == Mode.Vanilla) {
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
    }

    @EventTarget
    private void damage() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        final double fallHeight = 3.0625, offset = .0625;
        for (int i = 0; i <= (fallHeight / offset); i++) {
            networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + offset, thePlayer.getPosZ(), false));
            networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()), thePlayer.getPosZ(), (i == fallHeight / offset)));
        }
        networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + .1249999999999, thePlayer.getPosZ(), false));
        networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + .12268518518519, thePlayer.getPosZ(), false));
    }

    enum Mode {
        Vanilla,
        Hypixel
    }

    @SuppressWarnings("unused")
    private static class BlockData {
        private final BlockPos pos;
        private final Enum<?> facing;
        private Vec3 vec;

        public BlockData(final BlockPos pos, final Enum<?> facing, Vec3 vec) {
            this.pos = pos;
            this.facing = facing;
            this.vec = vec;
        }

        public Vec3 getVec() {
            return this.vec;
        }

        public void setVec(final Vec3 vec) {
            this.vec = vec;
        }

        public BlockPos getBlockPos() {
            return this.pos;
        }

        public Enum<?> getEnumFacing() {
            return this.facing;
        }
    }
}

