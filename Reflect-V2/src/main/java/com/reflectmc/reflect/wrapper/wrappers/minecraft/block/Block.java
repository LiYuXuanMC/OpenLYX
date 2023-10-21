package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.IBlockAccess;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.block.Block",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.Block",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Block extends WrapperBase {
    @ClassInstance
    public static Class BlockClass;
    @WrapField(deobfName = "blockMaterial",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "blockMaterial",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field blockMaterial;
    @WrapMethod(deobfName = "getBlockBoundsMaxX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMaxX;
    @WrapMethod(deobfName = "getBlockBoundsMaxY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMaxY;
    @WrapMethod(deobfName = "getBlockBoundsMaxZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMaxZ;
    @WrapMethod(deobfName = "getBlockBoundsMinX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMinX;
    @WrapMethod(deobfName = "getBlockBoundsMinY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMinY;
    @WrapMethod(deobfName = "getBlockBoundsMinZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getBlockBoundsMinZ;
    @WrapField(deobfName = "translucent",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "translucent",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field translucent;
    @WrapMethod(deobfName = "canCollideCheck",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "canCollideCheck",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method canCollideCheck;
    @WrapMethod(deobfName = "isVisuallyOpaque",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method isVisuallyOpaque;
    @WrapField(deobfName = "defaultBlockState",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field defaultBlockState;
    @WrapMethod(deobfName = "causesSuffocation",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method causesSuffocation;
    @WrapField(deobfName = "maxY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field maxY;
    @WrapField(deobfName = "maxX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field maxX;
    @WrapField(deobfName = "maxZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field maxZ;
    @WrapField(deobfName = "minY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field minY;
    @WrapField(deobfName = "minX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field minX;
    @WrapField(deobfName = "minZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field minZ;
    @WrapMethod(deobfName = "getBoundingBox",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBoundingBox;
    @WrapMethod(deobfName = "setBlockBoundsBasedOnState",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setBlockBoundsBasedOnState",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setBlockBoundsBasedOnState;
    @WrapMethod(deobfName = "getSelectedBoundingBox",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getSelectedBoundingBox",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getSelectedBoundingBox;
    @WrapMethod(deobfName = "shouldSideBeRendered",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "shouldSideBeRendered",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method shouldSideBeRendered;
    @WrapMethod(deobfName = "getBlockLayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlockLayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlockLayer;
    @WrapMethod(deobfName = "getMixedBrightnessForBlock",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getPackedLightmapCoords",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getMixedBrightnessForBlock;
    @WrapMethod(deobfName = "getAmbientOcclusionLightValue",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getAmbientOcclusionLightValue",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getAmbientOcclusionLightValue;
    @WrapMethod(deobfName = "isOpaqueCube",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isOpaqueCube",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isOpaqueCube;
    @WrapMethod(deobfName = "getUnlocalizedName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getUnlocalizedName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getUnlocalizedName;
    @WrapMethod(deobfName = "getIdFromBlock",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getIdFromBlock",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getIdFromBlock;
    @WrapMethod(deobfName = "getBlockHardness",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlockHardness",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlockHardness;
    @WrapMethod(deobfName = "getBlockById",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlockById",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlockById;
    public Block(Object obj) {
        super(obj);
    }

    public static Block getBlockById(int id) {
        return new Block(invokeStaticMethod(getBlockById,id));
    }

    public Material getMaterial(){
        return new Material(getField(blockMaterial));
    }
    public boolean isTranslucent(){
        return (boolean) getField(translucent);
    }
    public boolean isOpaqueCube(){
        return (boolean) invokeMethod(isOpaqueCube);
    }
    public static int getIdFromBlock(Block block){
        return (int) invokeStaticMethod(getIdFromBlock,block.getWrappedObject());
    }
    public String getUnlocalizedName(){
        return (String) invokeMethod(getUnlocalizedName);
    }
    public boolean isVisuallyOpaque(){
        if (Wrapper.getMapper().isVersionMatch(Environment.Forge1122,Environment.Vanilla1122)){
            return (boolean) invokeMethod(causesSuffocation,getField(defaultBlockState));
        }
        return  (boolean) invokeMethod(isVisuallyOpaque);
    }
    public float getBlockHardness(World world, BlockPos pos){
        return (float) invokeMethod(getBlockHardness,world.getWrappedObject(),pos.getWrappedObject());
    }
    public IBlockState getDefaultBlockState(){
        return new IBlockState(getField(defaultBlockState));
    }
    public double getMaxY(){
        if (Wrapper.getMapper().isVersionMatch(Environment.Forge1122,Environment.Vanilla1122)){
            return getBoundingBox(getDefaultBlockState(),null,BlockPos.wrap(null)).getMaxY();
        }
        return (double) getField(maxY);
    }
    public AxisAlignedBB getBoundingBox(IBlockState ib, IBlockAccess IBlockAccess, BlockPos bp){
        return new AxisAlignedBB(invokeMethod(getBoundingBox,ib.getWrappedObject(),IBlockAccess.getWrappedObject(),bp.getWrappedObject()));
    }
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        invokeMethod(setBlockBoundsBasedOnState,worldIn.getWrappedObject(),pos.getWrappedObject());
    }
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        return new AxisAlignedBB(invokeMethod(getSelectedBoundingBox,worldIn.getWrappedObject(),pos.getWrappedObject()));
    }
    public boolean canCollideCheck(IBlockState blockState,boolean hitIfLiquid){
        return (boolean) invokeMethod(canCollideCheck,blockState.getWrappedObject(),hitIfLiquid);
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
