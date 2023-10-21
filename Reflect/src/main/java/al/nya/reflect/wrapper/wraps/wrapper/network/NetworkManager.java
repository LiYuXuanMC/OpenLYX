package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import io.netty.channel.Channel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@WrapperClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = Maps.Srg1_12_2)
public class NetworkManager extends IWrapper {
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;)V net/minecraft/network/NetworkManager/func_179290_a (Lnet/minecraft/network/Packet;)V
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V net/minecraft/network/NetworkManager/func_179288_a (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V
    @WrapMethod(mcpName = "sendPacket",targetMap = Maps.Srg1_8_9,signature = "(Lnet/minecraft/network/Packet;)V")
    @WrapMethod(mcpName = "sendPacket",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/network/Packet;)V")
    public static Method sendPacket1;
    @WrapMethod(mcpName = "channelRead0",targetMap = Maps.Srg1_8_9,signature = "(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V")
    @WrapMethod(mcpName = "channelRead0",targetMap = Maps.Srg1_12_2)
    public static Method channelRead0;
    @WrapClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.NetworkManager",targetMap = Maps.Srg1_12_2)
    public static Class NetworkManagerClass;
    @WrapMethod(mcpName = "isChannelOpen",targetMap = Maps.Srg1_8_9)
    public static Method isChannelOpen;
    @WrapMethod(mcpName = "flushOutboundQueue",targetMap = Maps.Srg1_8_9)
    public static Method flushOutboundQueue;
    @WrapMethod(mcpName = "dispatchPacket",targetMap = Maps.Srg1_8_9)
    public static Method dispatchPacket;
    @WrapField(mcpName = "field_181680_j",targetMap = Maps.Srg1_8_9,customSrgName = "field_181680_j")
    public static Field field_181680_j;
    @WrapField(mcpName = "outboundPacketsQueue",targetMap = Maps.Srg1_8_9)
    public static Field outboundPacketsQueue;
    @WrapField(mcpName = "channel", targetMap = Maps.Srg1_8_9)
    public static Field channel;
    @WrapMethod(mcpName = "closeChannel", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "closeChannel", targetMap = Maps.Srg1_12_2)
    public static Method closeChannel;

    //(Lnet/minecraft/network/Packet;[Lio/netty/util/concurrent/GenericFutureListener;)V
    public NetworkManager(Object obj) {
        super(obj);
    }

    public void closeChannel(IChatComponent c) {
        invoke(closeChannel, c.getWrapObject());
    }

    public void sendPacket(Packet packet) {
        if (Objects.isNull(packet.getWrapObject())) {
            ClientUtil.printChat(ClientUtil.Level.DEBUG, packet.getClass().getCanonicalName() + " null object");
            return;
        }
        ReflectUtil.invoke(sendPacket1, getWrapObject(), packet.getWrapObject());
    }

    public void sendPacketNoEvent(Packet p) {
        EventBus.noEventPackets.add(p.getWrapObject());
        sendPacket(p);
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
        invoke(dispatchPacket,packet.getWrapObject(),futureListeners);
    }
    public ReentrantReadWriteLock getLock(){
        return (ReentrantReadWriteLock) getField(field_181680_j);
    }
    public Queue getOutboundPacketsQueue(){
        return (Queue) getField(outboundPacketsQueue);
    }
}