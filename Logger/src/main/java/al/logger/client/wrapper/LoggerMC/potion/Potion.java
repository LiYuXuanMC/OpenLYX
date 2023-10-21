package al.logger.client.wrapper.LoggerMC.potion;

import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.environment.Environment;
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

@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Potion extends IWrapper {
    @WrapField(mcpName = "REGISTRY", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field REGISTRY;
    @WrapObject(mcpName = "moveSpeed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion moveSpeed;
    @WrapObject(mcpName = "moveSlowdown", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion moveSlowdown;
    @WrapObject(mcpName = "digSpeed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion digSpeed;
    @WrapObject(mcpName = "digSlowdown", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion digSlowdown;
    @WrapObject(mcpName = "damageBoost", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion damageBoost;
    @WrapObject(mcpName = "heal", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion heal;
    @WrapObject(mcpName = "harm", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion harm;
    @WrapObject(mcpName = "jump", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion jump;
    @WrapObject(mcpName = "confusion", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion confusion;
    @WrapObject(mcpName = "regeneration", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion regeneration;
    @WrapObject(mcpName = "resistance", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion resistance;
    @WrapObject(mcpName = "fireResistance", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion fireResistance;
    @WrapObject(mcpName = "waterBreathing", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion waterBreathing;
    @WrapObject(mcpName = "invisibility", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion invisibility;
    @WrapObject(mcpName = "blindness", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion blindness;
    @WrapObject(mcpName = "nightVision", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion nightVision;
    @WrapObject(mcpName = "hunger", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion hunger;
    @WrapObject(mcpName = "weakness", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion weakness;
    @WrapObject(mcpName = "poison", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion poison;
    @WrapObject(mcpName = "wither", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion wither;
    @WrapObject(mcpName = "healthBoost", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion healthBoost;
    @WrapObject(mcpName = "absorption", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Potion absorption;
    @WrapMethod(mcpName = "getId", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getId;

    public Potion(Object obj) {
        super(obj);
    }

    public int getId() {
        return (int) invoke(getId);
    }

    public static Map<ResourceLocation, Potion> getRegistryObjects() {
        Map<ResourceLocation, Potion> registryObject = new HashMap<ResourceLocation, Potion>();
        RegistryNamespaced registry = new RegistryNamespaced(ReflectUtil.getField(REGISTRY, null));
        Map<Object, Object> objets = registry.getRegistryObjects();
        objets.forEach((R, E) -> {
            registryObject.put(new ResourceLocation(R), new Potion(E));
        });
        return registryObject;
    }
}
