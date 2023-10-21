package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.render.EventPreRenderPlayer;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.render.entity.RenderPlayer;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import com.google.common.base.Predicates;

public class RenderPlayerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return RenderPlayer.RenderPlayerClass;
    }

    @Override
    public void transformClass(ClassNode cn) {


        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(RenderPlayer.doRender.getName()) && method.desc.equals(Type.getMethodDescriptor(RenderPlayer.doRender))) {
                InsnList hook = new InsnList();

                //B:
                /*
                    INVOKESTATIC al/logger/client/Logger.getInstance ()Lal/logger/client/Logger;
    INVOKEVIRTUAL al/logger/client/Logger.getEventBus ()Lal/logger/client/event/EventBus;
    NEW al/logger/client/event/client/render/EventPreRenderPlayer
    DUP
    ALOAD 1
    DLOAD 2
    DLOAD 4
    DLOAD 6
    FLOAD 8
    FLOAD 9
    INVOKESPECIAL al/logger/client/event/client/render/EventPreRenderPlayer.<init> (Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V
    INVOKEVIRTUAL al/logger/client/event/EventBus.callEvent (Lal/logger/client/event/Event;)V
                 */



                //INVOKESTATIC al/logger/client/Logger.getInstance()Lal/logger/client/Logger;
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                //INVOKEVIRTUAL al/logger/client/Logger.getEventBus()Lal/logger/client/event/EventBus;
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));

                //NEW al/logger/client/event/client/render/EventPreRenderPlayer
                //DUP
                //ALOAD 1
                //ALOAD 0
                //FLOAD 9
                //DLOAD 2
                //DLOAD 4
                //DLOAD 6
                //INVOKESPECIAL al/logger/client/event/client/render/EventPreRenderPlayer.<init>(Ljava/lang/Object;Ljava/lang/Object;FDDD)V
                //INVOKEVIRTUAL al/logger/client/event/EventBus.callEvent(Lal/logger/client/event/Event;)V
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventPreRenderPlayer.class)));
                hook.add(new InsnNode(DUP));

                //ALOAD 1
                //ALOAD 0
                //FLOAD 9
                //DLOAD 2
                //DLOAD 4
                //DLOAD 6
                hook.add(new VarInsnNode(ALOAD, 1));
                hook.add(new VarInsnNode(ALOAD, 0));
                hook.add(new VarInsnNode(FLOAD, 9));
                hook.add(new VarInsnNode(DLOAD, 2));
                hook.add(new VarInsnNode(DLOAD, 4));
                hook.add(new VarInsnNode(DLOAD, 6));

                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPreRenderPlayer.class), "<init>", "(Ljava/lang/Object;Ljava/lang/Object;FDDD)V", false));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                for(AbstractInsnNode insnNode : method.instructions){
                    if(insnNode instanceof MethodInsnNode){
                        MethodInsnNode methodInsnNode = (MethodInsnNode)insnNode;
                        if(methodInsnNode.name.equals(RenderPlayer.doRender.getName())){
                            method.instructions.insertBefore(insnNode,hook);
                            break;
                        }
                    }
                }
                System.out.println("Transformed!");
            }
        }
    }
}
