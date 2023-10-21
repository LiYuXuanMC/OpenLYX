package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.client.entity.AbstractClientPlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.entity.AbstractClientPlayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class AbstractClientPlayer extends EntityPlayer {
    public AbstractClientPlayer(Object obj) {
        super(obj);
    }
}
