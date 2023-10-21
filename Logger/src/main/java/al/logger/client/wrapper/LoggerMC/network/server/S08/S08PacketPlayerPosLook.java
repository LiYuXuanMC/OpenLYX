package al.logger.client.wrapper.LoggerMC.network.server.S08;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S08PacketPlayerPosLook",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketPlayerPosLook",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S08PacketPlayerPosLook extends Packet {
@ClassInstance
public static Class<?> S08PacketPlayerPosLookClass;
    @WrapField(mcpName = "pitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "pitch", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field pitch;
    @WrapField(mcpName = "yaw", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "yaw", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field yaw;
    @WrapField(mcpName = "x", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "x", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field x;
    @WrapField(mcpName = "y",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "y",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field y;
    @WrapField(mcpName = "z",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "z",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field z;
    @WrapMethod(mcpName = "func_179834_f",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method func_179834_f;
    public S08PacketPlayerPosLook(Object obj) {
        super(obj);
    }
    public static boolean isS08PacketPlayerPosLook(Packet packet){
        return S08PacketPlayerPosLookClass.isInstance(packet.getWrappedObject());
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
