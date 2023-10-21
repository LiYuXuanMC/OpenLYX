package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S45PacketTitle", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketTitle", targetMap = Maps.Srg1_12_2)
public class S45PacketTitle extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.server.S45PacketTitle", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketTitle", targetMap = Maps.Srg1_12_2)
    public static Class<?> S45PacketTitleClass;
    @WrapField(mcpName = "message", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "message", targetMap = Maps.Srg1_12_2)
    public static Field message;
    public S45PacketTitle(Object obj) {
        super(obj);
    }
    public static boolean isS45PacketTitle(Packet p){
        return S45PacketTitleClass.isInstance(p.getWrapObject());
    }
    public IChatComponent getMessage(){
        return new IChatComponent(getField(message));
    }
    public String getFormattedText(){
        return getMessage().getFormattedText();
    }
}
