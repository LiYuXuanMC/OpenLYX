package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.Tessellator",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.Tessellator",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Tessellator extends WrapperBase {
    @WrapMethod(deobfName = "getInstance", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getInstance", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getInstance;
    @WrapField(deobfName = "worldRenderer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "buffer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field worldRenderer;
    @WrapMethod(deobfName = "draw", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "draw", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method draw;
    public Tessellator(Object wrap) {
        super(wrap);
    }
    public static Tessellator getInstance(){
        return new Tessellator(invokeStaticMethod(getInstance));
    }
    public WorldRenderer getWorldRenderer(){
        return new WorldRenderer(getField(worldRenderer));
    }
    public void draw(){
        invokeMethod(draw);
    }
}
