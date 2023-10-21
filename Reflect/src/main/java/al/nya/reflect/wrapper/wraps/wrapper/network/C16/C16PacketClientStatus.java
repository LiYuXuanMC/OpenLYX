package al.nya.reflect.wrapper.wraps.wrapper.network.C16;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C16PacketClientStatus",targetMap = Maps.Srg1_8_9)
public class C16PacketClientStatus extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C16PacketClientStatus",targetMap = Maps.Srg1_8_9)
    public static Class C16PacketClientStatusClass;
    @WrapField(mcpName = "status",targetMap = Maps.Srg1_8_9)
    public static Field status;
    public C16PacketClientStatus(Object obj) {
        super(obj);
    }
    public static boolean isC16PacketClientStatus(Packet packet){
        return C16PacketClientStatusClass.isInstance(packet.getWrapObject());
    }
    public Enum getStatus(){
        return (Enum) getField(status);
    }
}
