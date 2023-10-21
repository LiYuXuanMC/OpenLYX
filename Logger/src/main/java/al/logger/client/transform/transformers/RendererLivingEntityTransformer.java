package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.render.EventPostRenderLiving;
import al.logger.client.event.client.render.EventPreRenderLiving;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.render.entity.RendererLivingEntity;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

import java.util.ArrayList;
import java.util.List;

@Native
public class RendererLivingEntityTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return RendererLivingEntity.RendererLivingEntityClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(RendererLivingEntity.doRender.getName()) && method.desc.equals(Type.getMethodDescriptor(RendererLivingEntity.doRender))){
                {
                    /*//RotationAnimation
                    int f = 0;
                    int f1 = 0;
                    int f2 = 0;
                    int f7 = 0;
                    AbstractInsnNode interpolateRotation = findMethodCall(RendererLivingEntity.interpolateRotation,method.instructions);
                    if (interpolateRotation != null){
                        VarInsnNode fOP = (VarInsnNode) findVarOperate(interpolateRotation);
                        f = fOP.var;
                        interpolateRotation = findMethodCall(RendererLivingEntity.interpolateRotation,interpolateRotation);
                        VarInsnNode f1OP = (VarInsnNode) findVarOperate(interpolateRotation);
                        f1 = f1OP.var;
                        AbstractInsnNode FSUB = findFSUB(interpolateRotation);
                        VarInsnNode f2OP = (VarInsnNode) findVarOperate(FSUB);
                        f2 = f2OP.var;
                        AbstractInsnNode renderAt = findMethodCall(RendererLivingEntity.renderLivingAt,interpolateRotation);
                        AbstractInsnNode FADD = backFindFADD(renderAt);
                        VarInsnNode f7OP = (VarInsnNode) findVarOperate(FADD);
                        f7 = f7OP.var;

                        InsnList hook = new InsnList();
                        LocalVariableNode rot = new LocalVariableNode("rot",Type.getDescriptor(EventRotationAnimation.class),null,new LabelNode(),new LabelNode(), method.maxLocals);
                        method.localVariables.add(rot);

                        hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventRotationAnimation.class)));
                        hook.add(new InsnNode(DUP));
                        hook.add(new TypeInsnNode(NEW,Type.getInternalName(EntityLivingBase.class)));
                        hook.add(new InsnNode(DUP));
                        hook.add(new VarInsnNode(ALOAD,1));
                        hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityLivingBase.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                        hook.add(new VarInsnNode(FLOAD,f));
                        hook.add(new VarInsnNode(FLOAD,f1));
                        hook.add(new VarInsnNode(FLOAD,f2));
                        hook.add(new VarInsnNode(FLOAD,f7));
                        hook.add(new VarInsnNode(FLOAD,9));
                        hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventRotationAnimation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityLivingBase.class),Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE)));
                        hook.add(new VarInsnNode(ASTORE,rot.index));

                        hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                        hook.add(new VarInsnNode(ALOAD,rot.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));


                        //              f = event.getRenderYawOffset();
                        hook.add(new VarInsnNode(ALOAD,rot.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                                , ObfuscateHelper.targetObfuscatedMethod(EventRotationAnimation.class,"getRenderYawOffset").getName(),"()F"));
                        hook.add(new VarInsnNode(FSTORE,f));
                        //				f1 = event.getRotationYawHead();
                        hook.add(new VarInsnNode(ALOAD,rot.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                                , ObfuscateHelper.targetObfuscatedMethod(EventRotationAnimation.class,"getRotationYawHead").getName(),"()F"));
                        hook.add(new VarInsnNode(FSTORE,f1));
                        //				f2 = event.getRenderHeadYaw();
                        hook.add(new VarInsnNode(ALOAD,rot.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                                , ObfuscateHelper.targetObfuscatedMethod(EventRotationAnimation.class,"getRenderHeadYaw").getName(),"()F"));
                        hook.add(new VarInsnNode(FSTORE,f2));
                        //				f7 = event.getRenderHeadPitch();
                        hook.add(new VarInsnNode(ALOAD,rot.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                                , ObfuscateHelper.targetObfuscatedMethod(EventRotationAnimation.class,"getRenderHeadPitch").getName(),"()F"));
                        hook.add(new VarInsnNode(FSTORE,f7));
                        method.instructions.insert(f7OP,hook);
                    }else {
                        System.out.println("Find target fail");
                    }
                    */
                }
                {
                    //PreRenderLiving
                    InsnList hook = new InsnList();
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventPreRenderLiving.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EntityLivingBase.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ALOAD,1));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityLivingBase.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(RendererLivingEntity.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(RendererLivingEntity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                    hook.add(new VarInsnNode(DLOAD, 2));
                    hook.add(new VarInsnNode(DLOAD, 4));
                    hook.add(new VarInsnNode(DLOAD, 6));
                    hook.add(new VarInsnNode(FLOAD, 8));
                    hook.add(new VarInsnNode(FLOAD, 9));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPreRenderLiving.class),"<init>"
                            ,Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityLivingBase.class),Type.getType(RendererLivingEntity.class)
                            ,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE)));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    method.instructions.insert(hook);
                }
                {
                    //PostRenderLiving
                    List<AbstractInsnNode> ret = new ArrayList<>();
                    for (AbstractInsnNode instruction : method.instructions) {
                        if (instruction.getOpcode() == RETURN)
                            ret.add(instruction);
                    }
                    for (AbstractInsnNode insnNode : ret) {
                        InsnList hook = new InsnList();
                        hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                        hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventPostRenderLiving.class)));
                        hook.add(new InsnNode(DUP));
                        hook.add(new TypeInsnNode(NEW,Type.getInternalName(EntityLivingBase.class)));
                        hook.add(new InsnNode(DUP));
                        hook.add(new VarInsnNode(ALOAD,1));
                        hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityLivingBase.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                        hook.add(new TypeInsnNode(NEW,Type.getInternalName(RendererLivingEntity.class)));
                        hook.add(new InsnNode(DUP));
                        hook.add(new VarInsnNode(ALOAD,0));
                        hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(RendererLivingEntity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                        hook.add(new VarInsnNode(DLOAD, 2));
                        hook.add(new VarInsnNode(DLOAD, 4));
                        hook.add(new VarInsnNode(DLOAD, 6));
                        hook.add(new VarInsnNode(FLOAD, 8));
                        hook.add(new VarInsnNode(FLOAD, 9));
                        hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPostRenderLiving.class),"<init>"
                                ,Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityLivingBase.class),Type.getType(RendererLivingEntity.class)
                                ,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.DOUBLE_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE)));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                        method.instructions.insertBefore(insnNode,hook);
                    };
                }
            }
        }
    }
}
