package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.NeteaseDetector;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderHelper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;
import by.radioegor146.nativeobfuscator.Native;

public class GuiScreenTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiScreen.GuiScreenClass;
    }

    @Native
    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(GuiScreen.drawHoveringText.getName()) &&
                    method.desc.equals("(Ljava/util/List;II)V")) {
                InsnList insnList = method.instructions;
                InsnList drawHoveringText = new InsnList();
                drawHoveringText.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(GlStateManager.GlStateManagerClass), GlStateManager.enableLighting.getName(), "()V", false));
                drawHoveringText.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(GlStateManager.GlStateManagerClass), GlStateManager.enableDepth.getName(), "()V", false));
                drawHoveringText.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(RenderHelper.RenderHelperClass), RenderHelper.enableStandardItemLighting.getName(), "()V", false));
                drawHoveringText.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(GlStateManager.GlStateManagerClass), GlStateManager.enableRescaleNormal.getName(), "()V", false));
                insnList.insert(drawHoveringText);
                method.instructions = insnList;
            }
            if (method.name.equals(GuiScreen.handleComponentHover.getName()) &&
                    method.desc.equals("(L" + Type.getInternalName(IChatComponent.IChatComponentClass) + ";II)V")) {
                InsnList insnList = method.instructions;
                InsnList handleComponentHover = new InsnList();
                handleComponentHover.add(new VarInsnNode(Opcodes.ALOAD, 0));
                handleComponentHover.add(new VarInsnNode(Opcodes.ALOAD, 1));
                handleComponentHover.add(new VarInsnNode(Opcodes.ILOAD, 2));
                handleComponentHover.add(new VarInsnNode(Opcodes.ILOAD, 3));
                handleComponentHover.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "handleComponentHover", "(Ljava/lang/Object;Ljava/lang/Object;II)V"));

                insnList.insert(handleComponentHover);
                method.instructions = insnList;
            }
            if (Wrapper.env.equals(Maps.Srg1_8_9) && method.name.equals(GuiScreen.handleKeyboardInput.getName()) && method.desc.equals("()V")) {
                //mc version > 1.8.9 was fixed multi-language support
                InsnList inputFix = new InsnList();
                LabelNode L1 = new LabelNode();
                inputFix.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "isInputFix", "()Z"));
                inputFix.add(new JumpInsnNode(Opcodes.IFEQ, L1));
                inputFix.add(new VarInsnNode(Opcodes.ALOAD, 0));
                inputFix.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "inputFix", "(L" + Type.getInternalName(Object.class) + ";)V"));
                inputFix.add(new InsnNode(Opcodes.RETURN));
                inputFix.add(L1);
                inputFix.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                method.instructions.insert(inputFix);
            }
            if (NeteaseDetector.isFilter() && method.name.equals(GuiScreen.sendChatMessage_S_V.getName())) {
                InsnList antiFilter = new InsnList();
                antiFilter.add(new VarInsnNode(Opcodes.ALOAD, 0));
                antiFilter.add(new VarInsnNode(Opcodes.ALOAD, 1));
                antiFilter.add(new InsnNode(Opcodes.ICONST_1));
                antiFilter.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(GuiScreen.GuiScreenClass), GuiScreen.sendChatMessage_SZ_V.getName(), "(Ljava/lang/String;Z)V"));
                antiFilter.add(new InsnNode(Opcodes.RETURN));
                method.instructions = (antiFilter);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        System.out.println("13");
        classNode.accept(cw);
        System.out.println("14");
        return cw.toByteArray();
    }
}
