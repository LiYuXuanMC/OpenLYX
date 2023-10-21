package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPostUpdate;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.Rotation;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.utils.WrapperAdaptArrayList;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.block.*;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.InventoryPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.item.Container;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0APacketAnimation;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovementInput;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;

import static al.nya.reflect.features.modules.ModuleType.World;

public class Scaffold extends Module {
    private BlockData data;
    private int slot;
    private int towerTick;
    public static OptionValue swing = new OptionValue("Swing", false);
    public static OptionValue tower = new OptionValue("Tower", false);
    public static OptionValue towerboost = new OptionValue("TowerBoost", false){
        @Override
        public boolean show(){
            return tower.getValue();
        }
    };
    public static OptionValue timer = new OptionValue("Timer",false);
    public static DoubleValue timerSpeed = new DoubleValue("TimerSpeed", 3D,0.5D,1.5D,"0.0"){
        @Override
        public boolean show(){
            return timer.getValue();
        }
    };
    public static OptionValue keepY = new OptionValue("KeepY",false);
    public static OptionValue render = new OptionValue("Render", true);
    public static OptionValue sprint = new OptionValue("Sprint",true);
    public static OptionValue customPitch = new OptionValue("CustomPitch",false);
    public static DoubleValue pitch = new DoubleValue("Pitch",360D,0D,71.4D,"0.0"){
        @Override
        public boolean show(){
            return customPitch.getValue();
        }
    };
    public static DoubleValue towerTickLen = new DoubleValue("TowerTick",20D,2D,2D,"0"){
        @Override
        public boolean show(){
            return tower.getValue();
        }
    };
    public static OptionValue boost = new OptionValue("Boost",false);
    public static DoubleValue boostY = new DoubleValue("YPort",0.2D,0.01D,0.12D,"0.00"){
        @Override
        public boolean show(){
            return boost.getValue();
        }
    };
    public static OptionValue godBridge = new OptionValue("FakeGodBridge",false);
    private static FloatBuffer colorBuffer;
    private static int godBridgeStage = 0;
    private ItemStack currentblock;
    private static final Rotation rotation = new Rotation(999.0f, 999.0f);
    public final static List<Block> invalidBlocks = WrapperAdaptArrayList.toWrapperAdaptArrayList(Arrays.asList(Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.lava,
            Blocks.flowing_lava, Blocks.enchanting_table, Blocks.carpet, Blocks.glass_pane,
            Blocks.stained_glass_pane, Blocks.iron_bars, Blocks.snow_layer, Blocks.ice, Blocks.packed_ice,
            Blocks.coal_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.chest, Blocks.trapped_chest,
            Blocks.torch, Blocks.anvil, Blocks.trapped_chest, Blocks.noteblock, Blocks.jukebox, Blocks.tnt,
            Blocks.gold_ore, Blocks.iron_ore, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.quartz_ore,
            Blocks.redstone_ore, Blocks.wooden_pressure_plate, Blocks.stone_pressure_plate,
            Blocks.light_weighted_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.stone_button,
            Blocks.wooden_button, Blocks.lever, Blocks.tallgrass, Blocks.tripwire, Blocks.tripwire_hook,
            Blocks.rail, Blocks.waterlily, Blocks.red_flower, Blocks.red_mushroom, Blocks.brown_mushroom,
            Blocks.vine, Blocks.trapdoor, Blocks.yellow_flower, Blocks.ladder, Blocks.furnace, Blocks.sand,
            Blocks.cactus, Blocks.dispenser, Blocks.noteblock, Blocks.dropper, Blocks.crafting_table, Blocks.web,
            Blocks.pumpkin, Blocks.sapling, Blocks.cobblestone_wall, Blocks.oak_fence, Blocks.redstone_torch, Blocks.ender_chest));
    private double y = 0;

