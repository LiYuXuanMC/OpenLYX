package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import by.radioegor146.nativeobfuscator.Native;

import java.awt.image.BufferedImage;

public class HyScoreBoardTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return MargeleAntiCheatDetector.getHyCraftScoreBoard();
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals("onMsg$lambda-0")) {
                InsnList hookHead = new InsnList();
                hookHead.add(new VarInsnNode(ALOAD, 0));
                hookHead.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "asyncHandleHyCraftScreenshot", "(Ljava/lang/String;)V"));
                hookHead.add(new InsnNode(RETURN));
                method.instructions = (hookHead);
            }
            if (method.name.equals("cacheImage$lambda-2")) {
                InsnList hookEnd = new InsnList();
                hookEnd.add(new VarInsnNode(ALOAD, 0));
                hookEnd.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "asyncEndHyCraftScreenshot", Type.getMethodDescriptor(Type.getType(BufferedImage.class), Type.getType(BufferedImage.class))));
                hookEnd.add(new VarInsnNode(ASTORE, 0));
                method.instructions.insert(hookEnd);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
