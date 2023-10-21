package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S02PacketChat", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketChat", targetMap = Maps.Srg1_12_2)
public class S02PacketChat extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.server.S02PacketChat", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketChat", targetMap = Maps.Srg1_12_2)
    public static Class<?> S02PacketChatClass;
    @WrapField(mcpName = "chatComponent",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "chatComponent",targetMap = Maps.Srg1_12_2)
    public static Field chatComponent;
    public IChatComponent getChatComponent() {
        return new IChatComponent(getField(chatComponent));
    }
    public S02PacketChat(Object packet){
        super(packet);
    }
    public static boolean isS02PacketChat(Packet packet) {
        return S02PacketChatClass.isInstance(packet.getWrapObject());
    }
}
