package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S12PacketEntityVelocity",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketEntityVelocity",targetMap = Maps.Srg1_12_2)
public class S12PacketEntityVelocity extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S12PacketEntityVelocity",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketEntityVelocity",targetMap = Maps.Srg1_12_2)
    public static Class S12PacketEntityVelocityClass;
    @WrapField(mcpName = "motionX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionX",targetMap = Maps.Srg1_12_2)
    public static Field motionX;
    @WrapField(mcpName = "motionY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionY",targetMap = Maps.Srg1_12_2)
    public static Field motionY;
    @WrapField(mcpName = "motionZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "motionZ",targetMap = Maps.Srg1_12_2)
    public static Field motionZ;
    @WrapField(mcpName = "entityID",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "entityID",targetMap = Maps.Srg1_12_2)
    public static Field entityID;
    public S12PacketEntityVelocity(Object obj) {
        super(obj);
    }
    public static boolean isS12PacketEntityVelocity(Packet c) {
        return S12PacketEntityVelocityClass.isAssignableFrom(c.getWrapObject().getClass());
    }
    public int getMotionX(){
        return (int) ReflectUtil.getField(motionX,getWrapObject());
    }
    public int getMotionY(){
        return (int) ReflectUtil.getField(motionY,getWrapObject());
    }
    public int getMotionZ(){
        return (int) ReflectUtil.getField(motionZ,getWrapObject());
    }
    public int getEntityID(){
        return (int) ReflectUtil.getField(entityID,getWrapObject());
    }
    public void setMotionX(int i){
        ReflectUtil.setField(motionX,i,getWrapObject());
    }
    public void setMotionY(int i){
        ReflectUtil.setField(motionY,i,getWrapObject());
    }
    public void setMotionZ(int i){
        ReflectUtil.setField(motionZ,i,getWrapObject());
    }
}
