package al.nya.reflect.transform.transformers;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.World.disablers.MargelesAntiCheatDisabler;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import by.radioegor146.nativeobfuscator.Native;

public class EntityPlayerSPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayerSP.EntityPlayerSPClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(EntityPlayerSP.onLivingUpdate.getName()) && method.desc.equals("()V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList eventUpdatePost = new InsnList();
                eventUpdatePost.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                eventUpdatePost.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                eventUpdatePost.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventUpdate.class)));
                eventUpdatePost.add(new InsnNode(Opcodes.DUP));
                eventUpdatePost.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventUpdate.class),"<init>","()V"));
                eventUpdatePost.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                insnList.insert(eventUpdatePost);
                method.instructions = insnList;
            }
            if (method.name.equals(EntityPlayerSP.onLivingUpdate.getName()) && method.desc.equals("()V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList noslow = new InsnList();
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    //INVOKEVIRTUAL net/minecraft/client/entity/EntityPlayerSP.func_71039_bw ()Z
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (abstractInsnNode.getOpcode() == Opcodes.INVOKEVIRTUAL &&
                                ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass)) &&
                                ((MethodInsnNode) abstractInsnNode).name.equals(Entity.isRiding.getName())&&
                                ((MethodInsnNode) abstractInsnNode).desc.equals("()Z")){
                            AbstractInsnNode L12 = abstractInsnNode.getNext();
                            if (L12 instanceof JumpInsnNode){
                                noslow.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"isNoSlow","()Z"));
                                noslow.add(new JumpInsnNode(Opcodes.IFNE, ((JumpInsnNode) L12).label));
                                System.out.println("Insert");
                                insnList.insert(L12.getNext(),noslow);
                            }
                            break;
                        }
                    }
                }
                method.instructions = insnList;
            }
            if (method.name.equals(EntityPlayerSP.onUpdate.getName()) && method.desc.equals("()V")) {
                InsnList insnList = method.instructions;
                InsnList preUpdate = new InsnList();
                //INVOKESTATIC al/nya/reflect/events/EventBus.preUpdate ()V
                LabelNode L0 = new LabelNode();
                preUpdate.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "preUpdate", "()Z"));
                preUpdate.add(new JumpInsnNode(IFEQ,L0));
                preUpdate.add(new InsnNode(RETURN));
                preUpdate.add(L0);
                InsnList postUpdate = new InsnList();
                postUpdate.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "postUpdate", "()V"));
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (abstractInsnNode.getOpcode() == Opcodes.INVOKEVIRTUAL
                                && ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass))
                                && ((MethodInsnNode) abstractInsnNode).name.equals(EntityPlayerSP.onUpdateWalkingPlayer.getName())
                                && ((MethodInsnNode) abstractInsnNode).desc.equals("()V")){
                            insnList.insertBefore(abstractInsnNode,preUpdate);
                            insnList.insert(abstractInsnNode,postUpdate);
                        }
                    }
                }
                method.instructions = insnList;
            }
            if (method.name.equals(EntityPlayerSP.sendChatMessage.getName()) && method.desc.equals("(Ljava/lang/String;)V")){
                InsnList insnListOld = method.instructions;
                InsnList insnList = new InsnList();
                InsnList chat = new InsnList();
                //ALOAD 1
                //INVOKESTATIC al/nya/reflect/modules/Command.message (Ljava/lang/String;)Z
                //IFEQ L0
                //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
                //LDC ""
                //INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
                //        L0
                //FRAME SAME
                //RETURN
                LabelNode L0 = new LabelNode();
                chat.add(new VarInsnNode(Opcodes.ALOAD,1));
                chat.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"onMessage","(Ljava/lang/String;)Z"));
                chat.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                insnList.add(chat);
                insnList.add(insnListOld);
                insnList.add(L0);
                insnList.add(new InsnNode(Opcodes.RETURN));
                method.instructions = insnList;
            }
            if (method.name.contains("handler$eventUpdate") && MargeleAntiCheatDetector.isMAC()) {
                InsnList hookInsn = new InsnList();
                LabelNode L0 = new LabelNode();
                hookInsn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "macUpdate", "()Z"));
                hookInsn.add(new JumpInsnNode(IFEQ, L0));
                hookInsn.add(new InsnNode(RETURN));
                hookInsn.add(L0);
                method.instructions.insert(hookInsn);
            }
            if (method.name.equals(EntityPlayerSP.pushOutOfBlocks.getName()) && method.desc.equals("(DDD)Z")) {
                InsnList pushOutOfBlockEvent = new InsnList();
                LabelNode L0 = new LabelNode();
                LabelNode L1 = new LabelNode();
                pushOutOfBlockEvent.add(new VarInsnNode(ALOAD, 0));
                pushOutOfBlockEvent.add(new FieldInsnNode(GETFIELD, Type.getInternalName(Entity.EntityClass), Entity.noClip.getName(), "Z"));
                pushOutOfBlockEvent.add(new JumpInsnNode(IFEQ, L0));
                pushOutOfBlockEvent.add(new InsnNode(ICONST_0));
                pushOutOfBlockEvent.add(new InsnNode(IRETURN));
                pushOutOfBlockEvent.add(L0);
                pushOutOfBlockEvent.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class), "pushOutOfBlocks", "()Z"));
                pushOutOfBlockEvent.add(new JumpInsnNode(IFEQ, L1));
                pushOutOfBlockEvent.add(new InsnNode(ICONST_0));
                pushOutOfBlockEvent.add(new InsnNode(IRETURN));
                pushOutOfBlockEvent.add(L1);
                method.instructions.insert(pushOutOfBlockEvent);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
