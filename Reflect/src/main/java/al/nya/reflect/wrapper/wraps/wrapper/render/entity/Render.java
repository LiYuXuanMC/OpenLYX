package al.nya.reflect.wrapper.wraps.wrapper.render.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = Maps.Srg1_12_2)
public class Render extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = Maps.Srg1_12_2)
    public static Class<?> RenderClass;
    public Render(Object obj) {
        super(obj);
    }
}
