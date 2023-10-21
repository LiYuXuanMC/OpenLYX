package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.GameProfile;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.client.network.NetworkPlayerInfo",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.network.NetworkPlayerInfo",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class NetworkPlayerInfo extends WrapperBase {
    @WrapField(deobfName = "gameProfile",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "gameProfile",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field gameProfile;
    public NetworkPlayerInfo(Object obj) {
        super(obj);
    }
    public GameProfile getGameProfile(){
        return new GameProfile(getField(gameProfile));
    }
}
