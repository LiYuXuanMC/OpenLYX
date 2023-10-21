package al.logger.client.wrapper.environment;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentDetector {
    @Getter
    private static Environment minecraft;
    @Getter
    private static Environment modifier;
    @Getter
    private static Environment lwjgl;
    @Getter
    private static List<Environment> antiCheats = new ArrayList<>();
    @Getter
    private static List<Environment> mods = new ArrayList<>();

    public static void detect(){
        detectModifier();
        detectMinecraft();
        detectLWJGL();
        detectAntiCheats();
        detectMods();
    }
    public static void detectLWJGL(){

    }
    public static boolean hasAntiCheat(Environment ac){
        for (Environment antiCheat : antiCheats) {
            if (antiCheat == ac){
                return true;
            }
        }
        return false;
    }
    private static void detectMinecraft(){
        if (is189Srg()){
            minecraft = Environment.MINECRAFT_VERSION_1_8_9_Forge;
            return;
        }
        if (is189Notch()){
            minecraft = Environment.MINECRAFT_VERSION_1_8_9_Vanilla;
            return;
        }
        if (is1122Srg()){
            minecraft = Environment.MINECRAFT_VERSION_1_12_2_Forge;
            return;
        }
    }
    private static void detectModifier(){
        modifier = Environment.Vanilla;
        if (getLunar() != null){
            modifier = Environment.Lunar;
        }
        if (getGuiIngameForge() != null){
            modifier = Environment.Forge;
        }
        if (getFeather() != null){
            modifier = Environment.Feather;
        }
    }
    private static void detectAntiCheats(){
        if (getNAC() != null){
            antiCheats.add(Environment.NPlusAntiCheat);
        }
        if (getMAC() != null){
            antiCheats.add(Environment.MargelesAntiCheat);
        }
        if (getHyCraftScoreBoard() != null){
            antiCheats.add(Environment.HyCraftScreenShot);
        }
        if (getEACDetector() != null){
            antiCheats.add(Environment.EnsembleAntiCheat);
        }
        if (getELAC() != null){
            antiCheats.add(Environment.EastLandAntiCheat);
        }
    }
    private static void detectMods(){
        if (getOrangemarshallBlockHitAnimation() != null){
            mods.add(Environment.OrangemarshallBlockhitAnimation);
        }
    }
    public static boolean hasMod(Environment mod){
        for (Environment mod1 : mods) {
            if (mod1 == mod){
                return true;
            }
        }
        return false;
    }
    public static Class<?> getOrangemarshallBlockHitAnimation(){
        try {
            return Class.forName("com.orangemarshall.animations.BlockhitAnimation");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static Class<?> getFeather(){
        try {
            return Class.forName("net.digitalingot.feather.FeatherMod");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getLunar(){
        try {
            return Class.forName("com.lunarclient.common.v1.Color");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getHyGui(){
        try {
            return Class.forName("cn.hycraft.core.gui.HyCraftGui");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getELAC(){
        try {
            return Class.forName("cn.eastland.anticheat.EastLandAntiCheat");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static Class<?> getEACDetector() {
        try {
            //操你妈千鹤 你更新你妈呢
            return Class.forName("EAC.coremod.EACDetector");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static Class<?> getHyCraftScoreBoard() {
        try {
            return Class.forName("cn.hycraft.core.module.impl.game.ScoreBoard");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static Class<?> getNAC() {
        try {
            return Class.forName("cc.nplus.core.CoreLoader");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getMAC() {
        try {
            return Class.forName("cn.margele.netease.clientside.MargeleAntiCheat");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getEACRunning() {
        try {
            return Class.forName("EAC.coremod.Running");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    public static Class<?> getEAC() {
        try {
            return Class.forName("EAC.EAC");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static boolean is1122Srg(){
        try {
            Class.forName("net.minecraft.client.renderer.BufferBuilder");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    private static boolean is189Srg(){
        try {
            Class.forName("net.minecraft.client.renderer.WorldRenderer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    private static boolean is189Notch(){
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
    public static Class<?> getGuiIngameForge(){
        try {
            return Class.forName("net.minecraftforge.client.GuiIngameForge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
