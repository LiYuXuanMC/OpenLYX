package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.EventText;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import by.radioegor146.nativeobfuscator.Native;

public class FontRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return FontRenderer.FontRendererClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes){
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(FontRenderer.renderString.getName()) &&
                    method.desc.equals("(Ljava/lang/String;FFIZ)I")) {
                InsnList hookDrawString = new InsnList();
                LocalVariableNode eventText = new LocalVariableNode("et", "L" + Type.getInternalName(EventText.class) + ";", null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventText);
                LabelNode L0 = new LabelNode();
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD, 1));
                hookDrawString.add(new VarInsnNode(Opcodes.FLOAD, 2));
                hookDrawString.add(new VarInsnNode(Opcodes.FLOAD, 3));
                hookDrawString.add(new VarInsnNode(Opcodes.ILOAD, 4));
                hookDrawString.add(new VarInsnNode(Opcodes.ILOAD, 5));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),
                        "onRenderString", "(Ljava/lang/String;FFIZ)L" + Type.getInternalName(EventText.class) + ";"));
                hookDrawString.add(new VarInsnNode(Opcodes.ASTORE, eventText.index));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD, eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventText.class),
                        "isCancel", "()Z"));
                hookDrawString.add(new JumpInsnNode(Opcodes.IFEQ, L0));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD, eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(EventText.class),
                        "getReturnValue", "()I"));
                hookDrawString.add(new InsnNode(Opcodes.IRETURN));
                hookDrawString.add(L0);
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getText","()Ljava/lang/String;"));
                hookDrawString.add(new VarInsnNode(Opcodes.ASTORE,1));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getX","()F"));
                hookDrawString.add(new VarInsnNode(Opcodes.FSTORE,2));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getY","()F"));
                hookDrawString.add(new VarInsnNode(Opcodes.FSTORE,3));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getColor","()I"));
                hookDrawString.add(new VarInsnNode(Opcodes.ISTORE,4));
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "isDropShadow","()Z"));
                hookDrawString.add(new VarInsnNode(Opcodes.ISTORE,5));
                method.instructions.insert(hookDrawString);
            }
            if (method.name.equals(FontRenderer.getStringWidth.getName()) && method.desc.equals("(Ljava/lang/String;)I")){
                InsnList hookGetStringWidth = new InsnList();
                LocalVariableNode eventText = new LocalVariableNode("et","L"+Type.getInternalName(EventText.class)+";",null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(eventText);
                LabelNode L0 = new LabelNode();
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ALOAD,1));
                hookGetStringWidth.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),
                        "onGetStringWidth","(Ljava/lang/String;)L"+Type.getInternalName(EventText.class)+";"));
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ASTORE,eventText.index));
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookGetStringWidth.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getText","()Ljava/lang/String;"));
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ASTORE,1));
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookGetStringWidth.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "isCancel","()Z"));
                hookGetStringWidth.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                hookGetStringWidth.add(new VarInsnNode(Opcodes.ALOAD,eventText.index));
                hookGetStringWidth.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventText.class),
                        "getReturnValue","()I"));
                hookGetStringWidth.add(new InsnNode(Opcodes.IRETURN));
                hookGetStringWidth.add(L0);
                method.instructions.insert(hookGetStringWidth);
            }
            //Use renderString hook pls
            /*if (method.name.equals(FontRenderer.drawString_FFIZ.getName()) && method.desc.equals("(Ljava/lang/String;FFIZ)I")) { //
//                计划在头部插入
//                if (EventBus.canReplaceFontRenderer()) {
//                    return EventBus.onDrawString(string,f1,f2,int1,b1);
//                }

                InsnList hookDrawString = new InsnList();
                hookDrawString.add(new VarInsnNode(Opcodes.ALOAD,1));
                hookDrawString.add(new VarInsnNode(Opcodes.FLOAD,2));
                hookDrawString.add(new VarInsnNode(Opcodes.FLOAD,3));
                hookDrawString.add(new VarInsnNode(Opcodes.ILOAD,4));
                hookDrawString.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),
                        "onDrawString","(Ljava/lang/String;FFIZ)F"));
            }
            if (method.name.equals(FontRenderer.getStringWidth.getName()) && method.desc.equals("(Ljava/lang/String;)I")) {
//                计划在头部插入
//                if (EventBus.canReplaceFontRenderer()) {
//                    return EventBus.onGetStringWidth(string);
//                }
            }

             */
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}

