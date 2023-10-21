package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;


@WrapperClass(mcpName = "net.minecraft.network.play.client.C0APacketAnimation",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketAnimation",targetMap = Maps.Srg1_12_2)
public class C0APacketAnimation extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0APacketAnimation",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketAnimation",targetMap = Maps.Srg1_12_2)
    public static Class C0APacketAnimationClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9)
    public static Constructor C0APacketAnimation_V;

    public C0APacketAnimation(Object obj) {
        super(obj);
    }
    public C0APacketAnimation(){
        this(ReflectUtil.construction(C0APacketAnimation_V));
    }
}
