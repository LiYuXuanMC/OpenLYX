package al.nya.reflect.wrapper.wraps.wrapper.potion;

import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapObject;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;
import al.nya.reflect.wrapper.wraps.wrapper.utils.registry.RegistryNamespaced;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.potion.Potion", targetMap = Maps.Srg1_12_2)
public class Potion extends IWrapper {
    @WrapField(mcpName = "REGISTRY", targetMap = Maps.Srg1_12_2)
    public static Field REGISTRY;
    @WrapObject(mcpName = "moveSpeed", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion moveSpeed;
    @WrapObject(mcpName = "moveSlowdown", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion moveSlowdown;
    @WrapObject(mcpName = "digSpeed", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion digSpeed;
    @WrapObject(mcpName = "digSlowdown", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion digSlowdown;
    @WrapObject(mcpName = "damageBoost", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion damageBoost;
    @WrapObject(mcpName = "heal", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion heal;
    @WrapObject(mcpName = "harm", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion harm;
    @WrapObject(mcpName = "jump", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion jump;
    @WrapObject(mcpName = "confusion", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion confusion;
    @WrapObject(mcpName = "regeneration", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion regeneration;
    @WrapObject(mcpName = "resistance", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion resistance;
    @WrapObject(mcpName = "fireResistance", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion fireResistance;
    @WrapObject(mcpName = "waterBreathing", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion waterBreathing;
    @WrapObject(mcpName = "invisibility", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion invisibility;
    @WrapObject(mcpName = "blindness", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion blindness;
    @WrapObject(mcpName = "nightVision", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion nightVision;
    @WrapObject(mcpName = "hunger", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion hunger;
    @WrapObject(mcpName = "weakness", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion weakness;
    @WrapObject(mcpName = "poison", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion poison;
    @WrapObject(mcpName = "wither", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion wither;
    @WrapObject(mcpName = "healthBoost", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion healthBoost;
    @WrapObject(mcpName = "absorption", targetMap = Maps.Srg1_8_9, makeWrap = Potion.class)
    public static Potion absorption;
    @WrapMethod(mcpName = "getId", targetMap = Maps.Srg1_8_9)
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
