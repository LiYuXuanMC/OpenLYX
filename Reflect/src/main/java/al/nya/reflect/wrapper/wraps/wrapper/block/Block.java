package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.world.IBlockAccess;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.Block",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.Block",targetMap = Maps.Srg1_12_2)
public class Block extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.block.Block",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.block.Block",targetMap = Maps.Srg1_12_2)
    public static Class BlockClass;
    @WrapField(mcpName = "blockMaterial",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "blockMaterial",targetMap = Maps.Srg1_12_2)
    public static Field blockMaterial;
    @WrapMethod(mcpName = "getBlockBoundsMaxX",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMaxX;
    @WrapMethod(mcpName = "getBlockBoundsMaxY",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMaxY;
    @WrapMethod(mcpName = "getBlockBoundsMaxZ",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMaxZ;
    @WrapMethod(mcpName = "getBlockBoundsMinX",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMinX;
    @WrapMethod(mcpName = "getBlockBoundsMinY",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMinY;
    @WrapMethod(mcpName = "getBlockBoundsMinZ",targetMap = Maps.Srg1_8_9)
    public static Method getBlockBoundsMinZ;
    @WrapField(mcpName = "translucent",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "translucent",targetMap = Maps.Srg1_12_2)
    public static Field translucent;
    @WrapMethod(mcpName = "canCollideCheck",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "canCollideCheck",targetMap = Maps.Srg1_12_2)
    public static Method canCollideCheck;
    @WrapMethod(mcpName = "isVisuallyOpaque",targetMap = Maps.Srg1_8_9)
    public static Method isVisuallyOpaque;
    @WrapField(mcpName = "defaultBlockState",targetMap = Maps.Srg1_12_2)
    public static Field defaultBlockState;
    @WrapMethod(mcpName = "causesSuffocation",targetMap = Maps.Srg1_12_2)
    public static Method causesSuffocation;
    @WrapField(mcpName = "maxY",targetMap = Maps.Srg1_8_9)
    public static Field maxY;
    @WrapField(mcpName = "maxX",targetMap = Maps.Srg1_8_9)
    public static Field maxX;
    @WrapField(mcpName = "maxZ",targetMap = Maps.Srg1_8_9)
    public static Field maxZ;
    @WrapField(mcpName = "minY",targetMap = Maps.Srg1_8_9)
    public static Field minY;
    @WrapField(mcpName = "minX",targetMap = Maps.Srg1_8_9)
    public static Field minX;
    @WrapField(mcpName = "minZ",targetMap = Maps.Srg1_8_9)
    public static Field minZ;
    @WrapMethod(mcpName = "getBoundingBox",targetMap = Maps.Srg1_12_2)
    public static Method getBoundingBox;
    @WrapMethod(mcpName = "setBlockBoundsBasedOnState",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setBlockBoundsBasedOnState",targetMap = Maps.Srg1_12_2)
    public static Method setBlockBoundsBasedOnState;
    @WrapMethod(mcpName = "getSelectedBoundingBox",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getSelectedBoundingBox",targetMap = Maps.Srg1_12_2)
    public static Method getSelectedBoundingBox;
    @WrapMethod(mcpName = "shouldSideBeRendered",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "shouldSideBeRendered",targetMap = Maps.Srg1_12_2)
    public static Method shouldSideBeRendered;
    @WrapMethod(mcpName = "getBlockLayer",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockLayer",targetMap = Maps.Srg1_12_2)
    public static Method getBlockLayer;
    @WrapMethod(mcpName = "getMixedBrightnessForBlock",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getPackedLightmapCoords",targetMap = Maps.Srg1_12_2)
    public static Method getMixedBrightnessForBlock;
    @WrapMethod(mcpName = "getAmbientOcclusionLightValue",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getAmbientOcclusionLightValue",targetMap = Maps.Srg1_12_2)
    public static Method getAmbientOcclusionLightValue;
    @WrapMethod(mcpName = "isOpaqueCube",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isOpaqueCube",targetMap = Maps.Srg1_12_2)
    public static Method isOpaqueCube;
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = Maps.Srg1_12_2)
    public static Method getUnlocalizedName;
    @WrapMethod(mcpName = "getIdFromBlock",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getIdFromBlock",targetMap = Maps.Srg1_12_2)
    public static Method getIdFromBlock;
    @WrapMethod(mcpName = "getBlockHardness",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockHardness",targetMap = Maps.Srg1_12_2)
    public static Method getBlockHardness;
    @WrapMethod(mcpName = "getBlockById",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockById",targetMap = Maps.Srg1_12_2)
    public static Method getBlockById;
    public Block(Object obj) {
        super(obj);
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
        return (int) ReflectUtil.invoke(getIdFromBlock,null,block.getWrapObject());
    }
    public String getUnlocalizedName(){
        return (String) invoke(getUnlocalizedName);
    }
    public boolean isVisuallyOpaque(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            return (boolean) invoke(causesSuffocation,getField(defaultBlockState));
        }
        return  (boolean) invoke(isVisuallyOpaque);
    }
    public float getBlockHardness(World world,BlockPos pos){
        return (float) invoke(getBlockHardness,world.getWrapObject(),pos.getWrapObject());
    }
    public IBlockState getDefaultBlockState(){
        return new IBlockState(getField(defaultBlockState));
    }
    public double getMaxY(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            return getBoundingBox(getDefaultBlockState(),null,BlockPos.wrap(null)).getMaxY();
        }
        return (double) getField(maxY);
    }
    public AxisAlignedBB getBoundingBox(IBlockState ib, IBlockAccess IBlockAccess, BlockPos bp){
        return new AxisAlignedBB(invoke(getBoundingBox,ib.getWrapObject(),IBlockAccess.getWrapObject(),bp.getWrapObject()));
    }
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        invoke(setBlockBoundsBasedOnState,worldIn.getWrapObject(),pos.getWrapObject());
    }
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        return new AxisAlignedBB(invoke(getSelectedBoundingBox,worldIn.getWrapObject(),pos.getWrapObject()));
    }
    public boolean canCollideCheck(IBlockState blockState,boolean hitIfLiquid){
        return (boolean) invoke(canCollideCheck,blockState.getWrapObject(),hitIfLiquid);
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
