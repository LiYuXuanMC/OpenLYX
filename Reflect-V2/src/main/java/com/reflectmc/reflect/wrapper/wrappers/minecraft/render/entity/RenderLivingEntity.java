package com.reflectmc.reflect.wrapper.wrappers.minecraft.render.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.RendererLivingEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.RenderLivingBase",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class RenderLivingEntity extends Render {
    @ClassInstance
    public static Class<?> RenderLivingEntityClass;
    public RenderLivingEntity(Object obj) {
        super(obj);
    }
    @WrapMethod(deobfName = "doRender",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V")
    @WrapMethod(deobfName = "doRender",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V")
    public static Method doRender;
    @WrapField(deobfName = "mainModel", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "mainModel", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field mainModel;
    @WrapMethod(deobfName = "interpolateRotation", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method interpolateRotation;
    @WrapMethod(deobfName = "renderLivingAt", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method renderLivingAt;
}
