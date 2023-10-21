package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.render.WorldRenderer;
import by.radioegor146.nativeobfuscator.Native;

public class WorldRendererTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return WorldRenderer.WorldRendererClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes){
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(WorldRenderer.putColorMultiplier.getName()) && method.desc.equals("(FFFI)V")){
                System.out.println("Hook putColorMultiplier");
                AbstractInsnNode point = null;
                AbstractInsnNode[] array = method.instructions.toArray();
                for (int i = array.length - 1; i >= 0; i--) {
                    AbstractInsnNode abstractInsnNode = array[i];
                    if (abstractInsnNode instanceof VarInsnNode){
                        VarInsnNode temp = (VarInsnNode)abstractInsnNode;
                        if (temp.getOpcode() == Opcodes.ILOAD && temp.var == 6){
                            point = temp;
                            break;
                        }
                    }
                }
                if (point != null){
                    System.out.println("Insert");
                    InsnList hookInsn = new InsnList();
                    LabelNode end = new LabelNode();
                    LabelNode L1 = new LabelNode();
                    hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"hookFunction_putColorMultiplier","()Z",false));
                    hookInsn.add(new JumpInsnNode(Opcodes.IFEQ,L1));
                    hookInsn.add(new VarInsnNode(Opcodes.ILOAD,6));
                    hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"getXRayColor","(I)I",false));
                    hookInsn.add(new JumpInsnNode(Opcodes.GOTO,end));
                    hookInsn.add(L1);
                    method.instructions.insertBefore(point,hookInsn);
                    method.instructions.insert(point,end);
                }
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
