package pub.ensemble.hillo.obf;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;
import pub.ensemble.hillo.CoreMod;
import pub.ensemble.hillo.core.EACSMgr;
import pub.ensemble.hillo.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class EACTransformer implements IClassTransformer {

    public static boolean isFirstLoad = false;
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        //必要的环境检测
        if (!(System.getSecurityManager() instanceof EACSMgr)) {
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
        }
        if (Integer.valueOf(Thread.currentThread().getName().hashCode()).equals("Attach Listener".hashCode()) || transformedName.startsWith("com.sun.proxy.$")) {
            System.out.println("transformedName = " + transformedName);
        }
        if (CoreMod.eac.checkAntiCheat(name.length(), transformedName.length()) != name.length() + transformedName.length() + CoreMod.eac.getHYCCode()) {
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
            return null;
        }
        //调整EACTransformer转换顺序 适配 Deobf
//        if(transformedName.equals("net.minecraftforge.fml.common.Loader") && !isFirstLoad){
//            try{
//                Field transField = LaunchClassLoader.class.getDeclaredField("transformers") ;
//                transField.setAccessible(true);
//                List<IClassTransformer> delList = new ArrayList<IClassTransformer>(2);
//                List<IClassTransformer> transformer = (List<IClassTransformer>)transField.get(Launch.classLoader);
//                IClassTransformer eac = null;
//                for (IClassTransformer s : transformer){
//                    if (s.getClass().getName().contains(this.getClass().getName())){
//                        eac = s;
//                    }else{
//                        delList.add(s);
//                    }
//                }
//                delList.add(eac);
//                transField.set(Launch.classLoader, delList);
//                if (eac != null){
//                    System.out.println("EAC Transformer Re-Trans!");
//                    isFirstLoad = true;
//                    Launch.classLoader.registerTransformer(eac.getClass().getName());
//                }else{
//                    System.out.println("EAC Transformer Not Found!");
//                    Runtime.getRuntime().halt(0);
//                    Minecraft.getMinecraft().shutdown();
//                    Minecraft.getMinecraft().shutdownMinecraftApplet();
//                }
//            }catch (Throwable e){
//                e.printStackTrace();
//            }
//        }

//        //System.out.println(transformedName);
//        ClassReader cr = new ClassReader(basicClass);
//        ClassNode cn = new ClassNode();
//        cr.accept(cn, 0);
//
//        String test = "startVirtualBox(){} "+System.getProperty ( "line.separator" )+"EACProtect public native void endVirtualBox()́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄";
//        String test2 = "endVirtualBox(){} "+System.getProperty ( "line.separator" )+"EACProtect public native void endVirtualBox()́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄́̀̂̃̈̄";
//
//        for (FieldNode fieldNode : cn.fields) {
////            if (transformedName.equals("net.minecraft.client.Minecraft")){
////                if (fieldNode.name.equals("fontRendererObj")) {
////                    fieldNode.name = test;
////                    // dump(cn,0);
////                }
////            }
////
////            if (transformedName.equals("net.minecraft.client.settings.KeyBinding")){
////                if (fieldNode.name.equals("pressed")) {
////                    fieldNode.name = test;
////                    //dump(cn,0);
////                }
////            }
//
//            if (transformedName.equals("net.minecraftforge.fml.common.eventhandler.EventBus")){
//                if (fieldNode.name.equals("listeners")){
//                    fieldNode.name = test;
//                    //dump(cn,0);
//                }
//            }
//
//            if (transformedName.equals("net.minecraftforge.common.MinecraftForge")){
//                if (fieldNode.name.equals("EVENT_BUS")){
//                    fieldNode.name = test;
//                    // dump(cn,0);
//                }
//            }
//
//
//        }
//
//        for (MethodNode methodNode : cn.methods) {
////            if (methodNode.name.equals("bindTexture") && transformedName.equals("net.minecraft.client.renderer.GlStateManager")) {
////                methodNode.name = test;
////            }
////
////            if (methodNode.name.equals("getNetHandler") && transformedName.equals("net.minecraft.client.Minecraft")) {
////                methodNode.name = test2;
////            }
////
////            if (methodNode.name.equals("onTick") && transformedName.equals("net.minecraft.client.settings.KeyBinding")) {
////                methodNode.name = test;
////            }
//
//
//            for (AbstractInsnNode insnNode : methodNode.instructions.toArray()) {
////                if (insnNode instanceof MethodInsnNode) {
////                    MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
////                    if (methodInsnNode.owner.equals("net/minecraft/client/renderer/GlStateManager") && methodInsnNode.name.equals("bindTexture")) {
////                        methodInsnNode.name = test;
////                        //dump(cn,0);
////                    }
////
////                    if (methodInsnNode.owner.equals("net/minecraft/client/Minecraft") && methodInsnNode.name.equals("getNetHandler")) {
////                        methodInsnNode.name = test2;
////                        //dump(cn,0);
////                    }
////                    if (methodInsnNode.owner.equals("net/minecraft/client/settings/KeyBinding") && methodInsnNode.name.equals("onTick")) {
////                        methodInsnNode.name = test;
////                        // dump(cn,0);
////                    }
////                }
//
//                if (insnNode instanceof FieldInsnNode){
//                    FieldInsnNode fieldInsnNode = (FieldInsnNode) insnNode;
////                    if (fieldInsnNode.owner.equals("net/minecraft/client/Minecraft") && fieldInsnNode.name.equals("fontRendererObj")){
////                        fieldInsnNode.name = test;
////                        //dump(cn,0);
////                    }
////                    if (fieldInsnNode.owner.equals("net/minecraft/client/settings/KeyBinding") && fieldInsnNode.name.equals("pressed")){
////                        fieldInsnNode.name = test;
////                        //    dump(cn,0);
////                    }
//                    if (fieldInsnNode.owner.equals("net/minecraftforge/common/MinecraftForge") && fieldInsnNode.name.equals("EVENT_BUS")){
//                        fieldInsnNode.name = test;
//                        //   dump(cn,0);
//                    }
//                    if (fieldInsnNode.owner.equals("net/minecraftforge/fml/common/eventhandler/EventBus") && fieldInsnNode.name.equals("listeners")){
//                        fieldInsnNode.name = test;
//                        //   dump(cn,0);
//                    }
//                }
//            }
//
//
//
////        System.out.println("transformedName = " + transformedName);
//
////        if (transformedName.equals("net.minecraft.client.network.NetworkPlayerInfo")) {
////            ClassReader cr = new ClassReader(basicClass);
////            ClassNode cn = new ClassNode();
////            cr.accept(cn, 0);
////            cn.access |= Opcodes.ACC_FINAL;
////            ClassWriter classWriter = new ClassWriter(0);
////            cn.accept(classWriter);
////            return classWriter.toByteArray();
////        }
//
//        }
//        if (transformedName.equals("net.minecraft.client.Minecraft")){
//            //dump(cn,0);
//        }
//        ClassWriter cw = new ClassWriter(0);
//        cn.accept(cw);
        return basicClass;
    }
    public static void dump (ClassNode cn,int flag){
        ClassWriter cw = new ClassWriter(flag);
        cn.accept(cw);
        FileUtils.writeFile(new File("./" + cn.name.replace("/", ".") + ".class"), cw.toByteArray());
        System.out.println("Dump written " + "./" + cn.name.replace("/", ".") + ".class");
    }
}
