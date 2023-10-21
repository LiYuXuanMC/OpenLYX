package al.nya.reflect.transform.transformers;

import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.tree.ClassNode;
import al.nya.reflect.libraries.reflectasm.tree.MethodNode;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiNewChat;
import by.radioegor146.nativeobfuscator.Native;

public class GuiNewChatTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiNewChat.GuiNewChatClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {

        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
