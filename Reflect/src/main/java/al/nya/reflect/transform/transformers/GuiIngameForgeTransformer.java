package al.nya.reflect.transform.transformers;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.ForgeDetector;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiIngame;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import by.radioegor146.nativeobfuscator.Native;

public class GuiIngameForgeTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return ForgeDetector.getGuiIngameForge();
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(GuiIngame.renderTooltip.getName()) &&
                    method.desc.equals("(L"+Type.getInternalName(ScaledResolution.ScaledResolution)+";F)V")){
                InsnList render2DInsn = new InsnList();
                render2DInsn.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                render2DInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                render2DInsn.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventRender2D.class)));
                render2DInsn.add(new InsnNode(Opcodes.DUP));
                render2DInsn.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventRender2D.class),"<init>","()V"));
                render2DInsn.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                InsnList returnList = new InsnList();
                returnList.add(new InsnNode(Opcodes.RETURN));
                method.instructions.insert(render2DInsn);
                break;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
