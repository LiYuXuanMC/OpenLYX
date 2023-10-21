package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.EventCloseChannel;
import al.logger.client.event.client.render.EventText;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.render.FontRenderer;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;

public class FontRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return FontRenderer.FontRendererClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(FontRenderer.renderString.getName()) && method.desc.equals(Type.getMethodDescriptor(FontRenderer.renderString))) {
                LocalVariableNode eventText = new LocalVariableNode("et", "L" + Type.getInternalName(EventText.class) + ";", null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventText);
                InsnList hook = new InsnList();
                LabelNode L0 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventText.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD, 1));
                hook.add(new VarInsnNode(FLOAD, 2));
                hook.add(new VarInsnNode(FLOAD, 3));
                hook.add(new VarInsnNode(ILOAD, 4));
                hook.add(new VarInsnNode(ILOAD, 5));
                hook.add(new LdcInsnNode(true));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventText.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(String.class),Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.INT_TYPE,Type.BOOLEAN_TYPE,Type.BOOLEAN_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventText.index));

                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ, L0));
                hook.add(new VarInsnNode(ALOAD, eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getReturnValue").getName(), "()I"));
                hook.add(new InsnNode(IRETURN));
                hook.add(L0);
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getText").getName(),"()Ljava/lang/String;"));
                hook.add(new VarInsnNode(ASTORE,1));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getX").getName(),"()F"));
                hook.add(new VarInsnNode(FSTORE,2));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getY").getName(),"()F"));
                hook.add(new VarInsnNode(FSTORE,3));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getColor").getName(),"()I"));
                hook.add(new VarInsnNode(ISTORE,4));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"isDropShadow").getName(),"()Z"));
                hook.add(new VarInsnNode(ISTORE,5));
                method.instructions.insert(hook);
            }
            if (method.name.equals(FontRenderer.getStringWidth.getName()) && method.desc.equals("(Ljava/lang/String;)I")){
                InsnList hook = new InsnList();
                LocalVariableNode eventText = new LocalVariableNode("et","L"+Type.getInternalName(EventText.class)+";",null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(eventText);
                LabelNode L0 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventText.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD, 1));
                hook.add(new LdcInsnNode(0.0F));
                hook.add(new LdcInsnNode(0.0F));
                hook.add(new LdcInsnNode(0));
                hook.add(new LdcInsnNode(false));
                hook.add(new LdcInsnNode(false));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventText.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(String.class),Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.INT_TYPE,Type.BOOLEAN_TYPE,Type.BOOLEAN_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventText.index));

                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getText").getName(),"()Ljava/lang/String;"));
                hook.add(new VarInsnNode(ASTORE,1));

                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ, L0));
                hook.add(new VarInsnNode(ALOAD,eventText.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        ObfuscateHelper.targetObfuscatedMethod(EventText.class,"getReturnValue").getName(),"()I"));
                hook.add(new InsnNode(IRETURN));
                hook.add(L0);
                method.instructions.insert(hook);
            }
        }
    }
}
