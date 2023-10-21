package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0CPacketInput", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketInput", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C0CPacketInput extends Packet {
    @ClassInstance
    public static Class<?> C0CPacketInputClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla} , signature = {float.class,float.class,boolean.class,boolean.class})
    public static Constructor C0CPacketInputConstructor;
    @WrapField(mcpName = "strafeSpeed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field strafeSpeed;
    @WrapField(mcpName = "forwardSpeed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field forwardSpeed;
    @WrapField(mcpName = "jumping", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field jumping;
    @WrapField(mcpName = "sneaking", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field sneaking;
    public C0CPacketInput(Object obj) {
        super(obj);
    }

    public C0CPacketInput(float strafeSpeed, float forwardSpeed, boolean jumping, boolean sneaking) {
          this(construction(C0CPacketInputConstructor, strafeSpeed, forwardSpeed, jumping, sneaking));
    }

    public static boolean isC0CPacketInput(Packet packet) {
        return C0CPacketInputClass.isInstance(packet.getWrappedObject());
    }

    public float getStrafeSpeed() {
        return (float) ReflectUtil.getField(strafeSpeed, getWrappedObject());
    }

    public float getfowrardSpeed() {
        return (float) ReflectUtil.getField(forwardSpeed, getWrappedObject());
    }

    public void setStrafeSpeed(int i){
        ReflectUtil.setField(strafeSpeed,i,getWrappedObject());
    }

    public void setForwardSpeed(int i){
        ReflectUtil.setField(forwardSpeed,i,getWrappedObject());
    }

}
