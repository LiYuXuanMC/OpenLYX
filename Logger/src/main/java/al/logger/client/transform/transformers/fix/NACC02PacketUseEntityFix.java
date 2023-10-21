package al.logger.client.transform.transformers.fix;

import al.logger.client.transform.ClassTransformer;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.AbstractInsnNode;
import al.logger.libs.asm.tree.ClassNode;
import al.logger.libs.asm.tree.FieldInsnNode;
import al.logger.libs.asm.tree.MethodNode;

public class NACC02PacketUseEntityFix extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return C02PacketUseEntity.C02PacketUseEntityClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if (method.name.equals("<init>") && method.desc.equals(Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Entity.EntityClass),Type.getType(C02Action.ActionClass)))){
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction instanceof FieldInsnNode){
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) instruction;
                        if (fieldInsnNode.owner.equals(Type.getInternalName(C02Action.ActionClass))){
                            fieldInsnNode.name = fieldInsnNode.name.replace(C02Action.INTERACT.name(),C02Action.ATTACK.name());
                        }
                    }
                }
            }
        }
    }
}
