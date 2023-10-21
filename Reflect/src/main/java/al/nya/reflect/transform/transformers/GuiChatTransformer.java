package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import by.radioegor146.nativeobfuscator.Native;

public class GuiChatTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiChat.GuiChatClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(GuiChat.drawScreen.getName()) && method.desc.equals("(IIF)V")) {
                InsnList insnList = method.instructions;
                InsnList drawScreen = new InsnList();
                drawScreen.add(new VarInsnNode(Opcodes.ILOAD, 1));
                drawScreen.add(new VarInsnNode(Opcodes.ILOAD, 2));
                drawScreen.add(new VarInsnNode(Opcodes.FLOAD, 3));
                drawScreen.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "onGuiChatDrawScreen", "(IIF)V"));
                insnList.insert(drawScreen);
                method.instructions = insnList;
            }
            if (method.name.equals(GuiChat.mouseClicked.getName()) && method.desc.equals("(III)V")) {
                InsnList insnList = method.instructions;
                InsnList mouseClicked = new InsnList();
                mouseClicked.add(new VarInsnNode(Opcodes.ILOAD, 1));
                mouseClicked.add(new VarInsnNode(Opcodes.ILOAD, 2));
                mouseClicked.add(new VarInsnNode(Opcodes.ILOAD, 3));
                mouseClicked.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "onGuiChatMouseClicked", "(III)V"));
                insnList.insert(mouseClicked);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
