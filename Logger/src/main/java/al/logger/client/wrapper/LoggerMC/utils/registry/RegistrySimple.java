package al.logger.client.wrapper.LoggerMC.utils.registry;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.enchantment.Enchantment;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.util.registry.RegistrySimple",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RegistrySimple extends IWrapper {
    @WrapField(mcpName = "registryObjects",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field registryObjects;
    public RegistrySimple(Object obj) {
        super(obj);
    }
    public Map getRegistryObjects(){
        return (Map) getField(registryObjects);
    }
}
