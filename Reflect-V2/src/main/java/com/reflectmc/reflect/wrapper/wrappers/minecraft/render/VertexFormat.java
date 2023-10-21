package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.client.renderer.vertex.VertexFormat",targetEnvironment = {Environment.Forge189,Environment.Forge1122})
@WrapperClass(deobfName = "net.minecraft.client.renderer.vertex.VertexFormat",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class VertexFormat extends WrapperBase {
    public VertexFormat(Object obj) {
        super(obj);
    }
}
