package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.WorldRenderer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.BufferBuilder",targetMap = Maps.Srg1_12_2)
public class WorldRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.WorldRenderer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.BufferBuilder",targetMap = Maps.Srg1_12_2)
    public static Class WorldRendererClass;
    @WrapMethod(mcpName = "begin",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "begin",targetMap = Maps.Srg1_12_2)
    public static Method begin;
    @WrapMethod(mcpName = "pos",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "pos",targetMap = Maps.Srg1_12_2)
    public static Method pos;
    @WrapMethod(mcpName = "endVertex",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "endVertex",targetMap = Maps.Srg1_12_2)
    public static Method endVertex;
    @WrapMethod(mcpName = "color",targetMap = Maps.Srg1_8_9,signature = "(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(mcpName = "color",targetMap = Maps.Srg1_12_2,signature = "(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4f;
    @WrapMethod(mcpName = "color",targetMap = Maps.Srg1_8_9,signature = "(IIII)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(mcpName = "color",targetMap = Maps.Srg1_12_2,signature = "(IIII)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4I;
    @WrapMethod(mcpName = "putColorMultiplier",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "putColorMultiplier",targetMap = Maps.Srg1_12_2)
    public static Method putColorMultiplier;
    @WrapMethod(mcpName = "tex",targetMap=Maps.Srg1_12_2)
    @WrapMethod(mcpName = "tex",targetMap=Maps.Srg1_8_9)
    public static Method tex;

    public WorldRenderer(Object obj) {
        super(obj);
    }
    public void begin(int glMode, VertexFormat format)
    {
        invoke(begin,glMode,format.getWrapObject());
    }
    public WorldRenderer pos(double x, double y, double z)
    {
        invoke(pos,x,y,z);
        return this;
    }
    public void endVertex()
    {
        invoke(endVertex);
    }
    public WorldRenderer color(float red, float green, float blue, float alpha)
    {
        invoke(color_4f,red,green,blue,alpha);
        return this;
    }
    public WorldRenderer color(int i1,int i2,int i3,int i4) {
        invoke(color_4I,i1,i2,i3,i4);
        return this;
    }

    public WorldRenderer tex(double u2, double v1) {
        invoke(tex,u2,v1);
        return this;
    }
}
