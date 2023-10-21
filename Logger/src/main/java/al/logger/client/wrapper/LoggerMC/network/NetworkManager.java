package al.logger.client.wrapper.LoggerMC.network;

import al.logger.client.Logger;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import io.netty.channel.Channel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class NetworkManager extends IWrapper {
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;)V net/minecraft/network/NetworkManager/func_179290_a (Lnet/minecraft/network/Packet;)V
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V net/minecraft/network/NetworkManager/func_179288_a (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V
    @WrapMethod(mcpName = "sendPacket",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/network/Packet;)V")
    @WrapMethod(mcpName = "sendPacket",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/network/Packet;)V")
    public static Method sendPacket1;
    @WrapMethod(mcpName = "channelRead0",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V")
    @WrapMethod(mcpName = "channelRead0",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method channelRead0;
    @ClassInstance
    public static Class NetworkManagerClass;
    @WrapMethod(mcpName = "isChannelOpen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isChannelOpen;
    @WrapMethod(mcpName = "flushOutboundQueue",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method flushOutboundQueue;
    @WrapMethod(mcpName = "dispatchPacket",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method dispatchPacket;
    @WrapField(mcpName = "readWriteLock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},customSrgName = "field_181680_j")
    public static Field readWriteLock;
    @WrapField(mcpName = "outboundPacketsQueue",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field outboundPacketsQueue;
    @WrapField(mcpName = "channel", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field channel;
    @WrapMethod(mcpName = "closeChannel", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "closeChannel", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method closeChannel;
    public static Constructor NoEventPacketConstructor;

    //(Lnet/minecraft/network/Packet;[Lio/netty/util/concurrent/GenericFutureListener;)V
    public NetworkManager(Object obj) {
        super(obj);
    }

    public void closeChannel(IChatComponent c) {
        invoke(closeChannel, c.getWrappedObject());
    }

    public void sendPacket(Packet packet) {
        ReflectUtil.invoke(sendPacket1, getWrappedObject(), packet.getWrappedObject());
    }

    public void sendPacketNoEvent(Packet p) {
        if (NoEventPacketConstructor == null){
            NoEventPacketConstructor = Logger.getInstance().getBridgeManager().getNoEventPacketConstructor();
        }
        sendPacket(new Packet(construction(NoEventPacketConstructor,p)));
    }
    public Channel getChannel(){
        return (Channel) getField(channel);
    }
    public boolean isChannelOpen(){
        return (boolean) invoke(isChannelOpen);
    }
    public void flushOutboundQueue(){
        invoke(flushOutboundQueue);
    }
    public void dispatchPacket(Packet packet,Object futureListeners){
        invoke(dispatchPacket,packet.getWrappedObject(),futureListeners);
    }
    public ReentrantReadWriteLock getLock(){
        return (ReentrantReadWriteLock) getField(readWriteLock);
    }
    public Queue getOutboundPacketsQueue(){
        return (Queue) getField(outboundPacketsQueue);
    }
}