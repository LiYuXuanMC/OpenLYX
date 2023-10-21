package al.nya.reflect.wrapper.wraps.wrapper.network.S08;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S08PacketPlayerPosLook",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketPlayerPosLook",targetMap = Maps.Srg1_12_2)
public class S08PacketPlayerPosLook extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S08PacketPlayerPosLook", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketPlayerPosLook", targetMap = Maps.Srg1_12_2)
    public static Class<?> S08PacketPlayerPosLookClass;
    @WrapField(mcpName = "pitch", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "pitch", targetMap = Maps.Srg1_12_2)
    public static Field pitch;
    @WrapField(mcpName = "yaw", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "yaw", targetMap = Maps.Srg1_12_2)
    public static Field yaw;
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_12_2)
    public static Field x;
    @WrapField(mcpName = "y",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "y",targetMap = Maps.Srg1_12_2)
    public static Field y;
    @WrapField(mcpName = "z",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "z",targetMap = Maps.Srg1_12_2)
    public static Field z;
    @WrapMethod(mcpName = "func_179834_f",targetMap = Maps.Srg1_8_9)
    public static Method func_179834_f;
    public S08PacketPlayerPosLook(Object obj) {
        super(obj);
    }
    public static boolean isS08PacketPlayerPosLook(Packet packet){
        return S08PacketPlayerPosLookClass.isInstance(packet.getWrapObject());
    }
    public float getPitch(){
        return (float) getField(pitch);
    }
    public float getYaw(){
        return (float) getField(yaw);
    }
    public void setPitch(float f){
        setField(pitch,f);
    }
    public void setYaw(float f){
        setField(yaw,f);
    }
    public void setX(double d){
        setField(x,d);
    }
    public void setY(double d){
        setField(y,d);
    }
    public void setZ(double d){
        setField(z,d);
    }
    public double getX(){
        return (double) getField(x);
    }
    public double getY(){
        return (double) getField(y);
    }
    public double getZ(){
        return (double) getField(z);
    }
    /**
     * func_179834_f
     */
    public Set<Enum> getFlags() {
        return (Set<Enum>) invoke(func_179834_f);
    }
}
