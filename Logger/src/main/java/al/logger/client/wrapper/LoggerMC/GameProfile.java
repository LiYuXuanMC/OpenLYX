package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.util.UUID;

@WrapperClass(mcpName = "com.mojang.authlib.GameProfile", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class GameProfile extends IWrapper {
    @ClassInstance
    public static Class GameProfile;
    @WrapField(mcpName = "id", customSrgName = "id", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field id;
    @WrapField(mcpName = "name", customSrgName = "name", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field name;

    public GameProfile(Object obj) {
        super(obj);
    }

    public UUID getID() {
        return (UUID) getField(id);
    }

    public String getName() {
        return (String) getField(name);
    }

    public void setName(String newName) {
        setField(name, newName);
    }
}
