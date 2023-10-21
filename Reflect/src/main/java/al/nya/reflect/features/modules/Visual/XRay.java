package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.events.events.world.EventBlockRenderSide;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.block.*;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.EntityRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.world.IBlockAccess;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class XRay extends Module {
    public ModeValue mode = new ModeValue("Mode",Mode.Normal,Mode.values());
    public DoubleValue opacity = new DoubleValue("Opacity", 255, 0, 160,"0");
    public DoubleValue range = new DoubleValue("Range", 500, 0, 25,"0");
    public DoubleValue extremeRange = new DoubleValue("ExtreMeRange", 8, 0, 4,"0");
    public DoubleValue delay = new DoubleValue("Delay", 30, 1, 10,"0");
    public static OptionValue translucent = new OptionValue("Translucent", true);
    public OptionValue update = new OptionValue("Update", true);
    public OptionValue autoUpdate = new OptionValue("AutoUpdate", true);
    public OptionValue coal = new OptionValue("Coal", true);
    public OptionValue iron = new OptionValue("Iron", true);
    public OptionValue gold = new OptionValue("Gold", true);
    public OptionValue lapisLazuli = new OptionValue("LapisLazuli", true);
    public OptionValue diamond = new OptionValue("Diamond", true);
    public OptionValue redStone = new OptionValue("RedStone", true);
    public OptionValue emerald = new OptionValue("Emerald", false);
    public OptionValue quartz = new OptionValue("Quartz", false);
    public OptionValue water = new OptionValue("Water", false);
    public OptionValue lava = new OptionValue("Lava", false);
    public OptionValue tracer = new OptionValue("Tarcer", false);
    public OptionValue block = new OptionValue("Block", false);
    public static OptionValue cave = new OptionValue("Cave", true);
    public OptionValue esp = new OptionValue("ESP", true);
    public OptionValue extreme = new OptionValue("ExtreMe", true);

    public static LinkedList<Integer> antiXRayBlocks = new LinkedList<>();
    public static CopyOnWriteArrayList<XRayBlock> xRayBlocks = new CopyOnWriteArrayList<>();
    public static List<Block> needXrayBlocks = Arrays.asList(Blocks.coal_ore, Blocks.iron_ore, Blocks.gold_ore,
            Blocks.redstone_ore, Blocks.lapis_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.quartz_ore,
            Blocks.coal_block, Blocks.iron_block, Blocks.gold_block, Blocks.diamond_block, Blocks.emerald_block,
            Blocks.redstone_block, Blocks.lapis_block, Blocks.mob_spawner, Blocks.end_portal_frame, Blocks.command_block);
    public static List<Object> needXrayBlocksObject = new ArrayList<>();
    Block[] extremeBlocks = new Block[]{Blocks.obsidian, Blocks.clay, Blocks.mossy_cobblestone, Blocks.diamond_ore,
            Blocks.redstone_ore, Blocks.iron_ore, Blocks.coal_ore, Blocks.lapis_ore, Blocks.gold_ore,
            Blocks.emerald_ore, Blocks.quartz_ore};

    public static int alpha;
    long lastTime = 0;
    public static boolean isEnable = false;
    public XRay() {
        super("XRay", ModuleType.Visual);
        this.addValue(mode);
        this.addValues(opacity, range, extremeRange, delay);
        this.addValues(translucent, update, autoUpdate, coal, iron, gold, lapisLazuli, diamond, redStone, emerald, quartz, water, lava, tracer, block, cave, esp, extreme);
        for (Block needXrayBlock : needXrayBlocks) {
            needXrayBlocksObject.add(needXrayBlock.getWrapObject());
        }
    }
    @Override
    public void onEnable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (extreme.getValue() && thePlayer.getPosY() <= 25) doExtreme();
        if (update.getValue()) mc.getRenderGlobal().loadRenderers();
        int posX = (int) thePlayer.getPosX();
        int posY = (int) thePlayer.getPosY();
        int posZ = (int) thePlayer.getPosZ();
        mc.getRenderGlobal().markBlockRangeForRenderUpdate(posX - 900, posY - 900, posZ - 900, posX + 900, posY + 900, posZ + 900);
        addAntiXRayBlocks();
        isEnable = true;
    }
    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (mc.getTheWorld().isNull() || mc.getThePlayer().isNull()) return;
        if (alpha != opacity.getValue().intValue()) {
            mc.getRenderGlobal().loadRenderers();
            alpha = opacity.getValue().intValue();
        } else if (autoUpdate.getValue()) {
            if (System.currentTimeMillis() - lastTime > 1000 * delay.getValue()) {
                lastTime = System.currentTimeMillis();
                mc.getRenderGlobal().loadRenderers();
            }
        }
    }
    public static List<Object> getXrayBlocks() {
        return needXrayBlocksObject;
    }
    @EventTarget
    public void onBlockRenderSide(EventBlockRenderSide e) {
        WorldClient theWorld = mc.getTheWorld();
        for (int n : antiXRayBlocks) {
            XRayBlock xRayBlock;
            if (Block.getIdFromBlock(e.getBlock()) != n) continue;
            if (cave.getValue()) {
                if (mode.getValue() == Mode.Normal) {
                    int n2 = e.getSide() == EnumFacing.DOWN &&
                            e.getMinY() > 0.0 ? 1 : (e.getSide() == EnumFacing.UP &&
                            e.getMaxY() < 1.0 ? 1 : (e.getSide() == EnumFacing.NORTH &&
                            e.getMinZ() > 0.0 ? 1 : (e.getSide() == EnumFacing.SOUTH &&
                            e.getMaxZ() < 1.0 ? 1 : (e.getSide() == EnumFacing.WEST &&
                            e.getMinX() > 0.0 ? 1 : (e.getSide() == EnumFacing.EAST &&
                            e.getMaxX() < 1.0 ? 1 :
                            (!e.getBlockAccess().getBlockState(e.getPos()).getBlock().isOpaqueCube() ? 1 : 0))))));
                    int n3 = 0;
                    BlockPos[] blockPos1 = new BlockPos[6];
                    for (int i = 0; i < blockPos1.length; ++i) {
                        switch (i) {
                            case 0: {
                                blockPos1[i] = e.getPos().up();
                                continue;
                            }
                            case 1: {
                                blockPos1[i] = e.getPos().down();
                                continue;
                            }
                            case 2: {
                                blockPos1[i] = e.getPos().south();
                                continue;
                            }
                            case 3: {
                                blockPos1[i] = e.getPos().north();
                                continue;
                            }
                            case 4: {
                                blockPos1[i] = e.getPos().east();
                                continue;
                            }
                            case 5: {
                                blockPos1[i] = e.getPos().west();
                            }
                        }
                    }
                    for (BlockPos blockPos : blockPos1) {
                        Block block = theWorld.getBlockState(blockPos).getBlock();
                        if (!(BlockAir.isBlockAir(block)) && !(BlockLiquid.isBlockLiquid(block))) continue;
                        n3 = 1;
                    }
                    if (Arrays.stream(blockPos1).allMatch(blockPos -> theWorld.getBlockState(blockPos).getBlock().equals(Blocks.bedrock))) {
                        n3 = 1;
                    }
                    if (n2 == 0 && n3 == 0) {
                        continue;
                    }
                } else if (mode.getValue() == Mode.Hypixel &&
                        !this._FoodByteMethod1(e.getBlockAccess(), e.getPos(), e.getMinY(), e.getMaxY(), e.getMinZ(), e.getMaxZ(), e.getMinX(), e.getMaxX())) continue;
            }
            e.setShouldRender(true);
            if (!esp.getValue() && !tracer.getValue()) {
                return;
            }
            EntityPlayerSP thePlayer = mc.getThePlayer();
            double toPlayerRange = e.getPos().distanceTo(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());
            BlockPos offset = e.getPos().offset(e.getSide(),-1);
            if (toPlayerRange > (range.getValue()) || xRayBlocks.contains((xRayBlock = new XRayBlock(Math.round((float)offset.getX()),Math.round((float)offset.getY()), Math.round((float)offset.getZ()), e.getBlock())))) continue;
            xRayBlocks.add(xRayBlock);
        }
    }

    private boolean _FoodByteMethod1(IBlockAccess iBlockAccess, BlockPos blockPos, double d, double d2, double d3, double d4, double d5, double d6) {
        Enum<?>[] enumFacingArray = EnumFacing.values();
        for (Enum<?> enumFacing : enumFacingArray) {
            if (!this._FoodByteMethod0(iBlockAccess, blockPos.offset(enumFacing), enumFacing, d, d2, d3, d4, d5, d6))
                continue;
            return true;
        }
        return false;
    }

    private boolean _FoodByteMethod0(IBlockAccess iBlockAccess, BlockPos blockPos, Enum<?> enumFacing, double d, double d2, double d3, double d4, double d5, double d6) {
        return (enumFacing == EnumFacing.DOWN &&
                d > 0.0 || enumFacing == EnumFacing.UP &&
                d2 < 1.0 || enumFacing == EnumFacing.NORTH &&
                d3 > 0.0 || enumFacing == EnumFacing.SOUTH &&
                d4 < 1.0 || enumFacing == EnumFacing.WEST &&
                d5 > 0.0 || enumFacing == EnumFacing.EAST &&
                d6 < 1.0 || !iBlockAccess.getBlockState(blockPos).getBlock().isOpaqueCube() ? 1 : 0) != 0;
    }
    @EventTarget
    public void on3D(EventRender3D eventRender3D) {
        GameSettings gameSettings = mc.getGameSettings();
        RenderManager renderManager = mc.getRenderManager();
        EntityRenderer entityRenderer = mc.getEntityRenderer();
        Timer timer = mc.getTimer();
        WorldClient theWorld = mc.getTheWorld();
        for (XRayBlock xRayBlock : xRayBlocks) {
            double d;
            double d2;
            Color color;
            BlockPos blockPos = new BlockPos(xRayBlock.getX(), xRayBlock.getY(), xRayBlock.getZ());
            if (!(BlockOre.isBlockOre(theWorld.getBlockState(blockPos).getBlock()))) {
                xRayBlocks.remove(xRayBlock);
                continue;
            }
            color = new Color(12, 12, 12);
            String string = xRayBlock.getBlock().getUnlocalizedName();
            switch (string) {
                case "tile.oreRedstone":
                    color = new Color(0xFF0000);
                    break;
                case "tile.oreEmerald":
                    color = new Color(0, 255, 0);
                    break;
                case "tile.blockRedstone":
                    color = new Color(0xFF0000);
                    break;
                case "tile.blockLapis":
                    color = new Color(255);
                    break;
                case "tile.blockDiamond":
                    color = new Color(0, 255, 255);
                    break;
                case "tile.netherquartz":
                    color = new Color(255, 255, 255);
                    break;
                case "tile.blockGold":
                    color = new Color(0xFFFF00);
                    break;
                case "tile.blockIron":
                    color = new Color(210, 210, 210);
                    break;
                case "tile.oreLapis":
                    color = new Color(255);
                    break;
                case "tile.blockEmerald":
                    color = new Color(0, 255, 0);
                    break;
                case "tile.oreDiamond":
                    color = new Color(0, 255, 255);
                    break;
                case "tile.oreGold":
                    color = new Color(0xFFFF00);
                    break;
                case "tile.oreIron":
                    color = new Color(210, 210, 210);
                    break;
            }
            if (esp.getValue()) {
                double d3 = xRayBlock.getX() - renderManager.getRenderPosX();
                d2 = xRayBlock.getY() - renderManager.getRenderPosY();
                d = xRayBlock.getZ() - renderManager.getRenderPosZ();
                boolean isBlockStairs = BlockStairs.isBlockStairs(xRayBlock.getBlock());
                int id = Block.getIdFromBlock(xRayBlock.getBlock());
                double d4 = !(isBlockStairs) && id != 134 ? xRayBlock.getBlock().getBlockBoundsMinX() : 0.0;
                double d5 = !(isBlockStairs) && id != 134 ? xRayBlock.getBlock().getBlockBoundsMinY() : 0.0;
                double d6 = !(isBlockStairs) && id != 134 ? xRayBlock.getBlock().getBlockBoundsMinZ() : 0.0;
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770,771);
                GL11.glDisable(3553);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glLineWidth(1.0f);
                GL11.glColor4f(((float)color.getRed() / 255.0f), (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 0.627451f);
                RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(d3 + d4, d2 + d5, d + d6, d3 + xRayBlock.getBlock().getBlockBoundsMaxX(), d2 + xRayBlock.getBlock().getBlockBoundsMaxY(), d + xRayBlock.getBlock().getBlockBoundsMaxZ()));
                GL11.glColor3f(1.0f, 1.0f,1.0f);
                GL11.glEnable(3553);
                GL11.glEnable(2929);
                GL11.glDepthMask(true);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            if (!(tracer.getValue())) continue;
            double d7 = xRayBlock.getX() - renderManager.getRenderPosX();
            d2 = xRayBlock.getY() - renderManager.getRenderPosY();
            d = xRayBlock.getZ() - renderManager.getRenderPosZ();
            boolean bl = gameSettings.getViewBobbing();
            gameSettings.setViewBobbing(false);
            entityRenderer.setupCameraTransform(timer.getRenderPartialTicks(), 2);
            gameSettings.setViewBobbing(bl);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glEnable(2848);
            GL11.glDisable(3553);
            GL11.glColor4f(((float)color.getRed() / 255.0f),((float)color.getGreen() / 255.0f),((float)color.getBlue() / 255.0f),1.0f);
            GL11.glLineWidth(0.5f);
            GL11.glBegin(1);
            GL11.glVertex3d(0.0, mc.getThePlayer().getEyeHeight(), 0.0);
            GL11.glVertex3d(d7, d2, d);
            GL11.glEnd();
            GL11.glDisable(2848);
            GL11.glEnable(3553);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
    }
    public void doExtreme() {
        int n = extremeRange.getValue().intValue();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double posX = thePlayer.getPosX();
        double posY = thePlayer.getPosY();
        double posZ = thePlayer.getPosZ();
        WorldClient theWorld = mc.getTheWorld();
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        for (int i = -n; i < n; ++i) {
            for (int j = n; j > -n; --j) {
                for (int k = -n; k < n; ++k) {
                    XRayBlock xRayBlock;
                    int n2 = (int) Math.floor(posX) + i;
                    int n3 = (int) Math.floor(posY) + j;
                    int n4 = (int) Math.floor(posZ) + k;
                    if (!(thePlayer.getDistanceSq(posX + (double) i, posY + (double) j, posZ + (double) k) <= 16.0))
                        continue;
                    Block block = theWorld.getBlockState(new BlockPos(n2, n3, n4)).getBlock();
                    int n5 = 0;
                    Block[] blockArray = this.extremeBlocks;
                    Block[] blockArray2 = this.extremeBlocks;
                    int n6 = blockArray.length;
                    for (int i2 = 0; i2 < n6; ++i2) {
                        Block block2 = blockArray2[i2];
                        if (!block.equals(block2)) continue;
                        n5 = 1;
                        break;
                    }
                    int n7 = 0;
                    for (XRayBlock xRayBlockTemp : xRayBlocks) {
                        if (!xRayBlockTemp.samePos(new BlockPos(n2, n3, n4))) continue;
                        n7 = 1;
                        break;
                    }
                    if (n5 == 0 || n7 != 0) continue;
                    xRayBlock = new XRayBlock(new BlockPos(n2, n3, n4));
                    networkManager.sendPacketNoEvent(new C07PacketPlayerDigging(C07Action.ABORT_DESTROY_BLOCK,xRayBlock.getBlockPos(),EnumFacing.UP));
                    xRayBlocks.add(new XRayBlock(n2, n3, n4, mc.getTheWorld().getBlockState(xRayBlock.blockPos).getBlock()));
                }
            }
        }
    }
    private void addAntiXRayBlocks() {
        if (coal.getValue()) {
            antiXRayBlocks.add(16);
            if (block.getValue()) {
                antiXRayBlocks.add(173);
            }
        }
        if (iron.getValue()) {
            antiXRayBlocks.add(15);
            if (block.getValue()) {
                antiXRayBlocks.add(42);
            }
        }
        if (gold.getValue()) {
            antiXRayBlocks.add(14);
            if (block.getValue()) {
                antiXRayBlocks.add(41);
            }
        }
        if (lapisLazuli.getValue()) {
            antiXRayBlocks.add(21);
            if (block.getValue()) {
                antiXRayBlocks.add(22);
            }
        }
        if (diamond.getValue()) {
            antiXRayBlocks.add(56);
            if (block.getValue()) {
                antiXRayBlocks.add(57);
            }
        }
        if (redStone.getValue()) {
            antiXRayBlocks.add(73);
            antiXRayBlocks.add(74);
            if (block.getValue()) {
                antiXRayBlocks.add(152);
            }
        }
        if (emerald.getValue()) {
            antiXRayBlocks.add(129);
            if (block.getValue()) {
                antiXRayBlocks.add(133);
            }
        }
        if (quartz.getValue()) {
            antiXRayBlocks.add(153);
        }
        if (water.getValue()) {
            antiXRayBlocks.add(8);
            antiXRayBlocks.add(9);
        }
        if (lava.getValue()) {
            antiXRayBlocks.add(10);
            antiXRayBlocks.add(11);
        }
    }
    public static int getColor(int rgb) {
        Color temp = new Color(rgb);
        return new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), alpha).getRGB();
    }
    @Override
    public void onDisable() {
        mc.getRenderGlobal().loadRenderers();
        antiXRayBlocks.clear();
        xRayBlocks.clear();
        isEnable = false;
    }
    public enum Mode {
        Normal,
        Hypixel
    }
    class XRayBlock {
        private final double x;
        private final double y;
        private final double z;
        private final Block block;
        private BlockPos blockPos;

        public XRayBlock(BlockPos blockPos) {
            this.blockPos = blockPos;
            this.x = blockPos.getX();
            this.y = blockPos.getY();
            this.z = blockPos.getZ();
            this.block = mc.getTheWorld().getBlockState(blockPos).getBlock();
        }

        public XRayBlock(double d, double d2, double d3, Block block) {
            this.x = d;
            this.y = d2;
            this.z = d3;
            this.block = block;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        public double getZ() {
            return this.z;
        }

        public Block getBlock() {
            return this.block;
        }

        public BlockPos getBlockPos() {
            return blockPos;
        }

        public boolean samePos(BlockPos blockPos) {
            return ((int)this.x == blockPos.getX() && (int)this.y == blockPos.getY() && this.z == (double)blockPos.getZ() ? 1 : 0) != 0;
        }
    }
}
