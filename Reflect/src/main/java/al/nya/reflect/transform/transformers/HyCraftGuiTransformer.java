package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.*;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import by.radioegor146.nativeobfuscator.Native;

public class HyCraftGuiTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return MargeleAntiCheatDetector.getHyGui();
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals(GuiScreen.initGui.getName())){
                InsnList initGui = new InsnList();
                initGui.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(EventBus.class),"guiScreen","L"+ Type.getInternalName(GuiScreenImpl.class)+";"));
                initGui.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"initGui","()V"));
                initGui.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(initGui);
            }
            if (method.name.equals(GuiScreen.drawScreen.getName())){
                InsnList drawScreen = new InsnList();
                drawScreen.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(EventBus.class),"guiScreen","L"+ Type.getInternalName(GuiScreenImpl.class)+";"));
                drawScreen.add(new VarInsnNode(Opcodes.ILOAD,1));
                drawScreen.add(new VarInsnNode(Opcodes.ILOAD,2));
                drawScreen.add(new VarInsnNode(Opcodes.FLOAD,3));
                drawScreen.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"drawScreen","(IIF)V"));
                drawScreen.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(drawScreen);
            }
            if (method.name.equals(GuiScreen.updateScreen.getName())){
                InsnList updateScreen = new InsnList();
                updateScreen.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(updateScreen);
            }
            if (method.name.equals(GuiScreen.mouseClicked.getName())){
                InsnList mouseClicked = new InsnList();
                mouseClicked.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(mouseClicked);
            }
            if (method.name.equals(GuiScreen.keyTyped.getName())){
                InsnList keyTyped = new InsnList();
                LabelNode L0 = new LabelNode();
                keyTyped.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(EventBus.class),"guiScreen","L"+ Type.getInternalName(GuiScreenImpl.class)+";"));
                keyTyped.add(new VarInsnNode(Opcodes.ILOAD,1));
                keyTyped.add(new VarInsnNode(Opcodes.ILOAD,2));
                keyTyped.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"keyTyped","(CI)Z"));
                keyTyped.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                keyTyped.add(new VarInsnNode(Opcodes.ALOAD,0));
                keyTyped.add(new VarInsnNode(Opcodes.ILOAD,1));
                keyTyped.add(new VarInsnNode(Opcodes.ILOAD,2));
                keyTyped.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,classNode.superName,GuiScreen.keyTyped.getName(),"(CI)V"));
                keyTyped.add(L0);
                keyTyped.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(keyTyped);
            }
            if (method.name.equals(GuiScreen.onGuiClosed.getName())){
                InsnList onGuiClosed = new InsnList();
                onGuiClosed.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(EventBus.class),"guiScreen","L"+ Type.getInternalName(GuiScreenImpl.class)+";"));
                onGuiClosed.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenImpl.class),"onGuiClosed","()V"));
                onGuiClosed.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(onGuiClosed);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        try{
            classNode.accept(cw);
            cw.toByteArray();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
        return cw.toByteArray();
    }
}
