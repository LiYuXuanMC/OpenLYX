package com.reflectmc.reflect.wrapper.mapper;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.wrapper.mapper.node.ClassNode;
import com.reflectmc.reflect.wrapper.mapper.node.FieldNode;
import com.reflectmc.reflect.wrapper.mapper.node.MethodNode;
import com.reflectmc.reflect.wrapper.mapper.node.Node;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    @Getter
    private Environment version;
    @Getter
    private final List<Environment> antiCheats = new ArrayList<>();
    @Getter
    private List<Node> nodes = new ArrayList<>();
    private static String NAC_RES_CLASS = "cc.nplus.Oo00ooOOO0OooOoo0OO0oOooOo";
    public Mapper(){

    }
    public void genMap(){
        String map = new String(Reflect.getINSTANCE().getResourceManager().getResourceByAlias("map").getBuffer().array());
        String[] lines = map.split("\n");
        for (String line : lines) {
            Node node = Node.wrap(line);
            if (node != null)
                nodes.add(node);
        }
        System.out.println(nodes.size()+" nodes");
    }
    public void detectEnvironment(){
        detectVersion();
        detectModifier();
        detectAntiCheat();
    }
    public ClassNode findObfClass(String deobfName){
        for (Node node : nodes) {
            if (node instanceof ClassNode){
                if (node.getDeobfClassName().equals(deobfName)) return (ClassNode) node;
            }
        }
        return null;
    }
    public FieldNode findObfField(Class<?> obfClass,String deobfFieldName){
        String className = obfClass.getCanonicalName();
        for (Node node : nodes) {
            if (node instanceof FieldNode){
                if (node.getObfClassName().equals(className) && ((FieldNode) node).getDeobfFieldName().equals(deobfFieldName)) return (FieldNode) node;
                if (node.getObfClassName().replace("$",".").equals(className) && ((FieldNode) node).getDeobfFieldName().equals(deobfFieldName)) return (FieldNode) node;
            }
        }
        return null;
    }
    public List<MethodNode> findObfMethods(Class<?> obfClass,String deobfMethodName){
        String className = obfClass.getCanonicalName();
        List<MethodNode> methodNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node instanceof MethodNode){
                if (node.getObfClassName().equals(className) && ((MethodNode) node).getDeobfMethodName().equals(deobfMethodName)) methodNodes.add((MethodNode) node);
            }
        }
        return methodNodes;
    }
    public boolean isForge(){
        return getGuiIngameForge() != null;
    }
    public boolean isVersionMatch(Environment... env){
        for (Environment environment : env) {
            if (environment == version) return true;
        }
        return false;
    }
    public static Class<?> getGuiIngameForge(){
        try {
            return Class.forName("net.minecraftforge.client.GuiIngameForge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void detectVersion(){
        if (is189Srg()){
            version = Environment.Forge189;
        }
        if (is1122Srg()){
            version = Environment.Forge1122;
        }
        if (is189Notch()){
            version = Environment.Vanilla189;
        }
    }
    public boolean hasAntiCheat(Environment environment){
        for (Environment antiCheat : antiCheats) {
            if (antiCheat == environment){
                return true;
            }
        }
        return false;
    }
    private void detectModifier(){

    }
    private void detectAntiCheat(){
        if (isMAC()){
            antiCheats.add(Environment.MargelesAntiCheat);
        }
        if (isNAC()){
            antiCheats.add(Environment.NPlusAntiCheat);
        }
    }
    private static boolean isMAC() {
        return getMAC() != null;
    }
    private static boolean isNAC(){
        return getNAC() != null;
    }
    public static Class<?> getNAC() {
        try {
            return Class.forName(NAC_RES_CLASS);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    private static Class<?> getMAC() {
        try {
            return Class.forName("cn.margele.netease.clientside.MargeleAntiCheat");
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
}
