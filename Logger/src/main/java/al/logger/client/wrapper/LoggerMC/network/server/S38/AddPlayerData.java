package al.logger.client.wrapper.LoggerMC.network.server.S38;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.GameProfile;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S38PacketPlayerListItem$AddPlayerData", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class AddPlayerData extends IWrapper {
    @ClassInstance
    public static Class AddPlayerDataClass;
    @WrapField(mcpName = "profile", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field profile;
    @WrapField(mcpName = "ping", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field ping;
    @WrapField(mcpName = "gamemode", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field gamemode;

    public AddPlayerData(Object obj) {
        super(obj);
    }

    public GameProfile getProfile() {
        return new GameProfile(getField(profile));
    }

    public Enum getGameMode() {
        return (Enum) getField(gamemode);
    }

    public int getPing() {
        return (int) getField(ping);
    }

    @Override
    public String toString() {
        return getWrappedObject().toString();
    }
}
