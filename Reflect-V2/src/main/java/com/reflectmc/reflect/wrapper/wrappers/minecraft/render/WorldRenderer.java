package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.WorldRenderer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.BufferBuilder",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class WorldRenderer extends WrapperBase {
    @WrapMethod(deobfName = "begin",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "begin",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method begin;
    @WrapMethod(deobfName = "pos",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "pos",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method pos;
    @WrapMethod(deobfName = "endVertex",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "endVertex",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method endVertex;
    @WrapMethod(deobfName = "color",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(deobfName = "color",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4f;
    @WrapMethod(deobfName = "color",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(IIII)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(deobfName = "color",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(IIII)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4I;
    @WrapMethod(deobfName = "putColorMultiplier",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "putColorMultiplier",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method putColorMultiplier;
    @WrapMethod(deobfName = "tex",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "tex",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method tex;
    public WorldRenderer(Object wrap) {
        super(wrap);
    }
    public void begin(int glMode, VertexFormat format)
    {
        invokeMethod(begin,glMode,format.getWrappedObject());
    }
    public WorldRenderer pos(double x, double y, double z)
    {
        invokeMethod(pos,x,y,z);
        return this;
    }
    public void endVertex()
    {
        invokeMethod(endVertex);
    }
    public WorldRenderer color(float red, float green, float blue, float alpha)
    {
        invokeMethod(color_4f,red,green,blue,alpha);
        return this;
    }
    public WorldRenderer color(int i1,int i2,int i3,int i4) {
        invokeMethod(color_4I,i1,i2,i3,i4);
        return this;
    }

    public WorldRenderer tex(double u2, double v1) {
        invokeMethod(tex,u2,v1);
        return this;
    }
}
