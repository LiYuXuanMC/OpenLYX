package com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.client.multiplayer.ServerData",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.multiplayer.ServerData",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ServerData extends WrapperBase {
    @WrapField(deobfName = "serverIP",targetEnvironment = {Environment.Forge189, Environment.Vanilla189})
    @WrapField(deobfName = "serverIP",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field serverIP;
    public ServerData(Object obj) {
        super(obj);
    }
    public String getServerIP(){
        return (String) getField(serverIP);
    }
}
