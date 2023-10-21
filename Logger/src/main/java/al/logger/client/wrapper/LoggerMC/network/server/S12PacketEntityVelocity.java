package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S12PacketEntityVelocity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketEntityVelocity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S12PacketEntityVelocity extends Packet {
@ClassInstance    
public static Class S12PacketEntityVelocityClass;
    @WrapField(mcpName = "motionX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionX;
    @WrapField(mcpName = "motionY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionY;
    @WrapField(mcpName = "motionZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "motionZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field motionZ;
    @WrapField(mcpName = "entityID",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityID",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityID;
    public S12PacketEntityVelocity(Object obj) {
        super(obj);
    }
    public static boolean isS12PacketEntityVelocity(Packet c) {
        return S12PacketEntityVelocityClass.isAssignableFrom(c.getWrappedObject().getClass());
    }
    public int getMotionX(){
        return (int) ReflectUtil.getField(motionX,getWrappedObject());
    }
    public int getMotionY(){
        return (int) ReflectUtil.getField(motionY,getWrappedObject());
    }
    public int getMotionZ(){
        return (int) ReflectUtil.getField(motionZ,getWrappedObject());
    }
    public int getEntityID(){
        return (int) ReflectUtil.getField(entityID,getWrappedObject());
    }
    public void setMotionX(int i){
        ReflectUtil.setField(motionX,i,getWrappedObject());
    }
    public void setMotionY(int i){
        ReflectUtil.setField(motionY,i,getWrappedObject());
    }
    public void setMotionZ(int i){
        ReflectUtil.setField(motionZ,i,getWrappedObject());
    }
}
