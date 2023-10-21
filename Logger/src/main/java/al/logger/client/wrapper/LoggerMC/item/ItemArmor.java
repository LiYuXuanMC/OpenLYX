package al.logger.client.wrapper.LoggerMC.item;


import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemArmor extends Item{
@ClassInstance    
public static Class ItemArmorClass;
    @WrapField(mcpName = "damageReduceAmount",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "damageReduceAmount",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field damageReduceAmount;
    @WrapField(mcpName = "armorType",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "armorType",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field armorType;
    public ItemArmor(Object obj) {
        super(obj);
    }
    public static boolean isItemArmor(Item item){
        return ItemArmorClass.isInstance(item.getWrappedObject());
    }
    public int getDamageReduceAmount(){
        return (int) getField(damageReduceAmount);
    }
    public int getArmorType(){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            return EntityEquipmentSlot.getIndex(getArmorTypeV1_12_2());
        }
        return (int) getField(armorType);
    }
    public Enum getArmorTypeV1_12_2(){
        return (Enum) getField(armorType);
    }
}
