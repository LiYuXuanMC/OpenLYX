package al.logger.client.wrapper.LoggerMC.enchantment;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapObject;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.LoggerMC.utils.registry.RegistryNamespaced;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.enchantment.Enchantment", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.enchantment.Enchantment", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Enchantment extends IWrapper {
    @WrapObject(mcpName = "sharpness", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment sharpness;
    @WrapObject(mcpName = "fireAspect", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment fireAspect;
    @WrapObject(mcpName = "protection", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment protection;
    @WrapObject(mcpName = "blastProtection", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment blastProtection;
    @WrapObject(mcpName = "fireProtection", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment fireProtection;
    @WrapObject(mcpName = "thorns", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment thorns;
    @WrapObject(mcpName = "unbreaking", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment unbreaking;
    @WrapObject(mcpName = "featherFalling", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment featherFalling;
    @WrapObject(mcpName = "efficiency", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enchantment efficiency;
    @WrapField(mcpName = "effectId", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field effectId;
    @WrapField(mcpName = "REGISTRY", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field REGISTRY;
    @WrapMethod(mcpName = "getEnchantmentID", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEnchantmentID;
    @WrapMethod(mcpName = "getEnchantmentByID", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEnchantmentByID;

    public Enchantment(Object obj) {
        super(obj);
    }

    public int getEffectId() {
        return (int) getField(effectId);
    }

    public static Enchantment getEnchantmentByID(int id) {
        return new Enchantment(invokeStatic(getEnchantmentByID, id));
    }

    public static Map<ResourceLocation, Enchantment> getRegistryObjects() {
        Map<ResourceLocation, Enchantment> registryObject = new HashMap<ResourceLocation, Enchantment>();
        RegistryNamespaced registry = new RegistryNamespaced(ReflectUtil.getField(REGISTRY, null));
        Map<Object, Object> objets = registry.getRegistryObjects();
        objets.forEach((R, E) -> {
            registryObject.put(new ResourceLocation(R), new Enchantment(E));
        });
        return registryObject;
    }
}
