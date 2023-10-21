package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0CPacketInput", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketInput", targetMap = Maps.Srg1_12_2)
public class C0CPacketInput extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0CPacketInput", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketInput", targetMap = Maps.Srg1_12_2)
    public static Class<?> C0CPacketInputClass;

    public C0CPacketInput(Object obj) {
        super(obj);
    }

    public static boolean isC0CPacketInput(Packet packet) {
        return C0CPacketInputClass.isInstance(packet.getWrapObject());
    }

}
