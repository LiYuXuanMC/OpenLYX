package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapObject;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;


@WrapperClass(deobfName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class DefaultVertexFormats extends WrapperBase {
    @WrapObject(deobfName = "POSITION_TEX_COLOR", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "POSITION_TEX_COLOR", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static VertexFormat POSITION_TEX_COLOR;
    @WrapObject(deobfName = "POSITION", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "POSITION", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static VertexFormat POSITION;
    @WrapObject(deobfName = "POSITION_COLOR", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapObject(deobfName = "POSITION_COLOR", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static VertexFormat POSITION_COLOR;

    public DefaultVertexFormats(Object obj) {
        super(obj);
    }
}
