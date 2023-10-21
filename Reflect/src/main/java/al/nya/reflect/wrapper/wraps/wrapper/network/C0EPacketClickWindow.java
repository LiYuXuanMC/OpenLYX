package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0EPacketClickWindow", targetMap = Maps.Srg1_8_9)
public class C0EPacketClickWindow extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0EPacketClickWindow", targetMap = Maps.Srg1_8_9)
    public static Class C0EPacketClickWindowClass;

    public C0EPacketClickWindow(Object obj) {
        super(obj);
    }

    public static boolean isPacketClickWindowClass(Packet packet) {
        return C0EPacketClickWindowClass.isInstance(packet.getWrapObject());
    }
}
