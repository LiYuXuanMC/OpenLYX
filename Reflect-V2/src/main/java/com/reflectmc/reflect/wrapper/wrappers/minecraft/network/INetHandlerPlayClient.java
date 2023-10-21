package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.network.play.INetHandlerPlayClient",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
public class INetHandlerPlayClient extends INetHandler{
    public INetHandlerPlayClient(Object obj) {
        super(obj);
    }
}
