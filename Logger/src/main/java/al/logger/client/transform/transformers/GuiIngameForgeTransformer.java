package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.event.client.render.EventRenderExpBar;
import al.logger.client.event.client.render.EventRenderHorseJumpBar;
import al.logger.client.event.client.render.EventRenderPlayerStats;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.gui.GuiIngame;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class GuiIngameForgeTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EnvironmentDetector.getGuiIngameForge();
    }

    @Override
    public void transformClass(ClassNode cn) {
        System.out.println("Checking ClassInfo");
        for (MethodNode method : cn.methods) {
            if (method == null){
                System.out.println("Find Null method");
            }
            if(method.name == null){
                System.out.println("Find Null Method Name");
            }
            if(method.desc == null){
                System.out.println("Find Null Method Desc");
            }
        }
        for (MethodNode method : cn.methods) {
            if(method == null)continue;

            try{
                if (method.name.equals(GuiIngame.renderTooltip.getName()) && method.desc.equals("(L" + Type.getInternalName(ScaledResolution.ScaledResolutionClass) + ";F)V")){
                    LocalVariableNode eventRender2D = new LocalVariableNode("eventRender2D",Type.getDescriptor(EventRender2D.class),null,null,null, method.maxLocals);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender2D.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(FLOAD,2));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(F)V"));
                    hook.add(new VarInsnNode(ASTORE,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRender2D.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new InsnNode(RETURN));
                    hook.add(L1);
                    method.instructions.insert(hook);
                }if (method.name.equals(GuiIngame.renderExpBar.getName()) && method.desc.equals(Type.getMethodDescriptor(GuiIngame.renderExpBar))){
                    LocalVariableNode eventRender2D = new LocalVariableNode("renderExpBar",Type.getDescriptor(EventRenderExpBar.class),null,null,null, method.maxLocals);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRenderExpBar.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRenderExpBar.class), "<init>", "()V"));
                    hook.add(new VarInsnNode(ASTORE,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRenderExpBar.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new InsnNode(RETURN));
                    hook.add(L1);
                    method.instructions.insert(hook);
                }if (method.name.equals(GuiIngame.renderPlayerStats.getName()) && method.desc.equals(Type.getMethodDescriptor(GuiIngame.renderPlayerStats))){
                    LocalVariableNode eventRender2D = new LocalVariableNode("renderPlayerStats",Type.getDescriptor(EventRenderPlayerStats.class),null,null,null, method.maxLocals);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRenderPlayerStats.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRenderPlayerStats.class), "<init>", "()V"));
                    hook.add(new VarInsnNode(ASTORE,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRenderPlayerStats.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new InsnNode(RETURN));
                    hook.add(L1);
                    method.instructions.insert(hook);
                }if (method.name.equals(GuiIngame.renderHorseJumpBar.getName()) && method.desc.equals(Type.getMethodDescriptor(GuiIngame.renderHorseJumpBar))){
                    LocalVariableNode eventRender2D = new LocalVariableNode("renderHorseJumpBar",Type.getDescriptor(EventRenderHorseJumpBar.class),null,null,null, method.maxLocals);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRenderHorseJumpBar.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRenderHorseJumpBar.class), "<init>", "()V"));
                    hook.add(new VarInsnNode(ASTORE,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRenderHorseJumpBar.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new InsnNode(RETURN));
                    hook.add(L1);
                    method.instructions.insert(hook);
                }if (method.name.equals(GuiIngame.renderHorseJumpBar.getName()) && method.desc.equals(Type.getMethodDescriptor(GuiIngame.renderHorseJumpBar))){
                    LocalVariableNode eventRender2D = new LocalVariableNode("renderHorseJumpBar",Type.getDescriptor(EventRenderHorseJumpBar.class),null,null,null, method.maxLocals);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRenderHorseJumpBar.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRenderHorseJumpBar.class), "<init>", "()V"));
                    hook.add(new VarInsnNode(ASTORE,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventRender2D.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRenderHorseJumpBar.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new InsnNode(RETURN));
                    hook.add(L1);
                    method.instructions.insert(hook);
                }
            }catch (Throwable a){
                System.out.println("Pre - Catch");
                a.printStackTrace();
            }
        }
    }
}
