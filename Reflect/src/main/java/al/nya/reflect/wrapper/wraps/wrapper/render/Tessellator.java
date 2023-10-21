package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.Tessellator",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.Tessellator",targetMap = Maps.Srg1_12_2)
public class Tessellator extends IWrapper {
    @WrapMethod(mcpName = "getInstance",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getInstance",targetMap = Maps.Srg1_12_2)
    public static Method getInstance;
    @WrapField(mcpName = "worldRenderer",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "buffer",targetMap = Maps.Srg1_12_2)
    public static Field worldRenderer;
    @WrapMethod(mcpName = "draw",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "draw",targetMap = Maps.Srg1_12_2)
    public static Method draw;

    public Tessellator(Object obj) {
        super(obj);
    }
    public static Tessellator getInstance(){
        return new Tessellator(ReflectUtil.invoke(getInstance,null));
    }
    public WorldRenderer getWorldRenderer(){
        return new WorldRenderer(ReflectUtil.getField(worldRenderer,getWrapObject()));
    }
    public void draw(){
        ReflectUtil.invoke(draw,getWrapObject());
    }
}
