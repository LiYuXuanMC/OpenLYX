package al.nya.reflect.transform.transformers;

import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.ClassNode;
import al.nya.reflect.libraries.reflectasm.tree.InsnList;
import al.nya.reflect.libraries.reflectasm.tree.MethodInsnNode;
import al.nya.reflect.libraries.reflectasm.tree.MethodNode;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.render.ActiveRenderInfo;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import by.radioegor146.nativeobfuscator.Native;

public class ActiveRenderInfoTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return ActiveRenderInfo.ActiveRenderInfoClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(ActiveRenderInfo.updateRenderInfo.getName()) &&
                    method.desc.equals("(" + Type.getInternalName(EntityPlayer.EntityClass) + ";Z)V")) {
                final InsnList insnList = new InsnList();
                //call our hook function
                insnList.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(RenderUtil.class), "updateModelViewProjectionMatrix", "()V", false));
                //insert the list of instructions at the bottom of the function
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
