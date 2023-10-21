package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture",targetMap = Maps.Srg1_12_2)
public class AbstractTexture extends IWrapper {
    @WrapMethod(mcpName = "getGlTextureId",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getGlTextureId",targetMap = Maps.Srg1_12_2)
    public static Method getGlTextureId;
    public AbstractTexture(Object obj) {
        super(obj);
    }
    public int getGlTextureId()
    {
        return (int) ReflectUtil.invoke(getGlTextureId,getWrapObject());
    }
}
