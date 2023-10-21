package com.reflectmc.reflect.bridge;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Handle;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.loader.agent.ReflectNative;
import com.reflectmc.loader.utils.EncryptUtil;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.bridge.annotation.TargetMethodWrapping;
import com.reflectmc.reflect.bridge.bridges.BridgeBase;
import com.reflectmc.reflect.bridge.transformers.BridgeGetDataTransformer;
import com.reflectmc.reflect.gui.ClickGuiReflect;
import com.reflectmc.reflect.gui.Neverlose;
import com.reflectmc.reflect.utils.ClassUtil;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiScreen;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BridgeManager {
    private List<BridgeBuildInfo> bridges = new ArrayList<>();
    private Map<String,String> methodMap = new HashMap<>();
    private Map<String,Method> wrapperMap = new HashMap<>();
    public BridgeManager(){

    }
    public void init(){
        bridges.add(new BridgeBuildInfo(Neverlose.class));
        bridges.add(new BridgeBuildInfo(ClickGuiReflect.class));

        wrapperMap.put("initGui", GuiScreen.initGui);
        wrapperMap.put("drawScreen", GuiScreen.drawScreen);
        wrapperMap.put("onGuiClosed", GuiScreen.onGuiClosed);
        wrapperMap.put("updateScreen", GuiScreen.updateScreen);

        for (Method declaredMethod : BridgeBase.class.getDeclaredMethods()) {
            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof TargetMethodWrapping){
                    methodMap.put(declaredMethod.getName(),wrapperMap.get(((TargetMethodWrapping) declaredAnnotation).exportName()).getName());
                }
            }
        }

        for (BridgeBuildInfo bridge : bridges) {
            Reflect.getINSTANCE().getTransformManager().addTransformer(new BridgeGetDataTransformer(bridge, this::processData));
        }
    }
    private void processData(BridgeBuildInfo info){
        if (info.getBuildType() == BridgeBuildInfo.Type.DIRECT){
            Class<?> gui = generateNewClassAndRemap(info.getBytes());
            ReflectNative.logToNative(gui.getCanonicalName());
            info.setGeneratedClass(gui);
        }
    }
    public BridgeBuildInfo getBridge(Class<? extends BridgeBase> bridgeClass){
        for (BridgeBuildInfo bridge : bridges) {
            if (bridge.getBridge() == bridgeClass){
                return bridge;
            }
        }
        return null;
    }
    private Class<?> generateNewClassAndRemap(byte[] oldBytes){
        String newClassName = EncryptUtil.createRandomString(16);
        String newClassFullName = "com.reflectmc.gui."+newClassName;
        ClassReader cr = new ClassReader(oldBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        String oldName = cn.name;
        cn.name = newClassFullName.replace(".","/");
        if (cn.superName.equals(Type.getInternalName(BridgeBase.class))){
            cn.superName = Type.getInternalName(GuiScreen.GuiScreenClass);
        }
        if (cn.methods != null){
            for (MethodNode method : cn.methods) {
                methodMap.forEach((E,T) -> {
                    if (method.name.equals(E))
                        method.name = T;
                });
                if (method.instructions != null){
                    for (AbstractInsnNode instruction : method.instructions) {
                        if (instruction instanceof MethodInsnNode){
                            if (((MethodInsnNode) instruction).owner.equals(oldName))
                                ((MethodInsnNode) instruction).owner = newClassFullName.replace(".","/");
                            if (((MethodInsnNode) instruction).owner.equals(Type.getInternalName(BridgeBase.class))){
                                ((MethodInsnNode) instruction).owner = Type.getInternalName(GuiScreen.GuiScreenClass);
                                methodMap.forEach((E,T)->{
                                    if (((MethodInsnNode) instruction).name.equals(E)){
                                        ((MethodInsnNode) instruction).name = T;
                                    }
                                });
                            }
                        }
                        if (instruction instanceof FieldInsnNode){
                            if (((FieldInsnNode) instruction).owner.equals(oldName))
                                ((FieldInsnNode) instruction).owner = newClassFullName.replace(".","/");
                        }
                        if (instruction instanceof InvokeDynamicInsnNode){
                            List<Object> newArgs = new ArrayList<>();
                            for (Object bsmArg : ((InvokeDynamicInsnNode) instruction).bsmArgs) {
                                if (bsmArg instanceof Handle){
                                    if (((Handle) bsmArg).getOwner().equals(oldName)) {
                                        newArgs.add(new Handle(((Handle) bsmArg).getTag(), newClassFullName.replace(".", "/"),
                                                ((Handle) bsmArg).getName(), ((Handle) bsmArg).getDesc()));
                                    }else {
                                        newArgs.add(bsmArg);
                                    }
                                }else {
                                    newArgs.add(bsmArg);
                                }
                            }
                            ((InvokeDynamicInsnNode) instruction).bsmArgs = newArgs.toArray();
                            if (((InvokeDynamicInsnNode) instruction).bsm.getOwner().equals(oldName))
                                ((InvokeDynamicInsnNode) instruction).bsm = new Handle(((InvokeDynamicInsnNode) instruction).bsm.getTag(),
                                        newClassFullName.replace(".","/"),((InvokeDynamicInsnNode) instruction).bsm.getName(),
                                        ((InvokeDynamicInsnNode) instruction).bsm.getDesc());
                        }
                    }
                }
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();
        Class<?> clazz = ClassUtil.defineClass(bytes);
        return clazz;
    }
}
