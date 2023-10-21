package com.reflectmc.reflect.wrapper.wrappers.minecraft.potion;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapObject;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.potion.Potion", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.potion.Potion", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Potion extends WrapperBase {
    @WrapField(deobfName = "REGISTRY", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field REGISTRY;
    @WrapObject(deobfName = "moveSpeed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion moveSpeed;
    @WrapObject(deobfName = "moveSlowdown", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion moveSlowdown;
    @WrapObject(deobfName = "digSpeed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion digSpeed;
    @WrapObject(deobfName = "digSlowdown", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion digSlowdown;
    @WrapObject(deobfName = "damageBoost", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion damageBoost;
    @WrapObject(deobfName = "heal", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion heal;
    @WrapObject(deobfName = "harm", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion harm;
    @WrapObject(deobfName = "jump", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion jump;
    @WrapObject(deobfName = "confusion", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion confusion;
    @WrapObject(deobfName = "regeneration", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion regeneration;
    @WrapObject(deobfName = "resistance", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion resistance;
    @WrapObject(deobfName = "fireResistance", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion fireResistance;
    @WrapObject(deobfName = "waterBreathing", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion waterBreathing;
    @WrapObject(deobfName = "invisibility", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion invisibility;
    @WrapObject(deobfName = "blindness", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion blindness;
    @WrapObject(deobfName = "nightVision", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion nightVision;
    @WrapObject(deobfName = "hunger", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion hunger;
    @WrapObject(deobfName = "weakness", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion weakness;
    @WrapObject(deobfName = "poison", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion poison;
    @WrapObject(deobfName = "wither", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion wither;
    @WrapObject(deobfName = "healthBoost", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion healthBoost;
    @WrapObject(deobfName = "absorption", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Potion absorption;
    @WrapMethod(deobfName = "getId", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getId;

    public Potion(Object obj) {
        super(obj);
    }

    public int getId() {
        return (int) invokeMethod(getId);
    }

    /*public static Map<ResourceLocation, Potion> getRegistryObjects() {
        Map<ResourceLocation, Potion> registryObject = new HashMap<ResourceLocation, Potion>();
        RegistryNamespaced registry = new RegistryNamespaced(getStaticField(REGISTRY));
        Map<Object, Object> objets = registry.getRegistryObjects();
        objets.forEach((R, E) -> {
            registryObject.put(new ResourceLocation(R), new Potion(E));
        });
        return registryObject;
    }
     */
}
