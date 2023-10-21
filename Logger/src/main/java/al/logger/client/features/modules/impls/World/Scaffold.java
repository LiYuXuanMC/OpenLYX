package al.logger.client.features.modules.impls.World;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.lock.Locks;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.block.*;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.entity.InventoryPlayer;
import al.logger.client.wrapper.LoggerMC.item.Container;
import al.logger.client.wrapper.LoggerMC.item.Item;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.network.client.C0APacketAnimation;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.utils.*;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;
import al.logger.client.wrapper.utils.WrapperAdaptArrayList;

import java.util.Arrays;

public class Scaffold extends Module {
    private ModeValue placeMode = new ModeValue("PlaceMode" , PlaceMode.Post , PlaceMode.values());
    private ModeValue rotationMode = new ModeValue("RotationMode" , RotationMode.WatchDog , RotationMode.values());
    private OptionValue silent = new OptionValue("SilentRotation", true);
    private OptionValue keepY = new OptionValue("KeepY", false);
    private OptionValue eagle = new OptionValue("Eagle" , false);
    private DoubleValue delay = new DoubleValue("Delay" , 2000 , 0 , 200 , 1);
    private OptionValue tower = new OptionValue("Tower", false);
    private ModeValue towerMode = new ModeValue("TowerMode" , TowerMode.Normal , TowerMode.values());
    private OptionValue heldBlock = new OptionValue("HeldBlock", true);
    private OptionValue swing = new OptionValue("Swing", true);
    public static OptionValue sprint = new OptionValue("Sprint", false);
    public static ModeValue sprintMode = new ModeValue("SprintMode" , SprintMode.None , SprintMode.values());

