package al.nya.reflect.transform;

import al.nya.reflect.Reflect;
import al.nya.reflect.transform.transformers.*;
import al.nya.reflect.utils.client.*;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.wrapper.bridge.BridgeUtil;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class TransformManager {
    public static List<ClassTransformer> transformers = new ArrayList<ClassTransformer>();
    public static int index = 0;
    public static Thread mainThread = null;
    public static void init(){
//        transformers.add(new ActiveRenderInfoTransformer());
        transformers.add(new EntityPlayerTransformer());
        transformers.add(new GuiScreenTransformer());
        transformers.add(new RenderLivingEntityTransformer());
        transformers.add(new EntityLivingBaseTransformer());
        if (DoMCerDetector.isDomcer()) {
            transformers.add(new DoMCerTransformer());
        }
        transformers.add(new EntityPlayerSPTransformer());
        transformers.add(new MinecraftTransformer());
        if (ForgeDetector.isModedGuiIngame())
            transformers.add(new GuiIngameForgeTransformer());
        transformers.add(new GuiIngameTransformer());
        transformers.add(new EntityRendererTransformer());
        transformers.add(new PlayerControllerMPTransformer());
        transformers.add(new NetworkManagerTransformer());
        transformers.add(new WorldRendererTransformer());
        transformers.add(new BlockTransformer());
        transformers.add(new GuiChatTransformer());
        transformers.add(new EntityTransformer());
        transformers.add(new FontRendererTransformer());
        transformers.add(new BlockGrassTransformer());
        if (MargeleAntiCheatDetector.getHyGui() != null) {
            transformers.add(new HyCraftGuiTransformer());
        }
        if (MargeleAntiCheatDetector.isHyCraftScreenshot()) {
            transformers.add(new HyScoreBoardTransformer());
        }
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            System.out.println(thread.getName());
            if (thread.getName().equals("Client thread")) {
                mainThread = thread;
                break;
            }
        }
    }

    public static void transform() {
        if (DebugUtils.isDebugging()) {
            DebugUtils.transformClasses();
            return;
        }
        Reflect.loading.progress(index, transformers.size());
        Reflect.loading.setLoadingType("Event");
        ReflectNative.setState(1, true);
        index++;
        freezeMain();
        for (ClassTransformer transformer : transformers) {
            Reflect.loading.progress(index, transformers.size());
            int ret = ReflectNative.retransform(transformer.getTargetClass(), transformer);
            ClientUtil.printChat("Event Class " + transformer.getTargetClass().getSimpleName() + " " + JVMTIError.parse(ret));

            index++;
        }
        resumeMain();
        Reflect.loading.setLoadingType("Load fonts");
        ReflectNative.setState(1, false);
        FontManager.shouldLoad = true;
        //ReflectLoading.loadingProgress.setString("Building bridge");
        Reflect.loading.setLoadingType("BuildBridge");
        BridgeUtil.init();
        FontManager.init();
    }

    private static void freezeMain() {
        if (mainThread != null) mainThread.suspend();
    }

    private static void resumeMain() {
        if (mainThread != null) mainThread.resume();
    }

    public static byte[] onTransform(Class<?> clz, byte[] bytes_transform) {
        //ReflectLoading.loadingProgress.setString(clz.getCanonicalName());
        try {
            if (clz == null) {
                System.out.println("Null Class");
                return bytes_transform;
            }
            if (bytes_transform == null) {
                System.out.println("Null class byte");
                return bytes_transform;
            }
            if (Reflect.debug) {
                FileOutputStream fileOutputStream = new FileOutputStream(Reflect.ReflectDir + "/" + clz.getCanonicalName() + "_NonTransform.class");
                fileOutputStream.write(bytes_transform);
                fileOutputStream.close();
            }
            byte[] bytes = null;
            System.out.println("Start transform " + clz.getCanonicalName());
            System.out.println("AA");
            System.out.println("null: " + (bytes_transform == null));
            for (ClassTransformer transformer : transformers) {
                System.out.println("AAA");
                if (transformer.getTargetClass() == clz) {
                    System.out.println("AAAB");
                    bytes = transformer.transform(bytes_transform);
                }
            }
            System.out.println("a");
            if (bytes == null) {
                System.out.println("transformer return null");
                bytes = bytes_transform;
                return bytes;
            }
            System.out.println("b");
            if (Reflect.debug) {
                FileOutputStream fileOutputStream = new FileOutputStream(Reflect.ReflectDir + "/" + clz.getCanonicalName() + ".class");
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            }
            System.out.println("c");
            return bytes;
        }catch (Exception e){
            e.printStackTrace();
            return bytes_transform;
        }
    }
}
