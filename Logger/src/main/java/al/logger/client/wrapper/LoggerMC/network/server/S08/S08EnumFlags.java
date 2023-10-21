package al.logger.client.wrapper.LoggerMC.network.server.S08;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.server.S08PacketPlayerPosLook$EnumFlags",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S08EnumFlags extends EnumWrapper {
    @WrapEnum(mcpName = "X",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum X;
    @WrapEnum(mcpName = "Y",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum Y;
    @WrapEnum(mcpName = "Z",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum Z;
    @WrapEnum(mcpName = "Y_ROT",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum Y_ROT;
    @WrapEnum(mcpName = "X_ROT",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum X_ROT;
    public S08EnumFlags(Object obj) {
        super(obj);
    }
}
