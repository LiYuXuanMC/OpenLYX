package al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition$MovingObjectType",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.math.RayTraceResult$Type",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class MovingObjectType extends EnumWrapper {
    public MovingObjectType(Object obj) {
        super(obj);
    }
    @WrapEnum(mcpName = "BLOCK",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "BLOCK",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum BLOCK;
    @WrapEnum(mcpName = "MISS",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "MISS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum MISS;
    @WrapEnum(mcpName = "ENTITY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "ENTITY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum ENTITY;

}
