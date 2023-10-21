package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.network.INetHandler",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
public class INetHandler extends WrapperBase {
    public INetHandler(Object obj) {
        super(obj);
    }
}
