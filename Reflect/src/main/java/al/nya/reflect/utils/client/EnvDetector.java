package al.nya.reflect.utils.client;

import al.nya.reflect.resource.ResourceManager;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;

import java.lang.reflect.Field;

public class EnvDetector {
    public static boolean detect(){
        // if (ForgeDetector.isModedGuiIngame()){
            if (is189Srg()){
                Wrapper.env = Maps.Srg1_8_9;
                ResourceManager.setMap("189Srg.srg");
                return true;
            }
            if (is1122Srg()){
                Wrapper.env = Maps.Srg1_12_2;
                ResourceManager.setMap("1122Srg.srg");
                return true;
            }
        // }else {
            if (is189Notch()){
                Wrapper.env = Maps.Srg1_8_9;
                ResourceManager.setMap("189Notch.srg");
                return true;
            }
        // }
        return false;
    }
    public static boolean is1122Srg(){
        try {
            Class.forName("net.minecraft.client.renderer.BufferBuilder");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static boolean is189Srg(){
        try {
            Class.forName("net.minecraft.client.renderer.WorldRenderer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static boolean is189Notch(){
        try {
            Class<?> pk = Class.forName("pk");
            try {
                Field field = pk.getDeclaredField("u");
                if (field.getType() == double.class){
                    return true;
                } else {
                    return false;
                }
            } catch (NoSuchFieldException e) {
                return false;
            }
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
