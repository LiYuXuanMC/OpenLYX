package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;


import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.network.NetworkManager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.network.NetworkManager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class NetworkManager extends WrapperBase {
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;)V net/minecraft/network/NetworkManager/func_179290_a (Lnet/minecraft/network/Packet;)V
    //MD: net/minecraft/network/NetworkManager/sendPacket (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V net/minecraft/network/NetworkManager/func_179288_a (Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V
    @WrapMethod(deobfName = "sendPacket",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/network/Packet;)V")
    @WrapMethod(deobfName = "sendPacket",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/network/Packet;)V")
    public static Method sendPacket1;
    @WrapMethod(deobfName = "channelRead0",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V")
    @WrapMethod(deobfName = "channelRead0",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method channelRead0;
    @ClassInstance
    public static Class NetworkManagerClass;
    @WrapMethod(deobfName = "isChannelOpen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method isChannelOpen;
    @WrapMethod(deobfName = "flushOutboundQueue",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method flushOutboundQueue;
    @WrapField(deobfName = "outboundPacketsQueue",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field outboundPacketsQueue;
    @WrapField(deobfName = "channel", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field channel;
    @WrapMethod(deobfName = "closeChannel", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "closeChannel", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method closeChannel;

    //(Lnet/minecraft/network/Packet;[Lio/netty/util/concurrent/GenericFutureListener;)V
    public NetworkManager(Object obj) {
        super(obj);
    }

    public void closeChannel(IChatComponent c) {
        invokeMethod(closeChannel, c.getWrappedObject());
    }
    public void sendPacket(Packet packet) {
        if (packet.isNull()) {
            //ClientUtil.printChat(ClientUtil.Level.DEBUG, packet.getClass().getCanonicalName() + " null object");
            return;
        }
        invokeMethod(sendPacket1, packet.getWrappedObject());
    }
}