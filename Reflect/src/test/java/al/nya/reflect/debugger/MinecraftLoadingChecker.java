package al.nya.reflect.debugger;

import al.nya.reflect.Reflect;
import al.nya.reflect.debugger.attach.SelfAttachAgent;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.transform.ReflectNative;
import al.nya.reflect.transform.TransformManager;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.wrapper.bridge.BridgeUtil;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MinecraftLoadingChecker implements Runnable {
    private static byte[] result;
    @Override
    public void run() {
        boolean loaded = false;
        while (!loaded) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            Class Minecraft = ClassUtil.getClass("net.minecraft.client.Minecraft");
            if (Minecraft == null) continue;
            Field theMinecraft;
            try {
                theMinecraft = Minecraft.getDeclaredField("theMinecraft");
            } catch (NoSuchFieldException e) {
                theMinecraft = null;
            }
            if (theMinecraft == null) continue;
            if (!theMinecraft.isAccessible()) theMinecraft.setAccessible(true);
            Object mc;
            try {
                mc = theMinecraft.get(null);
            } catch (IllegalAccessException e) {
                mc = null;
            }
            if (mc == null) continue;
            Field currentScreen;
            try {
                currentScreen = Minecraft.getDeclaredField("currentScreen");
            } catch (NoSuchFieldException e) {
                currentScreen = null;
            }
            if (currentScreen == null) continue;
            if (!currentScreen.isAccessible()) currentScreen.setAccessible(true);
            Object screen;
            try {
                screen = currentScreen.get(mc);
            } catch (IllegalAccessException e) {
                screen = null;
            }
            if (screen != null) loaded = true;
        }
        System.out.println("Minecraft is fully loaded");
        System.out.println("Attaching to Minecraft...");
        SelfAttachAgent.loadAgent();
        System.out.println("Attached to Minecraft");
        new Reflect();
        //loadAsm();
        //System.load(new File(Loader.CLIENT_ROOT).getAbsolutePath() + "/natives/Reflect-Native.dll");
    }

    public static void transformClasses() {
        DebugTransformer transformer = new DebugTransformer();
        Instrumentation instrumentation = SelfAttachAgent.getInstrumentation();
        for (ClassTransformer classTransformer : TransformManager.transformers) {
            byte[] bytes = getClassBytes(classTransformer.getTargetClass());
            bytes = TransformManager.onTransform(classTransformer.getTargetClass(), bytes);
            ClassDefinition classDefinition = new ClassDefinition(classTransformer.getTargetClass(), bytes);
            try {
                instrumentation.redefineClasses(classDefinition);
            } catch (ClassNotFoundException | UnmodifiableClassException e) {
                e.printStackTrace();
            }
        }
        Reflect.loading.setLoadingType("Load fonts");
        FontManager.shouldLoad = true;
        //ReflectLoading.loadingProgress.setString("Building bridge");
        Reflect.loading.setLoadingType("BuildBridge");
        BridgeUtil.init();
        FontManager.init();
    }

    public static void loadAsm() {
        List<Class<?>> classes = Arrays.asList(SelfAttachAgent.getInstrumentation().getAllLoadedClasses());
        List<Class<?>> asmLibClasses = new ArrayList<>();
        for (Class<?> aClass : classes) {
            if (aClass.getCanonicalName().startsWith("al.nya.reflect.libraries.reflectasm")) {
                if (!Modifier.isInterface(aClass.getModifiers()) && !Modifier.isAbstract(aClass.getModifiers())) {
                    asmLibClasses.add(aClass);
                }
            }
        }
        for (Class<?> asmLibClass : asmLibClasses) {
            try {
                asmLibClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized byte[] getClassBytes(Class<?> c) {
        Instrumentation inst = SelfAttachAgent.getInstrumentation();
        AtomicBoolean ok = new AtomicBoolean(false);
        ClassFileTransformer tmp =
                (loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
                    if (classBeingRedefined == c) {
                        ok.set(true);
                        result = classfileBuffer;
                    }
                    return classfileBuffer;
                };
        inst.addTransformer(tmp, true);
        try {
            inst.retransformClasses(c);
            while (!ok.get()) {
                Thread.sleep(100L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        inst.removeTransformer(tmp);
        return result;
    }
}
