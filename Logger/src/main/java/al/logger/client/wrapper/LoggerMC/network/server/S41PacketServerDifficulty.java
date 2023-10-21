package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S41PacketServerDifficulty",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S41PacketServerDifficulty extends Packet {
    @ClassInstance
    public static Class S41PacketServerDifficultyClass;
    @WrapField(mcpName = "difficulty", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field difficulty;
    @WrapField(mcpName = "difficultyLocked", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field difficultyLocked;



    public S41PacketServerDifficulty(Object obj) {
        super(obj);
    }

    public static boolean isS41PacketServerDifficulty(Packet p) {
        return S41PacketServerDifficultyClass.isInstance(p.getWrappedObject());
    }

}