    private OptionValue telly = new OptionValue("Telly" , false);
    private final static WrapperAdaptArrayList<Block> invalidBlocks = WrapperAdaptArrayList.toWrapperAdaptArrayList(Arrays.asList(Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.lava,
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
    TimerUtils delaytimer = new TimerUtils();
    private float yaw = Float.NaN,pitch = Float.NaN;
    private int originSlot = -1;
    private int slot = -1;
    private BlockData data;
    private ItemStack currentblock;
    private double y;
    public Scaffold() {
        super("Scaffold",Category.World);
        eagle.addCallBack(() -> !keepY.getValue());
        towerMode.addCallBack(() -> tower.getValue());
        sprintMode.addCallBack(() -> sprint.getValue());
        addValues(placeMode,rotationMode ,silent , keepY , eagle , delay , tower , towerMode , heldBlock , swing , sprint,sprintMode,telly);
    }
    @Override
    public void onEnable(){
        this.yaw = Float.NaN;
        this.pitch = Float.NaN;
        this.data = null;
        if (heldBlock.getValue()){
            originSlot = mc.getThePlayer().getInventory().currentItem();
        }
    }
    @Override
    public void onDisable(){
        if (heldBlock.getValue()){
            mc.getThePlayer().getInventory().currentItem(originSlot);
        }
    }
    @Listener(priority = 700)
    public void onPreUpdate(EventPreUpdate preUpdate){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (getBlockCount() == 0){
            setDisableNoNotification();
            logger.getNotificationManager().addNotification(new Notification("No blocks in hotbar", Notification.NotificationType.Error));
            return;
        }
        logger.getLockManager().getLock(Locks.Scaffolding).setLocked(true);
        double posY = thePlayer.getPosY();
        double posZ = thePlayer.getPosZ();
        double posX = thePlayer.getPosX();

        if (sprint.getValue() && sprintMode.getValue() == SprintMode.WatchDog){
            mc.getGameSettings().getKeyBindSprint().setPressed(false);
            mc.getThePlayer().setSprinting(false);
            if (mc.getThePlayer().isPotionActive(Potion.moveSpeed)) {
                mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * 0.95);
                mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionZ() * 0.95);
            } else {
                mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * 0.99);
                mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionZ() * 0.99);
            }
        }



        if (eagle.getValue()){
            if (!(mc.getGameSettings().getKeyBindJump().isKeyDown())) {
                BlockPos pos = new BlockPos(thePlayer.getPosX(), thePlayer.getPosY() - 1.0, thePlayer.getPosZ());
                KeyBinding sneak = mc.getGameSettings().getKeyBindSneak();
                if (theWorld.getBlockState(pos).getBlock().equals(Blocks.air)) {
                    sneak.setPressed(true);
                } else {
                    sneak.setPressed(false);
                }
            }
        }

        if (keepY.getValue()){
            if (thePlayer.isOnGround()){
                y = posY;
            }else {
                if (!isMoving()){
                    y = posY;
                }
            }
        }else {
            y = posY;
        }

        this.data = this.getBlockData(new BlockPos(posX, y - 1.0, posZ)) == null ? this.getBlockData(new BlockPos(posX, y - 1.0, posZ).down()) : this.getBlockData(new BlockPos(posX, y - 1.0, posZ));
        this.slot = this.getBestSlot();
        if (heldBlock.getValue())
            thePlayer.getInventory().currentItem(slot);
        //this.currentblock = thePlayer.getInventoryContainer().getSlot(slot + 36).getStack();
        if (this.slot == -1 || this.getBlockCount() <= 0 || this.data == null
                || !(isMoving() || mc.getGameSettings().getKeyBindJump().isKeyDown())) {
            return;
        }
        float[] rotation = getRotation();
        if (silent.getValue()){
            preUpdate.setRotation(rotation[0],rotation[1]);
        }else {
            yaw = rotation[0];
            pitch = rotation[1];
        }
        if (tower.getValue() && isAirUnder(theWorld,thePlayer) && MovementUtils.isOnGround(1.15)
                && mc.getGameSettings().getKeyBindJump().isKeyDown() && !PlayerUtils.MovementInput()) {

            if (silent.getValue()){
                preUpdate.setPitch(90);
            }else {
                yaw = Float.NaN;
                pitch = 90;
            }
            if ((towerMode.getValue() == TowerMode.Normal) && isAirUnder(theWorld,thePlayer) && isOnGround(theWorld,thePlayer,1.12)
                    && mc.getGameSettings().getKeyBindJump().isKeyDown() && !hasMovementInput()) {

                thePlayer.setMotionX(0);
                thePlayer.setMotionY(0.42f);
                thePlayer.setMotionZ(0);
                thePlayer.getMovementInput().setMoveForward(0);
                thePlayer.getMovementInput().setMoveStrafe(0);
            }

            if(towerMode.getValue() == TowerMode.Matrix){
                if (mc.getGameSettings().getKeyBindJump().isKeyDown() && isAirUnder(theWorld,thePlayer) && mc.getThePlayer().getMotionY() < 0.2) {
                    mc.getThePlayer().setMotionY(0.42F);
                    preUpdate.setOnGround(true);
                }
            }
        }
        if (placeMode.getValue() == PlaceMode.Pre){
            placeBlock();
        }
    }
    @Listener
    public void onPostUpdate(EventPostUpdate postUpdate){
        if (placeMode.getValue() == PlaceMode.Post){
            placeBlock();
        }
    }
    @Listener
    public void onTick(EventTick tick){
        if (placeMode.getValue() == PlaceMode.Tick){
            placeBlock();
        }
    }
    public boolean hasMovementInput() {
        GameSettings gameSettings = mc.getGameSettings();
        return gameSettings.getKeyBindForward().isKeyDown() || gameSettings.getKeyBindLeft().isKeyDown() || gameSettings.getKeyBindRight().isKeyDown() || gameSettings.getKeyBindBack().isKeyDown();
    }
    public boolean isAirUnder(WorldClient theWorld,Entity ent) {
        return theWorld.getBlockState(new BlockPos(ent.getPosX(), ent.getPosY() - 1, ent.getPosZ())).getBlock().equals(Blocks.air);
    }
    public boolean isOnGround(WorldClient theWorld,EntityPlayerSP thePlayer,double height) {
        return !theWorld.getCollidingBoundingBoxes(thePlayer, thePlayer.getEntityBoundingBox().offset(0.0D, -height, 0.0D)).isEmpty();
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
    private float[] getRotation(){
        if (rotationMode.getValue() == RotationMode.WatchDog){
            return getRotationsHypixel(data.pos, data.facing);
        }
        if (rotationMode.getValue() == RotationMode.Back) {
            return new float[]{mc.getThePlayer().getRotationYaw() - 180, 77};
        }
        if (rotationMode.getValue() == RotationMode.Matrix){
            return RotationUtils.getAngles(mc.getThePlayer());
        }
        return new float[]{Float.NaN,Float.NaN};
    }
    private float[] getRotationsHypixel(BlockPos block, Enum face) {
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

    private int getBestSlot(){
        Container container = mc.getThePlayer().getInventoryContainer();
        int usableSlot = -1;
        int slotBlocks = -1;
        for (int i = 0; i < 9; ++i) {
            if (!container.getSlot(36+i).getHasStack()
                    || !(ItemBlock.isItemBlock(container.getSlot(i + 36).getStack()
                    .getItem())))
                continue;
            ItemBlock itemBlock = new ItemBlock(container.getSlot(i + 36).getStack().getItem().getWrappedObject());
            if (invalidBlocks.contains(itemBlock)) continue;
            if (slotBlocks < container.getSlot(i + 36).getStack().getStackSize()){
                usableSlot = i;
                slotBlocks = container.getSlot(i + 36).getStack().getStackSize();
            }
        }
        return usableSlot;
    }
    public void placeBlock() {
        if (slot == -1 && data == null){
            return;
        }
        InventoryPlayer inventory = mc.getThePlayer().getInventory();
        int last = -1;
        if (!heldBlock.getValue()){
            last = inventory.currentItem();
            inventory.currentItem(this.slot);
        }

        if (delaytimer.hasTimeElapsed(delay.getValue().longValue())) {
            if (mc.getPlayerController().onPlayerRightClick(mc.getThePlayer(), mc.getTheWorld(),
                    mc.getThePlayer().getCurrentEquippedItem(), this.data.getBlockPos(), this.data.getEnumFacing(),
                    getVec3(this.data.getBlockPos(), this.data.getEnumFacing()))) {
                if (!inventory.getStackInSlot(inventory.currentItem()).getItem().isNull()
                        && (ItemBlock.isItemBlock(inventory.getStackInSlot(inventory.currentItem())
                        .getItem()))) {

                    if (swing.getValue()){
                        mc.getThePlayer().getSendQueue().addToSendQueue(new C0APacketAnimation());
                    }
                }
            }
            delaytimer.reset();
        }
        if (!heldBlock.getValue() && last != -1){
            inventory.currentItem(last);
        }
    }
    private static Vec3 getVec3(BlockPos pos, Enum face) {
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
    private boolean isMoving() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        return movementInput.getMoveForward() != 0F || movementInput.getMoveStrafe() != 0F;
    }
    public static double randomNumber(double max, double min) {
        return Math.random() * (max - min) + min;
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
            i++;
        }
        return n;
    }
    public static boolean isValid(final Item item) {
        return ItemBlock.isItemBlock(item) && !invalidBlocks.contains((new ItemBlock(item.getWrappedObject())).getBlock());
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
    enum PlaceMode {
        Pre,
        Post,
        Tick
    }
    enum TowerMode {
        Normal,
        Matrix
    }
    enum RotationMode {
        WatchDog,
        Back,
        Matrix,
        Test
    }

   public enum SprintMode{
        Normal,
        None,
        WatchDog
    }
}
