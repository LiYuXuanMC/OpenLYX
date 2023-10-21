package com.reflectmc.reflect.transform;

import com.reflectmc.builder.annotation.ExportName;
import com.reflectmc.loader.agent.ReflectNative;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.transform.transformers.*;
import com.reflectmc.reflect.wrapper.Wrapper;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
@ExportName(export = "OBF/TransformManager")
public class TransformManager {
    @Getter
    private final List<ClassTransformer> transformers = new ArrayList<>();
    public TransformManager(){
    }
    public void loadTransformers(){
        if (Wrapper.getMapper().isForge())
            transformers.add(new GuiIngameForgeTransformer());
        transformers.add(new GuiIngameTransformer());
        transformers.add(new MinecraftTransformer());
        transformers.add(new EntityPlayerSPTransformer());
        transformers.add(new EntityRendererTransformer());
        transformers.add(new NetworkManagerTransformer());
        transformers.add(new PlayerControllerMPTransformer());
        transformers.add(new RendererLivingEntityTransformer());
        transformers.add(new EntityTransformer());
        transformers.add(new EntityPlayerTransformer());
    }
    public void addTransformer(ClassTransformer transformer){
        transformers.add(transformer);
    }
    @SneakyThrows
    public void transform(){
        List<Class<?>> requiredClasses = new ArrayList<>();
        for (ClassTransformer transformer : transformers) {
            if (!requiredClasses.contains(transformer.getTargetClass())) requiredClasses.add(transformer.getTargetClass());
        }
        Thread main = null;
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread.getName().equals("Client thread")){
                main = thread;
            }
        }
        for (Class<?> requiredClass : requiredClasses) {
            int result = ReflectNative.transform(requiredClass);
            Thread.sleep(50);
            System.out.println(result);
        }
        transformers.clear();//Unload all transformers
    }
    @ExportName(export = "OBF/TransformManager/onTransform")
    public static byte[] onTransform(Class<?> clazz,byte[] classByte){
        System.out.println("..."+clazz.getCanonicalName());
        byte[] bytes = classByte;
        for (ClassTransformer transformer : Reflect.getINSTANCE().getTransformManager().getTransformers()) {
            if (transformer.getTargetClass() == clazz){
                try {
                    bytes = transformer.transform(bytes);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Exception in transformer "+transformer.getClass().getCanonicalName());
                }
            }
        }
        return bytes;
    }
}
