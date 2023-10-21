package al.nya.reflect.wrapper.wraps.wrapper.utils.event;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.SoundEvent",targetMap = Maps.Srg1_12_2)
public class SoundEvent extends IWrapper {
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {ResourceLocation.class})
    public static Constructor SoundEvent_ResourceLocation;
    public SoundEvent(Object obj) {
        super(obj);
    }
    public SoundEvent(ResourceLocation rl){
        super(ReflectUtil.construction(SoundEvent_ResourceLocation,rl.getWrapObject()));
    }
}
