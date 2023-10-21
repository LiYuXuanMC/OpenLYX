package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.ClassNode;
import al.nya.reflect.libraries.reflectasm.tree.InsnList;
import al.nya.reflect.libraries.reflectasm.tree.MethodInsnNode;
import al.nya.reflect.libraries.reflectasm.tree.MethodNode;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import by.radioegor146.nativeobfuscator.Native;

public class EntityLivingBaseTransformer extends ClassTransformer {
    @Override
    public Class getTargetClass() {
        return EntityLivingBase.EntityLivingBaseClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityLivingBase.jump.getName()) && method.desc.equals("()V")){
                InsnList insnList = method.instructions;
                InsnList jump = new InsnList();
                jump.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"jump","()V"));
                insnList.insert(jump);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
