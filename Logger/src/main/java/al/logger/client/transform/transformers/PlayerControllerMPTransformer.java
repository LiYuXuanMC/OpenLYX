package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.player.EventAttack;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.multiplayer.PlayerControllerMP;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class PlayerControllerMPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return PlayerControllerMP.PlayerControllerMPClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(PlayerControllerMP.attackEntity.getName()) && method.desc.equals(Type.getMethodDescriptor(PlayerControllerMP.attackEntity))){
                InsnList insnList = method.instructions;
                InsnList attack = new InsnList();
                //    ALOAD 1
                //    ALOAD 2
                //    INVOKESTATIC al/nya/reflect/events/EventBus.attack (Lal/nya/reflect/wrapper/wraps/wrapper/entity/EntityPlayer;Lal/nya/reflect/wrapper/wraps/wrapper/entity/Entity;)V

                LabelNode L1 = new LabelNode();
                LabelNode L2 = new LabelNode();
                attack.add(new VarInsnNode(ALOAD,1));
                attack.add(new TypeInsnNode(INSTANCEOF, Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass)));
                attack.add(new JumpInsnNode(IFEQ,L1));

                attack.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                attack.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(EventAttack.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(EntityPlayer.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new VarInsnNode(ALOAD,1));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityPlayer.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(Entity.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new VarInsnNode(ALOAD,2));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Entity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventAttack.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityPlayer.class),Type.getType(Entity.class))));
                attack.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                attack.add(L1);
                insnList.insert(attack);
                method.instructions = insnList;
            }
        }
        //dump(cn ,0);
    }
}
