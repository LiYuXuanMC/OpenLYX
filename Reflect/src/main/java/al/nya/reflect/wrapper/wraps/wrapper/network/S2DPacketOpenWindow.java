package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.server.S2DPacketOpenWindow",targetMap = Maps.Srg1_8_9)
public class S2DPacketOpenWindow extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S2DPacketOpenWindow",targetMap = Maps.Srg1_8_9)
    public static Class S2DPacketOpenWindowClass;
    public S2DPacketOpenWindow(Object obj) {
        super(obj);
    }
    public static boolean isS2DPacketOpenWindow(Packet packet){
        return S2DPacketOpenWindowClass.isInstance(packet.getWrapObject());
    }
}
