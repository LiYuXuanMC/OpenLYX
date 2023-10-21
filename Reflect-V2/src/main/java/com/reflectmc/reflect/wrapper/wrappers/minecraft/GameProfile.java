package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.util.UUID;

public class GameProfile extends WrapperBase {
    public static Class GameProfile;
    public static Field id;
    public static Field name;
    static {
        try {
            GameProfile = Class.forName("com.mojang.authlib.GameProfile");
            id = GameProfile.getDeclaredField("id");
            id.setAccessible(true);
            name = GameProfile.getDeclaredField("name");
            name.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public GameProfile(Object obj) {
        super(obj);
    }
    public UUID getID(){
        return (UUID) getField(id);
    }
    public String getName(){
        return (String) getField(name);
    }
}
