package al.nya.reflect.utils.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.renderer.ActiveRenderInfo", targetMap = Maps.Srg1_12_2)
@WrapperClass(mcpName = "net.minecraft.client.renderer.ActiveRenderInfo", targetMap = Maps.Srg1_8_9)
public class ActiveRenderInfo extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.ActiveRenderInfo", targetMap = Maps.Srg1_12_2)
    @WrapClass(mcpName = "net.minecraft.client.renderer.ActiveRenderInfo", targetMap = Maps.Srg1_8_9)
    public static Class<?> ActiveRenderInfoClass;
    @WrapField(mcpName = "updateRenderInfo", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "updateRenderInfo", targetMap = Maps.Srg1_8_9)
    public static Field updateRenderInfo;

    public ActiveRenderInfo(Object obj) {
        super(obj);
    }

}