    public Scaffold() {
        super("Scaffold", World);
        addValues(swing, tower, towerboost, towerTickLen, timer, timerSpeed, render, customPitch, pitch, keepY, sprint, boost, boostY, godBridge);
    }

    public static Vec3 getVec3(BlockPos pos, Enum face) {
        double x = (double) pos.getX() + 0.5;
        double y = (double) pos.getY() + 0.5;
        double z = (double) pos.getZ() + 0.5;
        if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
            x += randomNumber(0.3, -0.3);
            z += randomNumber(0.3, -0.3);
        } else {
            y += randomNumber(0.3, -0.3);
        }
        if (face == EnumFacing.WEST || face == EnumFacing.EAST) {
            z += randomNumber(0.3, -0.3);
        }
        if (face == EnumFacing.SOUTH || face == EnumFacing.NORTH) {
            x += randomNumber(0.3, -0.3);
        }
        return new Vec3(x, y, z);
    }

    @Override
    public void onEnable() {
        godBridgeStage = 0;
        if (timer.getValue()) {
            mc.getTimer().setTimerSpeed(timerSpeed.getValue().floatValue());
        }
    }

    public boolean isAirUnder(Entity ent) {
        return mc.getTheWorld().getBlockState(new BlockPos(ent.getPosX(), ent.getPosY() - 1, ent.getPosZ())).getBlock().equals(Blocks.air);
    }

    @EventTarget
    public void onPost(EventPostUpdate e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        InventoryPlayer inventory = thePlayer.getInventory();
        int last = thePlayer.getInventory().currentItem();
        thePlayer.getInventory().currentItem(slot);
        if (mc.getPlayerController().onPlayerRightClick(thePlayer, mc.getTheWorld(),
                thePlayer.getCurrentEquippedItem(), this.data.getBlockPos(), this.data.getEnumFacing(),
                getVec3(this.data.getBlockPos(), this.data.getEnumFacing()))) {
            if (inventory.getStackInSlot(inventory.currentItem()).getItem() != null
                    && (ItemBlock.isItemBlock(inventory.getStackInSlot(inventory.currentItem())
                    .getItem()))) {
                ItemBlock itemblock = new ItemBlock(inventory
                        .getStackInSlot(inventory.currentItem()).getItem().getWrapObject());

                if (itemblock != null) {
                    /*mc.thePlayer.playSound(itemblock.getBlock().stepSound.getPlaceSound(),
                            (itemblock.getBlock().stepSound.getVolume() + 1.0F) / 2.0F,
                            itemblock.getBlock().stepSound.getFrequency() * 0.8F);
                     */
                }
            }
            if (swing.getValue()) thePlayer.getSendQueue().addToSendQueue(new C0APacketAnimation());
        }
        inventory.currentItem(last);
    }

    @EventTarget
    public void onPre(EventPreUpdate event) {
        godBridgeStage++;
        if (godBridgeStage > 20) {
            godBridgeStage = -20;
        }
        double motionY = mc.getThePlayer().getMotionY();
        double motionX = mc.getThePlayer().getMotionY();
        if (godBridge.getValue()) {
            if (motionX > motionY){
                //Move toward X
                event.setY(event.getY() + (godBridgeStage*0.01));
            }else if (motionY > motionX){
                //Move toward Y
                event.setX(event.getX() + (godBridgeStage*0.01));
            }
        }
        if ((getBlockCount() <= 0)) {
            this.setEnable(false);
            return;
        }
        if (boost.getValue()) boost();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double posY = thePlayer.getPosY();
        double posZ = thePlayer.getPosZ();
        double posX = thePlayer.getPosX();
        if (keepY.getValue()){
            if (thePlayer.isOnGround()){
                y = posY;
            }else {
                if (!MovementUtils.isMoving() && keepY.getValue()){
                    y = posY;
                }
            }
        }else {
            y = posY;
        }
        /*if (this.getBlockCount() <= 0) {
            int spoofSlot = this.getBestSpoofSlot();
            this.getBlock(spoofSlot);
        }
         */

        this.data = this.getBlockData(new BlockPos(posX, y - 1.0,
                posZ)) == null
                ? this.getBlockData(new BlockPos(posX, y - 1.0,
                posZ).down())
                : this.getBlockData(new BlockPos(posX, y - 1.0,
                posZ));
        this.slot = this.getBestSlot();
        this.currentblock = thePlayer.getInventoryContainer().getSlot(slot + 36).getStack();
        if (this.data == null || this.slot == -1 || this.getBlockCount() <= 0
                || !(isMoving() || mc.getGameSettings().getKeyBindJump().isKeyDown())) {
            return;
        }
        if (mc.getTheWorld().getBlockState(new BlockPos(posX,
                y - 0.5, posZ)).getBlock().equals(Blocks.air)) {
            float[] rotations = getRotationsHypixel(data.pos, data.facing);
            rotation.setYaw(rotations[0]);
            rotation.setPitch(rotations[1]);
        }

        if (rotation.getYaw() != 999.0f) {
            event.setYaw(rotation.getYaw());
        }
        if (rotation.getPitch() != 999.0f) {
            event.setPitch(rotation.getPitch());
        }
        if (customPitch.getValue())
            event.setPitch(pitch.getValue().floatValue());
        //ClientUtil.printChat("[Scaffold]Yaw:"+event.getYaw()+" Pitch:"+event.getPitch()+" Y:"+y+" OnGround:"+mc.thePlayer.onGround);
        if ((tower.getValue()) && isAirUnder(thePlayer) && MovementUtils.isOnGround(1.15)
                && mc.getGameSettings().getKeyBindJump().isKeyDown() && !PlayerUtil.MovementInput()) {
            if (towerboost.getValue())
                mc.getTimer().setTimerSpeed(2.1078f);
            thePlayer.setMotionX(0);
            thePlayer.setMotionZ(0);
            thePlayer.getMovementInput().setMoveForward(0);
            thePlayer.getMovementInput().setMoveStrafe(0);
            this.towerTick++;
            if (this.towerTick < towerTickLen.getValue()) {
                thePlayer.jump();
            } else {
                this.towerTick = 0;
            }
        }
        if (MovementUtils.isOnGround(1.15) && mc.getGameSettings().getKeyBindJump().isKeyDown()
                && !PlayerUtil.MovementInput() && tower.getValue()) {

        } else if (mc.getTimer().getTimerSpeed() == 2.1078f) {
            mc.getTimer().setTimerSpeed(1);
        }
    }

    @Override
    public void setEnable(boolean b) {
        if (b){
            this.data = null;
            this.slot = -1;
            rotation.setYaw(999.0f);
            rotation.setPitch(999.0f);
            this.towerTick = 0;
        }else {
            onDisable();
        }
        super.setEnable(b);
    }

    public void onDisable() {
        rotation.setYaw(999.0f);
        rotation.setPitch(999.0f);
        ClientUtil.resetTimer();
    }

    public float[] getRotationsHypixel(BlockPos block, Enum face) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        final double x = block.getX() + 0.5 - thePlayer.getPosX() + EnumFacing.getFrontOffsetX(face) / 2.0;
        final double z = block.getZ() + 0.5 - thePlayer.getPosZ() + EnumFacing.getFrontOffsetX(face) / 2.0;
        double y = block.getY() + 0.5;
        final double d1 = y + thePlayer.getEyeHeight() - y;
        final double d2 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float) (Math.atan2(d1, d2) * 180.0 / 3.141592653589793);
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        return new float[]{yaw, pitch};
    }

    public void boost() {
        if (PlayerUtil.MovementInput()) {
            if (mc.getThePlayer().isOnGround()) {
                if (mc.getThePlayer().isCollidedVertically())
                    mc.getThePlayer().setMotionY(boostY.getValue());
            } else {
                MovementUtils.strafe(MovementUtils.defaultSpeed() /* * 1.6 + Math.random() / 100*/);
            }
        }
    }

    private int getBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            if (!mc.getThePlayer().getInventoryContainer().getSlot(i + 36).getHasStack()
                    || !(ItemBlock.isItemBlock(mc.getThePlayer().getInventoryContainer().getSlot(i + 36).getStack()
                    .getItem())))
                continue;
            return i;
        }
        return -1;
    }
    private int getBestSlot(){
        Container container = mc.getThePlayer().getInventoryContainer();
        int usableSlot = -1;
        int slotBlocks = -1;
        for (int i = 0; i < 9; ++i) {
            if (!container.getSlot(36+i).getHasStack()
                    || !(ItemBlock.isItemBlock(container.getSlot(i + 36).getStack()
                    .getItem())))
                continue;
            ItemBlock itemBlock = new ItemBlock(container.getSlot(i + 36).getStack().getItem().getWrapObject());
            if (invalidBlocks.contains(itemBlock.getBlock().getWrapObject())) continue;
            if (slotBlocks < container.getSlot(i + 36).getStack().getStackSize()){
                usableSlot = i;
                slotBlocks = container.getSlot(i + 36).getStack().getStackSize();
            }
        }
        return usableSlot;
    }

    private BlockData getBlockData(BlockPos pos) {
        if (isPosSolid(pos.add(0, -1, 0))) {
            return new BlockData(pos.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos.add(-1, 0, 0))) {
            return new BlockData(pos.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos.add(1, 0, 0))) {
            return new BlockData(pos.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos.add(0, 0, 1))) {
            return new BlockData(pos.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos.add(0, 0, -1))) {
            return new BlockData(pos.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos1 = pos.add(-1, 0, 0);
        if (isPosSolid(pos1.add(0, -1, 0))) {
            return new BlockData(pos1.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos1.add(-1, 0, 0))) {
            return new BlockData(pos1.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos1.add(1, 0, 0))) {
            return new BlockData(pos1.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos1.add(0, 0, 1))) {
            return new BlockData(pos1.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos1.add(0, 0, -1))) {
            return new BlockData(pos1.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos2 = pos.add(1, 0, 0);
        if (isPosSolid(pos2.add(0, -1, 0))) {
            return new BlockData(pos2.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos2.add(-1, 0, 0))) {
            return new BlockData(pos2.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos2.add(1, 0, 0))) {
            return new BlockData(pos2.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos2.add(0, 0, 1))) {
            return new BlockData(pos2.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos2.add(0, 0, -1))) {
            return new BlockData(pos2.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos3 = pos.add(0, 0, 1);
        if (isPosSolid(pos3.add(0, -1, 0))) {
            return new BlockData(pos3.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos3.add(-1, 0, 0))) {
            return new BlockData(pos3.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos3.add(1, 0, 0))) {
            return new BlockData(pos3.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos3.add(0, 0, 1))) {
            return new BlockData(pos3.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos3.add(0, 0, -1))) {
            return new BlockData(pos3.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos4 = pos.add(0, 0, -1);
        if (isPosSolid(pos4.add(0, -1, 0))) {
            return new BlockData(pos4.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos4.add(-1, 0, 0))) {
            return new BlockData(pos4.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos4.add(1, 0, 0))) {
            return new BlockData(pos4.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos4.add(0, 0, 1))) {
            return new BlockData(pos4.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos4.add(0, 0, -1))) {
            return new BlockData(pos4.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos19 = pos.add(-2, 0, 0);
        if (isPosSolid(pos1.add(0, -1, 0))) {
            return new BlockData(pos1.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos1.add(-1, 0, 0))) {
            return new BlockData(pos1.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos1.add(1, 0, 0))) {
            return new BlockData(pos1.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos1.add(0, 0, 1))) {
            return new BlockData(pos1.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos1.add(0, 0, -1))) {
            return new BlockData(pos1.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos29 = pos.add(2, 0, 0);
        if (isPosSolid(pos2.add(0, -1, 0))) {
            return new BlockData(pos2.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos2.add(-1, 0, 0))) {
            return new BlockData(pos2.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos2.add(1, 0, 0))) {
            return new BlockData(pos2.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos2.add(0, 0, 1))) {
            return new BlockData(pos2.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos2.add(0, 0, -1))) {
            return new BlockData(pos2.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos39 = pos.add(0, 0, 2);
        if (isPosSolid(pos3.add(0, -1, 0))) {
            return new BlockData(pos3.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos3.add(-1, 0, 0))) {
            return new BlockData(pos3.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos3.add(1, 0, 0))) {
            return new BlockData(pos3.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos3.add(0, 0, 1))) {
            return new BlockData(pos3.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos3.add(0, 0, -1))) {
            return new BlockData(pos3.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos49 = pos.add(0, 0, -2);
        if (isPosSolid(pos4.add(0, -1, 0))) {
            return new BlockData(pos4.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos4.add(-1, 0, 0))) {
            return new BlockData(pos4.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos4.add(1, 0, 0))) {
            return new BlockData(pos4.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos4.add(0, 0, 1))) {
            return new BlockData(pos4.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos4.add(0, 0, -1))) {
            return new BlockData(pos4.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos5 = pos.add(0, -1, 0);
        if (isPosSolid(pos5.add(0, -1, 0))) {
            return new BlockData(pos5.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos5.add(-1, 0, 0))) {
            return new BlockData(pos5.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos5.add(1, 0, 0))) {
            return new BlockData(pos5.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos5.add(0, 0, 1))) {
            return new BlockData(pos5.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos5.add(0, 0, -1))) {
            return new BlockData(pos5.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos6 = pos5.add(1, 0, 0);
        if (isPosSolid(pos6.add(0, -1, 0))) {
            return new BlockData(pos6.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos6.add(-1, 0, 0))) {
            return new BlockData(pos6.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos6.add(1, 0, 0))) {
            return new BlockData(pos6.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos6.add(0, 0, 1))) {
            return new BlockData(pos6.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos6.add(0, 0, -1))) {
            return new BlockData(pos6.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos7 = pos5.add(-1, 0, 0);
        if (isPosSolid(pos7.add(0, -1, 0))) {
            return new BlockData(pos7.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos7.add(-1, 0, 0))) {
            return new BlockData(pos7.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos7.add(1, 0, 0))) {
            return new BlockData(pos7.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos7.add(0, 0, 1))) {
            return new BlockData(pos7.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos7.add(0, 0, -1))) {
            return new BlockData(pos7.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos8 = pos5.add(0, 0, 1);
        if (isPosSolid(pos8.add(0, -1, 0))) {
            return new BlockData(pos8.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos8.add(-1, 0, 0))) {
            return new BlockData(pos8.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos8.add(1, 0, 0))) {
            return new BlockData(pos8.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos8.add(0, 0, 1))) {
            return new BlockData(pos8.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos8.add(0, 0, -1))) {
            return new BlockData(pos8.add(0, 0, -1), EnumFacing.SOUTH);
        }
        BlockPos pos9 = pos5.add(0, 0, -1);
        if (isPosSolid(pos9.add(0, -1, 0))) {
            return new BlockData(pos9.add(0, -1, 0), EnumFacing.UP);
        }
        if (isPosSolid(pos9.add(-1, 0, 0))) {
            return new BlockData(pos9.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (isPosSolid(pos9.add(1, 0, 0))) {
            return new BlockData(pos9.add(1, 0, 0), EnumFacing.WEST);
        }
        if (isPosSolid(pos9.add(0, 0, 1))) {
            return new BlockData(pos9.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (isPosSolid(pos9.add(0, 0, -1))) {
            return new BlockData(pos9.add(0, 0, -1), EnumFacing.SOUTH);
        }
        return null;
    }

    public static boolean isPosSolid(BlockPos pos) {
        Block block = Minecraft.getMinecraft().getTheWorld().getBlockState(pos).getBlock();
        return (block.getMaterial().isSolid() ||
                !block.isTranslucent() ||
                block.isVisuallyOpaque() ||
                BlockLadder.isBlockLaddeer(block) ||
                BlockCarpet.isBlockCarpet(block) ||
                BlockSnow.isBlockSnow(block) ||
                BlockSkull.isBlockSkull(block)) &&
                !block.getMaterial().isLiquid() &&
                !(BlockContainer.isBlockContainer(block));
    }

    public static boolean isRotating() {
        return rotation.getYaw() != 999.0f || rotation.getPitch() != 999.0f;
    }

    public int getBlockCount() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        int n = 0;
        int i = 36;
        while (i < 45) {
            if (thePlayer.getInventoryContainer().getSlot(i).getHasStack()) {
                final ItemStack stack = thePlayer.getInventoryContainer().getSlot(i).getStack();
                final Item item = stack.getItem();
                if (ItemBlock.isItemBlock(item) && isValid(item)) {
                    n += stack.getStackSize();
                }
            }
            ++i;
        }
        return n;
    }

    public static boolean isValid(final Item item) {
        return ItemBlock.isItemBlock(item) && !invalidBlocks.contains((new ItemBlock(item.getWrapObject())).getBlock().getWrapObject());
    }

    /*public void swap(int slot1, int hotbarSlot) {
        mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), slot1, hotbarSlot, 2, mc.getThePlayer());
    }
     */

    /*void getBlock(int hotbarSlot) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        for (int i = 9; i < 45; ++i) {
            if (thePlayer.getInventoryContainer().getSlot(i).getHasStack()
                    && (mc.getCurrentScreen().isNull())) {
                ItemStack is = thePlayer.getInventoryContainer().getSlot(i).getStack();
                if (ItemBlock.isItemBlock(is.getItem())) {
                    ItemBlock block = new ItemBlock(is.getItem().getWrapObject());
                    if (isValid(block)) {
                        if (36 + hotbarSlot != i) {
                            this.swap(i, hotbarSlot);
                        }
                        break;
                    }
                }
            }
        }
    }
     */

    int getBestSpoofSlot() {
        int spoofSlot = 5;

        for (int i = 36; i < 45; ++i) {
            if (!mc.getThePlayer().getInventoryContainer().getSlot(i).getHasStack()) {
                spoofSlot = i - 36;
                break;
            }
        }

        return spoofSlot;
    }

    public boolean isAirBlock(Block block) {
        return block.getMaterial().isReplaceable()
                && (!(BlockSnow.isBlockSnow(block)) || block.getMaxY() <= 0.125);
    }

    public static double randomNumber(double max, double min) {
        return Math.random() * (max - min) + min;
    }

    public boolean isMoving() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        return movementInput.getMoveForward() != 0F || movementInput.getMoveStrafe() != 0F;
    }

    private static class BlockData {
        private Vec3 vec;
        private final BlockPos pos;
        private final Enum facing;

        public BlockData(final BlockPos pos, final Enum facing) {
            this.pos = pos;
            this.facing = facing;
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

        public Enum getEnumFacing() {
            return this.facing;
        }
    }

    private static FloatBuffer setColorBuffer(final float p_setColorBuffer_0_, final float p_setColorBuffer_1_,
                                              final float p_setColorBuffer_2_, final float p_setColorBuffer_3_) {
        colorBuffer.clear();
        colorBuffer.put(p_setColorBuffer_0_).put(p_setColorBuffer_1_).put(p_setColorBuffer_2_).put(p_setColorBuffer_3_);
        colorBuffer.flip();
        return colorBuffer;
    }

}