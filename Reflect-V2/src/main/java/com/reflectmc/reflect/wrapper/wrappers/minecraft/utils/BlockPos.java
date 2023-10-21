package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.BlockPos",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.BlockPos",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockPos extends Vec3i {
    @ClassInstance
    public static Class BlockPosClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {double.class,double.class,double.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {double.class,double.class,double.class})
    public static Constructor BlockPos_DDD;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {Entity.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {Entity.class})
    public static Constructor BlockPos_Entity;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {Vec3.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {Vec3.class})
    public static Constructor BlockPos_Vec;
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;")
    public static Method offset;
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;")
    public static Method offset_I;
    @WrapMethod(deobfName = "down",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "down",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method down;
    @WrapMethod(deobfName = "up",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "up",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method up;
    @WrapMethod(deobfName = "south",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "south",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(I)Lnet/minecraft/util/math/BlockPos;")
    public static Method south_I;
    @WrapMethod(deobfName = "south",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "south",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method south;
    @WrapMethod(deobfName = "north",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "north",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method north;
    @WrapMethod(deobfName = "east",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "east",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method east;
    @WrapMethod(deobfName = "west",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "west",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()Lnet/minecraft/util/math/BlockPos;")
    public static Method west;
    @WrapMethod(deobfName = "down",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "down",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(I)Lnet/minecraft/util/math/BlockPos;")
    public static Method down_I;
    @WrapMethod(deobfName = "north",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "north",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(I)Lnet/minecraft/util/math/BlockPos;")
    public static Method north_I;
    @WrapMethod(deobfName = "west",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "west",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(I)Lnet/minecraft/util/math/BlockPos;")
    public static Method west_I;
    @WrapMethod(deobfName = "add",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(III)Lnet/minecraft/util/BlockPos;")
    @WrapMethod(deobfName = "add",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(III)Lnet/minecraft/util/math/BlockPos;")
    public static Method add_III;
    @WrapObject(deobfName = "ORIGIN", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "ORIGIN", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static BlockPos ORIGIN;
    public BlockPos(Object obj) {
        super(obj);
    }
    public BlockPos(double x, double y, double z)
    {
        super(construct(BlockPos_DDD,x,y,z));
    }
    public BlockPos(Entity entity){
        this(construct(BlockPos_Entity,entity.getWrappedObject()));
    }
    public BlockPos(Vec3 vec){
        this(construct(BlockPos_Vec,vec.getWrappedObject()));
    }
    public BlockPos offset(Enum facing){
        return new BlockPos(invokeMethod(offset,facing));
    }
    public BlockPos offset(Enum facing,int i){
        return new BlockPos(invokeMethod(offset_I,facing,i));
    }
    public BlockPos down(){
        return new BlockPos(invokeMethod(down));
    }
    public BlockPos up(){
        return new BlockPos(invokeMethod(up));
    }
    public BlockPos south(int i){
        return new BlockPos(invokeMethod(south_I,i));
    }
    public BlockPos south(){
        return new BlockPos(invokeMethod(south));
    }
    public BlockPos north(){
        return new BlockPos(invokeMethod(north));
    }
    public BlockPos east(){
        return new BlockPos(invokeMethod(east));
    }
    public BlockPos west(){
        return new BlockPos(invokeMethod(west));
    }
    public BlockPos down(int i){
        return new BlockPos(invokeMethod(down_I,i));
    }
    public BlockPos north(int i){
        return new BlockPos(invokeMethod(north_I,i));
    }
    public BlockPos west(int i){
        return new BlockPos(invokeMethod(west_I,i));
    }
    public BlockPos add(int x, int y, int z)
    {
        return new BlockPos(invokeMethod(add_III,x,y,z));
    }
    public static BlockPos wrap(Object o){
        return new BlockPos(o);
    }
}
