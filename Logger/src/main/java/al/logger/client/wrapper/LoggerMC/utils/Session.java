package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.GameProfile;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Session",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.Session",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Session extends IWrapper {
    @WrapMethod(mcpName = "getProfile",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getProfile",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getProfile;
    @WrapField(mcpName = "username",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "username",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field username;
    @WrapConstructor(signature = {String.class,String.class,String.class,String.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> Session_String_String_String_String;

    public Session(String p_username, String p_playerID, String p_token, String p_sessionType) {
        super(ReflectUtil.construction(Session_String_String_String_String, p_username, p_playerID, p_token, p_sessionType));
    }

    public Session(Object obj) {
        super(obj);
    }
    public GameProfile getProfile(){
        return new GameProfile(invoke(getProfile));
    }
    public String getUsername(){
        return (String) getField(username);
    }
    public void setUsername(String p_username){
        setField(username,p_username);
    }
}
