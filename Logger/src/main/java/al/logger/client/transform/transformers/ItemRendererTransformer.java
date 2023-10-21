package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.render.EventAnimation;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.render.ItemRenderer;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class ItemRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return ItemRenderer.ItemRendererClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(ItemRenderer.renderItemInFirstPerson.getName()) && method.desc.equals(Type.getMethodDescriptor(ItemRenderer.renderItemInFirstPerson))){
                int index = 0;
                AbstractInsnNode transformFirstPersonItem = method.instructions.getFirst();
                while (index != 3){
                    transformFirstPersonItem = findMethodCall(ItemRenderer.transformFirstPersonItem,transformFirstPersonItem);
                    index++;
                }
                if (transformFirstPersonItem == null){
                    throw new RuntimeException("transformFirstPersonItem "+transformFirstPersonItem);
                }
                AbstractInsnNode ALOADNode = backFindALOAD(transformFirstPersonItem);
                JumpInsnNode GOTONode = (JumpInsnNode) findGOTO(transformFirstPersonItem);
                if (ALOADNode != null && GOTONode != null){
                    LocalVariableNode eventAnimation = new LocalVariableNode("ea",Type.getDescriptor(EventAnimation.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                    method.localVariables.add(eventAnimation);
                    LabelNode L1 = new LabelNode();
                    InsnList hook = new InsnList();
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventAnimation.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventAnimation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE)));
                    hook.add(new VarInsnNode(ASTORE,eventAnimation.index));
                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,eventAnimation.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                    hook.add(new VarInsnNode(ALOAD,eventAnimation.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventAnimation.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new JumpInsnNode(GOTO,GOTONode.label));
                    hook.add(L1);
                    method.instructions.insertBefore(ALOADNode,hook);
                }else {
                    throw new RuntimeException("ALOAD "+ALOADNode+" GOTO "+GOTONode);
                }
            }
        }
    }
}
