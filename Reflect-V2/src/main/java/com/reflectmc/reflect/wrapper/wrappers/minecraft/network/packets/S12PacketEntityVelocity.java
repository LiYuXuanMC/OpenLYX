package com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.network.play.server.S12PacketEntityVelocity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.play.server.SPacketEntityVelocity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class S12PacketEntityVelocity extends Packet {
    @ClassInstance
    public static Class S12PacketEntityVelocityClass;
    @WrapField(deobfName = "motionX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionX;
    @WrapField(deobfName = "motionY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionY;
    @WrapField(deobfName = "motionZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "motionZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field motionZ;
    @WrapField(deobfName = "entityID",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "entityID",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field entityID;
    public S12PacketEntityVelocity(Object obj) {
        super(obj);
    }
    public static boolean isS12PacketEntityVelocity(Packet c) {
        return S12PacketEntityVelocityClass.isAssignableFrom(c.getWrappedObject().getClass());
    }
    public int getMotionX(){
        return (int) getField(motionX);
    }
    public int getMotionY(){
        return (int) getField(motionY);
    }
    public int getMotionZ(){
        return (int) getField(motionZ);
    }
    public int getEntityID(){
        return (int) getField(entityID);
    }
    public void setMotionX(int i){
        setField(motionX,i);
    }
    public void setMotionY(int i){
        setField(motionY,i);
    }
    public void setMotionZ(int i){
        setField(motionZ,i);
    }
}
