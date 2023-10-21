package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.mac.EventMACMouseOver;
import al.logger.client.event.client.render.EventCameraClip;
import al.logger.client.event.client.render.EventDrawBlockHighlight;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.Combat.Reach;
import al.logger.client.features.modules.impls.Visual.NoHurtCam;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.multiplayer.PlayerControllerMP;
import al.logger.client.wrapper.LoggerMC.render.EntityRenderer;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class EntityRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityRenderer.EntityRendererClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(EntityRenderer.getMouseOver.getName()) && method.desc.equals("(F)V") && EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)){
                LocalVariableNode mouseOver = new LocalVariableNode("mouseOver",Type.getDescriptor(EventMACMouseOver.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(mouseOver);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventMACMouseOver.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(FLOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventMACMouseOver.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.FLOAT_TYPE)));
                hook.add(new VarInsnNode(ASTORE,mouseOver.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,mouseOver.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,mouseOver.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMACMouseOver.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));

                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(EntityRenderer.renderWorldPass.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityRenderer.renderWorldPass))){
                InsnList insnList = new InsnList();
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender3D.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(FLOAD,2));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender3D.class), "<init>", "(F)V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                //Hook2
                InsnList hook2 = new InsnList();
                hook2.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook2.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook2.add(new TypeInsnNode(NEW, Type.getInternalName(EventDrawBlockHighlight.class)));
                hook2.add(new InsnNode(DUP));
                hook2.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventDrawBlockHighlight.class), "<init>", "()V"));
                hook2.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));

                boolean transformed = false;
                for (AbstractInsnNode instruction : method.instructions) {
                    insnList.add(instruction);
                    if (instruction instanceof MethodInsnNode && (!transformed)){
                        if (((MethodInsnNode) instruction).owner.equals(Type.getInternalName(GlStateManager.GlStateManagerClass))
                                && ((MethodInsnNode) instruction).name.equals(GlStateManager.alphaFunc.getName())&&((MethodInsnNode) instruction).desc.equals("(IF)V")){
                            insnList.add(hook);
                            transformed = true;
                        }
                    }
                    if (instruction instanceof LdcInsnNode){
                        if (((LdcInsnNode) instruction).cst.equals("outline")){
                            insnList.insert(hook2);
                            System.out.println("Transformed Outline");
                        }
                    }
                }
                method.instructions = insnList;
                method.maxLocals++;
            }
            if (method.name.equals(EntityRenderer.getMouseOver.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityRenderer.getMouseOver)) && !EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)){
                AbstractInsnNode newMovingObjectPosition = null;
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction instanceof TypeInsnNode){
                        if (instruction.getOpcode() == NEW && ((TypeInsnNode) instruction).desc.equals(Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass))){
                            newMovingObjectPosition = instruction;
                            break;
                        }
                    }
                }
                AbstractInsnNode L52 = newMovingObjectPosition.getPrevious();
                while (!(L52 instanceof LabelNode)){
                    while (!(L52 instanceof LabelNode)){
                        L52 = L52.getPrevious();
                    }
                }
                AbstractInsnNode ALOAD0 = null;
                ALOAD0 = backFindALOAD(L52,0);
                AbstractInsnNode getBlockReachDistance = findMethodCall(PlayerControllerMP.getBlockReachDistance,method.instructions);
                AbstractInsnNode DSTORE3 = getBlockReachDistance.getNext();
                while (!(DSTORE3 instanceof VarInsnNode)){
                    DSTORE3 = DSTORE3.getNext();
                }
                AbstractInsnNode DLOAD3 = DSTORE3.getNext();
                while (!(DLOAD3 instanceof VarInsnNode && ((VarInsnNode) DLOAD3).var == ((VarInsnNode) DSTORE3).var)){
                    DLOAD3 = DLOAD3.getNext();
                }
                AbstractInsnNode DSTORE5 = DSTORE3.getNext();
                while (!(DSTORE5 instanceof VarInsnNode && ((VarInsnNode) DSTORE5).var == ((VarInsnNode) DSTORE3).var)){
                    DSTORE5 = DSTORE5.getNext();
                }
                DSTORE5 = DSTORE5.getNext();
                while (!(DSTORE5 instanceof VarInsnNode && ((VarInsnNode) DSTORE5).var == ((VarInsnNode) DSTORE3).var)){
                    DSTORE5 = DSTORE5.getNext();
                }
                DSTORE5 = DSTORE5.getNext();
                AbstractInsnNode getLook = findMethodCall(Entity.getLook,method.instructions);
                AbstractInsnNode ALOAD2 = backFindALOAD(getLook,2);
                MethodInsnNode extendedReach = null;
                for (AbstractInsnNode abstractInsnNode : method.instructions) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (((MethodInsnNode) abstractInsnNode).name.equals(PlayerControllerMP.extendedReach.getName()) &&
                                ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(PlayerControllerMP.PlayerControllerMPClass))
                                && ((MethodInsnNode) abstractInsnNode).desc.equals("()Z")){
                            extendedReach = (MethodInsnNode) abstractInsnNode;
                        }
                    }
                }
                if (ALOAD0 != null && DSTORE3 != null && DLOAD3 != null && ALOAD2 != null && DSTORE5 != null && extendedReach != null){
                    LocalVariableNode reach = new LocalVariableNode("Reach",Type.getDescriptor(Reach.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                    {
                        //double d0 = reach.getMaxRange()
                        LabelNode L1 = new LabelNode();
                        InsnList hook = new InsnList();
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                        hook.add(new JumpInsnNode(IFEQ,L1));
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Reach.class,"getMaxRange").getName(),"()D"));
                        hook.add(new VarInsnNode(DSTORE,((VarInsnNode) DSTORE3).var));
                        hook.add(L1);
                        method.instructions.insert(DSTORE3,hook);
                    }
                    {
                        //Reach reach = Logger.getInstance().getModuleManager().getModule(Reach.class);
                        InsnList hook = new InsnList();
                        hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                        hook.add(new LdcInsnNode(Type.getType(Reach.class)));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                        hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Reach.class)));
                        hook.add(new VarInsnNode(ASTORE,reach.index));
                        method.instructions.insert(DSTORE3,hook);
                    }
                    {
                        //this.mc.getObjectMouseOver() = entity.rayTrace(reach.getState() ? reach.getBuildReachValue().get() : d0, p_getMouseOver_1_);
                        LabelNode L1 = new LabelNode();
                        LabelNode L2 = new LabelNode();
                        InsnList hook = new InsnList();
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                        hook.add(new JumpInsnNode(IFEQ,L1));
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Reach.class,"getBuildRange").getName(),"()D"));
                        hook.add(new JumpInsnNode(GOTO,L2));
                        hook.add(L1);
                        method.instructions.insertBefore(DLOAD3,hook);
                        method.instructions.insert(DLOAD3,L2);
                    }
                    {
                        //            if (reach.getState()) {
                        //                d1 = reach.getCombatReachValue().get();
                        //            }
                        InsnList hook = new InsnList();
                        LabelNode L1 = new LabelNode();
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                        hook.add(new JumpInsnNode(IFEQ,L1));
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Reach.class,"getAttackRange").getName(),"()D"));
                        hook.add(new VarInsnNode(DSTORE,((VarInsnNode) DSTORE5).var));
                        hook.add(L1);
                        method.instructions.insertBefore(ALOAD2,hook);
                    }
                    {
                        AbstractInsnNode jumpInsnNode = extendedReach.getNext();
                        while (jumpInsnNode.getOpcode() != IFLE){
                            jumpInsnNode = jumpInsnNode.getNext();
                        }
                        AbstractInsnNode ICONST = jumpInsnNode.getNext();
                        while (ICONST.getOpcode() != ICONST_1){
                            ICONST = ICONST.getNext();
                        }
                        method.instructions.remove(ICONST);
                        InsnList hook = new InsnList();
                        LabelNode L1 = new LabelNode();
                        LabelNode L2 = new LabelNode();
                        hook.add(new VarInsnNode(ALOAD,reach.index));
                        hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                        hook.add(new JumpInsnNode(IFNE,L1));
                        hook.add(new InsnNode(ICONST_1));
                        hook.add(new JumpInsnNode(GOTO,L2));
                        hook.add(L1);
                        hook.add(new InsnNode(ICONST_0));
                        hook.add(L2);
                        method.instructions.insert(jumpInsnNode,hook);
                    }
                }else {
                    System.out.println("ALOAD0 "+ALOAD0 +" DSTORE3 "+ DSTORE3+" DLOAD3 "+DLOAD3+" ALOAD2 "+ALOAD2+" DSTORE5 "+DSTORE5+" extendedReach "+extendedReach);
                }
            }
            if (method.name.equals(EntityRenderer.hurtCameraEffect.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityRenderer.hurtCameraEffect))){
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                hook.add(new LdcInsnNode(Type.getType(NoHurtCam.class)));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(NoHurtCam.class)));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(NoHurtCam.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(EntityRenderer.orientCamera.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityRenderer.orientCamera))){
                AbstractInsnNode vec3_DistanceTo = findMethodCall(Vec3.distanceTo,method.instructions);

                LocalVariableNode eventCameraClip = new LocalVariableNode("eventCameraClip",Type.getDescriptor(EventCameraClip.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventCameraClip);

                LabelNode L1 = new LabelNode();
                InsnList hook = new InsnList();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventCameraClip.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EntityRenderer.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,0));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityRenderer.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new VarInsnNode(FLOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventCameraClip.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityRenderer.class),Type.FLOAT_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventCameraClip.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventCameraClip.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventCameraClip.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventCameraClip.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insertBefore(vec3_DistanceTo,hook);
            }
        }
        //dump(cn,0);
    }
}
