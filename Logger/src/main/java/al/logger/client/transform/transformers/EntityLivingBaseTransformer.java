package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class EntityLivingBaseTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityLivingBase.EntityLivingBaseClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(EntityLivingBase.jump.getName()) && method.desc.equals("()V")){
                LocalVariableNode jump = new LocalVariableNode("jump", Type.getDescriptor(EventJump.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                InsnList insnList = method.instructions;
                LabelNode L1 = new LabelNode();
                InsnList hook = new InsnList();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventJump.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventJump.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE)));
                hook.add(new VarInsnNode(ASTORE,jump.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,jump.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,jump.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventJump.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                insnList.insert(hook);
                method.instructions = insnList;
            }
        }
    }
}
