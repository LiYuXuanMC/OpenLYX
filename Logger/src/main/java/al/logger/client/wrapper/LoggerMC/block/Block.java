package al.logger.client.wrapper.LoggerMC.block;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.world.IBlockAccess;
import al.logger.client.wrapper.LoggerMC.world.World;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.Block",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.block.Block",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Block extends IWrapper {
@ClassInstance    
public static Class BlockClass;
    @WrapField(mcpName = "blockMaterial",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "blockMaterial",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field blockMaterial;
    @WrapMethod(mcpName = "getBlockBoundsMaxX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMaxX;
    @WrapMethod(mcpName = "getBlockBoundsMaxY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMaxY;
    @WrapMethod(mcpName = "getBlockBoundsMaxZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMaxZ;
    @WrapMethod(mcpName = "getBlockBoundsMinX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMinX;
    @WrapMethod(mcpName = "getBlockBoundsMinY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMinY;
    @WrapMethod(mcpName = "getBlockBoundsMinZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockBoundsMinZ;
    @WrapField(mcpName = "translucent",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "translucent",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field translucent;
    @WrapMethod(mcpName = "canCollideCheck",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "canCollideCheck",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method canCollideCheck;
    @WrapMethod(mcpName = "isVisuallyOpaque",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isVisuallyOpaque;
    @WrapField(mcpName = "defaultBlockState",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field defaultBlockState;
    @WrapMethod(mcpName = "causesSuffocation",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method causesSuffocation;
    @WrapField(mcpName = "maxY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field maxY;
    @WrapField(mcpName = "maxX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field maxX;
    @WrapField(mcpName = "maxZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field maxZ;
    @WrapField(mcpName = "minY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field minY;
    @WrapField(mcpName = "minX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field minX;
    @WrapField(mcpName = "minZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field minZ;
    @WrapMethod(mcpName = "getBoundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBoundingBox;
    @WrapMethod(mcpName = "setBlockBoundsBasedOnState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setBlockBoundsBasedOnState",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setBlockBoundsBasedOnState;
    @WrapMethod(mcpName = "getSelectedBoundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getSelectedBoundingBox",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getSelectedBoundingBox;
    @WrapMethod(mcpName = "shouldSideBeRendered",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "shouldSideBeRendered",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method shouldSideBeRendered;
    @WrapMethod(mcpName = "getBlockLayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockLayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockLayer;
    @WrapMethod(mcpName = "getMixedBrightnessForBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getPackedLightmapCoords",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMixedBrightnessForBlock;
    @WrapMethod(mcpName = "getAmbientOcclusionLightValue",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getAmbientOcclusionLightValue",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getAmbientOcclusionLightValue;
    @WrapMethod(mcpName = "isOpaqueCube",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isOpaqueCube",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isOpaqueCube;
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getUnlocalizedName;
    @WrapMethod(mcpName = "getIdFromBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getIdFromBlock",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getIdFromBlock;
    @WrapMethod(mcpName = "getBlockHardness",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockHardness",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockHardness;
    @WrapMethod(mcpName = "getBlockById",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlockById",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlockById;
    @WrapMethod(mcpName = "getMetaFromState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMetaFromState",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMetaFromState;

    @WrapMethod(mcpName = "isCollidable",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isCollidable",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isCollidable;
    @WrapMethod(mcpName = "isBlockNormalCube",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isBlockNormalCube;
    @WrapMethod(mcpName = "isFullCube",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isFullBlock;
    @WrapMethod(mcpName = "addCollisionBoxesToList",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method addCollisionBoxesToList;
    @WrapField(mcpName = "blockState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field blockState;
    public Block(Object obj) {
        super(obj);
    }
    public int getMetaFromState(IBlockState state)
    {
        return (int)invoke(getMetaFromState,state);
    }
    public static Block getBlockById(int id) {
        return new Block(ReflectUtil.invoke(getBlockById,null,id));
    }

    public Material getMaterial(){
        return new Material(getField(blockMaterial));
    }
    public boolean isTranslucent(){
        return (boolean) getField(translucent);
    }
    public boolean isOpaqueCube(){
        return (boolean) invoke(isOpaqueCube);
    }
    public static int getIdFromBlock(Block block){
        return (int) ReflectUtil.invoke(getIdFromBlock,null,block.getWrappedObject());
    }
    public String getUnlocalizedName(){
        return (String) invoke(getUnlocalizedName);
    }
    public boolean isVisuallyOpaque(){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            return (boolean) invoke(causesSuffocation,getField(defaultBlockState));
        }
        return  (boolean) invoke(isVisuallyOpaque);
    }
    public float getBlockHardness(World world,BlockPos pos){
        return (float) invoke(getBlockHardness,world.getWrappedObject(),pos.getWrappedObject());
    }
    public IBlockState getDefaultBlockState(){
        return new IBlockState(getField(defaultBlockState));
    }
    public double getMaxY(){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            return getBoundingBox(getDefaultBlockState(),null,BlockPos.wrap(null)).getMaxY();
        }
        return (double) getField(maxY);
    }
    public AxisAlignedBB getBoundingBox(IBlockState ib, IBlockAccess IBlockAccess, BlockPos bp){
        return new AxisAlignedBB(invoke(getBoundingBox,ib.getWrappedObject(),IBlockAccess.getWrappedObject(),bp.getWrappedObject()));
    }
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        invoke(setBlockBoundsBasedOnState,worldIn.getWrappedObject(),pos.getWrappedObject());
    }
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        return new AxisAlignedBB(invoke(getSelectedBoundingBox,worldIn.getWrappedObject(),pos.getWrappedObject()));
    }
    public boolean canCollideCheck(IBlockState blockState,boolean hitIfLiquid){
        return (boolean) invoke(canCollideCheck,blockState.getWrappedObject(),hitIfLiquid);
    }

    public boolean isCollidable(){
        return (boolean) invoke(isCollidable,this.getWrappedObject());
    }

    public boolean isBlockNormalCube(){
        return (boolean) invoke(isBlockNormalCube,this.getWrappedObject());
    }

    public boolean isFullBlock(){
        return (boolean) invoke(isFullBlock,this.getWrappedObject());
    }
    public double getBlockBoundsMaxX() {
        return (double) getField(maxX);
    }
    public double getBlockBoundsMaxY() {
        return (double) getField(maxY);
    }
    public double getBlockBoundsMaxZ() {
        return (double) getField(maxZ);
    }
    public double getBlockBoundsMinX() {
        return (double) getField(minX);
    }
    public double getBlockBoundsMinY() {
        return (double) getField(minY);
    }
    public double getBlockBoundsMinZ() {
        return (double) getField(minZ);
    }

}
