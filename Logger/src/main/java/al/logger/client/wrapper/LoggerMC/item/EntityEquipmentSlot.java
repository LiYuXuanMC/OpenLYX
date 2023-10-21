package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.inventory.EntityEquipmentSlot",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityEquipmentSlot extends EnumWrapper {
    @WrapEnum(mcpName = "MAINHAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum MAINHAND;
    @WrapEnum(mcpName = "OFFHAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum OFFHAND;
    @WrapEnum(mcpName = "FEET",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum FEET;
    @WrapEnum(mcpName = "LEGS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum LEGS;
    @WrapEnum(mcpName = "CHEST",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum CHEST;
    @WrapEnum(mcpName = "HEAD",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum HEAD;
    @WrapField(mcpName = "index",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field index;
    public EntityEquipmentSlot(Object obj) {
        super(obj);
    }
    public static int getIndex(Enum e){
        return (int) ReflectUtil.getField(index,e);
    }
}
