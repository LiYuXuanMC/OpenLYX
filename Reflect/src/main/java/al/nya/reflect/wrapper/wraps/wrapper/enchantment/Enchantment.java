package al.nya.reflect.wrapper.wraps.wrapper.enchantment;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapObject;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;
import al.nya.reflect.wrapper.wraps.wrapper.utils.registry.RegistryNamespaced;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.enchantment.Enchantment", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.enchantment.Enchantment", targetMap = Maps.Srg1_12_2)
public class Enchantment extends IWrapper {
    @WrapObject(mcpName = "sharpness", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment sharpness;
    @WrapObject(mcpName = "fireAspect", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment fireAspect;
    @WrapObject(mcpName = "protection", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment protection;
    @WrapObject(mcpName = "blastProtection", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment blastProtection;
    @WrapObject(mcpName = "fireProtection", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment fireProtection;
    @WrapObject(mcpName = "thorns", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment thorns;
    @WrapObject(mcpName = "unbreaking", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment unbreaking;
    @WrapObject(mcpName = "featherFalling", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment featherFalling;
    @WrapObject(mcpName = "efficiency", targetMap = Maps.Srg1_8_9, makeWrap = Enchantment.class)
    public static Enchantment efficiency;
    @WrapField(mcpName = "effectId", targetMap = Maps.Srg1_8_9)
    public static Field effectId;
    @WrapField(mcpName = "REGISTRY", targetMap = Maps.Srg1_12_2)
    public static Field REGISTRY;
    @WrapMethod(mcpName = "getEnchantmentID", targetMap = Maps.Srg1_12_2)
    public static Method getEnchantmentID;
    @WrapMethod(mcpName = "getEnchantmentByID", targetMap = Maps.Srg1_12_2)
    public static Method getEnchantmentByID;

    public Enchantment(Object obj) {
        super(obj);
    }

    public int getEffectId() {
        return (int) getField(effectId);
    }

    public static Enchantment getEnchantmentByID(int id) {
        return new Enchantment(ReflectUtil.invoke(getEnchantmentByID, null, id));
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
