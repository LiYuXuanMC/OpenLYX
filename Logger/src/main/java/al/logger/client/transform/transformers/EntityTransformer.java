package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.EventType;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventStep;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.Profiler;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class EntityTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Entity.EntityClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(Entity.moveEntity.getName())&&method.desc.equals("(DDD)V")){
                {
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
                    move.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventMove.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE)));
                    move.add(new VarInsnNode(ASTORE,moveVar.index));

                    move.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    move.add(new VarInsnNode(ALOAD,moveVar.index));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));


                    move.add(new VarInsnNode(ALOAD,moveVar.index));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    move.add(new JumpInsnNode(IFEQ,L0));
                    move.add(new InsnNode(RETURN));
                    move.add(L0);
                    move.add(new VarInsnNode(ALOAD,moveVar.index));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.targetObfuscatedMethod(EventMove.class,"getX").getName(),"()D"));
                    move.add(new VarInsnNode(DSTORE,1));
                    move.add(new VarInsnNode(ALOAD,moveVar.index));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.targetObfuscatedMethod(EventMove.class,"getY").getName(),"()D"));
                    move.add(new VarInsnNode(DSTORE,3));
                    move.add(new VarInsnNode(ALOAD,moveVar.index));
                    move.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMove.class),ObfuscateHelper.targetObfuscatedMethod(EventMove.class,"getZ").getName(),"()D"));
                    move.add(new VarInsnNode(DSTORE,5));
                    move.add(L1);
                    insnList.insert(move);
                    method.maxLocals++;
                    method.instructions = insnList;
                }
                {
                    LocalVariableNode step = new LocalVariableNode("step",Type.getDescriptor(EventStep.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                    method.localVariables.add(step);
                    InsnList hook = new InsnList();
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventStep.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Pre).getName(),Type.getDescriptor(EventType.class)));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.stepHeight.getName(),"F"));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventStep.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EventType.class),Type.FLOAT_TYPE)));
                    hook.add(new VarInsnNode(ASTORE,step.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,step.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new VarInsnNode(ALOAD,step.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventStep.class),ObfuscateHelper.targetObfuscatedMethod(EventStep.class,"getHeight").getName(),Type.getMethodDescriptor(Type.FLOAT_TYPE)));
                    hook.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.stepHeight.getName(),"F"));

                    int index = 0;
                    for (AbstractInsnNode abstractInsnNode : method.instructions) {
                        if (abstractInsnNode instanceof FieldInsnNode){
                            if (((FieldInsnNode) abstractInsnNode).name.equals(Entity.stepHeight.getName()) &&
                                    ((FieldInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(Entity.EntityClass))&&
                                    ((FieldInsnNode) abstractInsnNode).desc.equals("F")){
                                index ++;
                                if (index == 2){
                                    method.instructions.insertBefore(abstractInsnNode,hook);
                                }
                            }
                        }
                    }
                    hook = new InsnList();
                    LabelNode Lnew = new LabelNode();
                    hook.add(Lnew);
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventStep.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Post).getName(),Type.getDescriptor(EventType.class)));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.stepHeight.getName(),"F"));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventStep.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EventType.class),Type.FLOAT_TYPE)));
                    hook.add(new VarInsnNode(ASTORE,step.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,step.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    for (AbstractInsnNode abstractInsnNode : method.instructions) {
                        if (abstractInsnNode instanceof MethodInsnNode){
                            if (((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(Profiler.ProfilerClass)) &&
                                    ((MethodInsnNode) abstractInsnNode).name.equals(Profiler.endSection.getName()) &&
                                    ((MethodInsnNode) abstractInsnNode).desc.equals("()V")){
                                AbstractInsnNode node = abstractInsnNode.getPrevious();
                                while (!(node instanceof LabelNode)){
                                    node = node.getPrevious();
                                }
                                LabelNode L33 = (LabelNode) node;
                                hook.insert(new JumpInsnNode(GOTO,L33));
                                method.instructions.insertBefore(L33,hook);
                                while (!(node instanceof JumpInsnNode)){
                                    node = node.getPrevious();
                                }
                                node = node.getPrevious();
                                while (!(node instanceof JumpInsnNode)){
                                    node = node.getPrevious();
                                }
                                JumpInsnNode jumpL33 = (JumpInsnNode) node;
                                jumpL33.label = Lnew;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
