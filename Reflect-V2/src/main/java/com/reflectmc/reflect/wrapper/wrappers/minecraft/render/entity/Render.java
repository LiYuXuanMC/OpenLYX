package com.reflectmc.reflect.wrapper.wrappers.minecraft.render.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.Render",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.Render",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Render extends WrapperBase {
    @ClassInstance
    public static Class<?> RenderClass;
    public Render(Object obj) {
        super(obj);
    }
}
