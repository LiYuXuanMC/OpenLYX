package com.reflectmc.reflect.wrapper.wrappers.cactus;

import com.reflectmc.libraries.asm.tree.MethodNode;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.transform.transformers.ClassDumpTransformer;
import com.reflectmc.reflect.utils.FileUtil;
import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.mapper.node.Signature;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.GameSettings;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiIngame;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.FontRenderer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CactusAdaptor {
    private static List<ClassDumpTransformer> transformers = new ArrayList<>();
    public static void deobfuscate() throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        Map<String,byte[]> deobfuscationFiles = FileUtil.processZip(Reflect.getINSTANCE().getResourceManager().getResource("DeobfuscationKit.zip").getBuffer().array());
        for (CactusWrappingInfo cactusWrappingInfo : Wrapper.getCactusWrapping()) {
            if (!classes.contains(cactusWrappingInfo.getTargetClass())){
                classes.add(cactusWrappingInfo.getTargetClass());
            }
        }
        for (Class<?> aClass : classes) {
            transformers.add(new ClassDumpTransformer(aClass));
        }
        for (ClassDumpTransformer transformer : transformers) {
            Reflect.getINSTANCE().getTransformManager().addTransformer(transformer);
        }
        Reflect.getINSTANCE().getTransformManager().transform();
        int index = 0;
        for (CactusWrappingInfo cactusWrappingInfo : Wrapper.getCactusWrapping()) {
            index ++;
            Reflect.getINSTANCE().getInjectorSocket().updateProgress("Wrapping",index,Wrapper.getCactusWrapping().size());
            if (cactusWrappingInfo.getAnnotation() instanceof WrapMethod){
                WrapMethod method = (WrapMethod) cactusWrappingInfo.getAnnotation();
                Cactus cactus = new Cactus();

                byte[] deobfClassBytes = deobfuscationFiles.get(cactusWrappingInfo.getTargetClass().getSimpleName() + ".deobf.class");
                byte[] obfuscateClassBytes = null;
                for (ClassDumpTransformer transformer : transformers) {
                    if (transformer.getTarget() == cactusWrappingInfo.getTargetClass()){
                        obfuscateClassBytes = transformer.getClassBytes();
                        break;
                    }
                }
                String deobfConfig = "m "+method.deobfName() +" "+method.signature();
                cactus.input(deobfClassBytes,obfuscateClassBytes,deobfConfig);
                DeobfuscationResult result = cactus.run();
                if (result.getStatus() == DeobfuscationResult.Status.Fail){
                    throw new Exception("Deobfuscation fail "+method.deobfName() +" "+method.signature() +" "+result.getReason());
                }
                DeobfuscationResult.DeobfuscationItem item = result.getResults().get(0);
                MethodNode methodNode = (MethodNode) item.getOrigin();
                Signature signature = new Signature(methodNode.desc);
                signature.parse();
                Method method1 = cactusWrappingInfo.getTargetClass().getDeclaredMethod(methodNode.name,signature.getParameter());
                method1.setAccessible(true);
                System.out.println(method.deobfName() + " "+method.signature()+" located");
                cactusWrappingInfo.getWrapper().set(null,method1);
            }
        }
    }
    public static void locateVariable(){
        for (Field declaredField : Minecraft.MinecraftClass.getDeclaredFields()) {
            if (declaredField.getType() == GuiIngame.GuiIngameClass){
                declaredField.setAccessible(true);
                Minecraft.ingameGUI = declaredField;
                System.out.println("ingameGui located");
            }
            if (declaredField.getType() == GameSettings.GameSettingsClass){
                declaredField.setAccessible(true);
                Minecraft.gameSettings = declaredField;
                System.out.println("gameSettings located");
            }
            if (declaredField.getType() == FontRenderer.FontRendererClass){
                if (declaredField.getName().startsWith("Na")){
                    declaredField.setAccessible(true);
                    Minecraft.fontRendererObj = declaredField;
                    System.out.println("fontRendererObj located");
                }
            }
        }
        for (Field declaredField : Entity.EntityClass.getDeclaredFields()) {
            if (declaredField.getType() == float.class){
                if (declaredField.getName().startsWith("Na")){
                    declaredField.setAccessible(true);
                    Entity.stepHeight = declaredField;
                    System.out.println("stepHeight located");
                }
            }
        }
    }
}
