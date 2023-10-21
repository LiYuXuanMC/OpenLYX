package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C0DPacketCloseWindow",targetMap = Maps.Srg1_8_9)
public class C0DPacketCloseWindow extends Packet{
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {})
    public static Constructor C0DPacketCloseWindowConstructor;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {int.class})
    public static Constructor C0DPacketCloseWindowConstructor_I;
    public C0DPacketCloseWindow(Object obj) {
        super(obj);
    }
    public C0DPacketCloseWindow(){
        this(ReflectUtil.construction(C0DPacketCloseWindowConstructor));
    }
    public C0DPacketCloseWindow(int windowId){
        this(ReflectUtil.construction(C0DPacketCloseWindowConstructor_I));
    }
}
