package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import by.radioegor146.nativeobfuscator.Native;

// KeepSprint
// Skid From Vapu Transform
// TODO: can toggle
public class EntityPlayerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayer.EntityPlayerClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode, 0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityPlayer.attackTargetEntityWithCurrentItem.getName()) &&
                    method.desc.equals("(L" + Type.getInternalName(Entity.EntityClass) + ";)V")) {
                System.out.println("Hook attackTargetEntityWithCurrentItem");
                LabelNode L1 = new LabelNode();
                InsnList ks = new InsnList();
                ks.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(EventBus.class),"canKeepSprint", "()Z", false));
                ks.add(new JumpInsnNode(IFEQ, L1));
                AbstractInsnNode start = null;
                AbstractInsnNode end = null;

                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof MethodInsnNode) {
                        String name2 = ((MethodInsnNode) node).name;
                        String desc = ((MethodInsnNode) node).desc;
                        if (name2.equals(Entity.addVelocity.getName()) && desc.equals("(DDD)V")){
                            start = node;
                        }
                        if ((name2.equals(EntityPlayerSP.setSprinting.getName())) && desc.equals("(Z)V")) {
                            end = node;
                        }
                    }
                }
                if (start != null && end != null){
                    method.instructions.insert(start,ks);
                    method.instructions.insert(end,L1);
                }
            }
        }
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
