package al.logger.client.wrapper.LoggerMC.network.client.C03;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.network.Packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C03PacketPlayer extends Packet {
@ClassInstance    
public static Class<?> C03PacketPlayer;
    @WrapField(mcpName = "moving", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field moving;
    @WrapField(mcpName = "x", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field x;
    @WrapField(mcpName = "y", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field y;
    @WrapField(mcpName = "z", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field z;
    @WrapField(mcpName = "yaw", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field yaw;
    @WrapField(mcpName = "pitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field pitch;
    @WrapField(mcpName = "onGround", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field onGround;
    @WrapField(mcpName = "rotating", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotating;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {boolean.class})
    public static Constructor<?> C03PacketPlayer_boolean;

    public C03PacketPlayer(Object obj) {
        super(obj);
    }

    public C03PacketPlayer(Packet e) {
        super(e.getWrappedObject());
    }

    public C03PacketPlayer(boolean b) {
        this(construction(C03PacketPlayer_boolean, b));
    }

    public static boolean isPacketPlayer(Packet packet) {
        return C03PacketPlayer.isInstance(packet.getWrappedObject());
    }

    public boolean isMoving() {
        return (boolean) getField(moving);
    }

    public double getX() {
        return (double) getField(x);
    }

    public double getY() {
        return (double) getField(y);
    }

    public double getZ() {
        return (double) getField(z);
    }

    public void setX(double d) {
        setField(x, d);
    }

    public void setY(double d) {
        setField(y, d);
    }

    public void setZ(double d) {
        setField(z, d);
    }

    public boolean isOnGround() {
        return (boolean) getField(onGround);
    }

    public void setOnGround(boolean onGroundValue) {
        setField(onGround, onGroundValue);
    }

    public boolean isRotating() {
        return (boolean) getField(rotating);
    }

    public float getYaw() {
        return (float) getField(yaw);
    }

    public float getPitch() {
        return (float) getField(pitch);
    }
}
