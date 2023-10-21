package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C01PacketChatMessage",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketChatMessage",targetMap = Maps.Srg1_12_2)
public class C01PacketChatMessage extends Packet{
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {String.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {String.class})
    public static Constructor<?> C01PacketChatMessage_String;

    @WrapField(mcpName = "message",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "message",targetMap = Maps.Srg1_12_2)
    public static Field message;

    @WrapClass(mcpName = "net.minecraft.network.play.client.C01PacketChatMessage",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketChatMessage",targetMap = Maps.Srg1_12_2)
    public static Class<?> C01PacketChatMessageClass;

    public C01PacketChatMessage(Object obj) {
        super(obj);
    }
    public C01PacketChatMessage(String s){
        this(ReflectUtil.construction(C01PacketChatMessage_String,s));
    }

    public String getMessage(){
        return (String) ReflectUtil.getField(message,getWrapObject());
    }

    public static boolean isC01PacketChatMessage(Packet c) {
        return C01PacketChatMessageClass.isInstance(c.getWrapObject());
    }
}
