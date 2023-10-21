package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.EventCancelable;
import com.reflectmc.reflect.event.events.player.EventMove;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class EntityTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return Entity.EntityClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(Entity.moveEntity.getName())&&method.desc.equals("(DDD)V")){
                LocalVariableNode moveVar = new LocalVariableNode("move",Type.getDescriptor(EventMove.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(moveVar);
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList move = new InsnList();
                //ALOAD 0
                //INVOKESTATIC al/nya/reflect/events/EventBus.move (Ljava/lang/Object;)V
                LabelNode L0 = new LabelNode();
                LabelNode L1 = new LabelNode();
                move.add(new VarInsnNode(ALOAD,0));
                move.add(new TypeInsnNode(INSTANCEOF,Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass)));
                move.add(new JumpInsnNode(IFEQ,L1));

                move.add(new TypeInsnNode(NEW,Type.getInternalName(EventMove.class)));
                move.add(new InsnNode(DUP));
                move.add(new VarInsnNode(DLOAD,1));
                move.add(new VarInsnNode(DLOAD,3));
                move.add(new VarInsnNode(DLOAD,5));
                move.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventMove.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE)));
                move.add(new VarInsnNode(ASTORE,moveVar.index));

                move.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                move.add(new VarInsnNode(ALOAD,moveVar.index));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));


                move.add(new VarInsnNode(ALOAD,moveVar.index));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.findObfMethod(EventCancelable.class,"isCancel").getName(),"()Z"));
                move.add(new JumpInsnNode(IFEQ,L0));
                move.add(new InsnNode(RETURN));
                move.add(L0);
                move.add(new VarInsnNode(ALOAD,moveVar.index));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.findObfMethod(EventMove.class,"Move$getX").getName(),"()D"));
                move.add(new VarInsnNode(DSTORE,1));
                move.add(new VarInsnNode(ALOAD,moveVar.index));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.findObfMethod(EventMove.class,"Move$getY").getName(),"()D"));
                move.add(new VarInsnNode(DSTORE,3));
                move.add(new VarInsnNode(ALOAD,moveVar.index));
                move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.findObfMethod(EventMove.class,"Move$getZ").getName(),"()D"));
                move.add(new VarInsnNode(DSTORE,5));
                move.add(L1);
                insnList.insert(move);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
