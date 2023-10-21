package al.logger.client.wrapper.LoggerMC.multiplayer;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.ServerData",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.ServerData",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ServerData extends IWrapper {
    @WrapField(mcpName = "serverIP",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "serverIP",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field serverIP;
    public ServerData(Object obj) {
        super(obj);
    }
    public String getServerIP(){
        return (String) getField(serverIP);
    }
}
