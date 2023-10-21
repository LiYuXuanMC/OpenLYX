package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.*;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.block.BlockGrass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumWorldBlockLayer;
import by.radioegor146.nativeobfuscator.Native;

import java.awt.image.BufferedImage;

public class DoMCerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        try {
            return Class.forName("customskinloader.Logger");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("sb");
            return null;
        }
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("initLogger")) {
                InsnList hookPreScreenshot = new InsnList();
                hookPreScreenshot.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "onScreenShotAntiCheatPre", "()V"));
                method.instructions.insert(hookPreScreenshot);
            }
            if (method.name.equals("lambda$null$0")) {
                InsnList hookPostScreenshot = new InsnList();
                hookPostScreenshot.add(new VarInsnNode(ALOAD, 0));
                hookPostScreenshot.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "onScreenShotAntiCheatPost"
                        , Type.getMethodDescriptor(Type.getType(BufferedImage.class), Type.getType(BufferedImage.class))));
                hookPostScreenshot.add(new VarInsnNode(ASTORE, 0));
                method.instructions.insert(hookPostScreenshot);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
