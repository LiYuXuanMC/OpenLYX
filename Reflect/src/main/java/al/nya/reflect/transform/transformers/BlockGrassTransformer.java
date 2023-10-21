package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.block.BlockGrass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumWorldBlockLayer;
import by.radioegor146.nativeobfuscator.Native;

public class BlockGrassTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return BlockGrass.BlockGrassClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes){
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(BlockGrass.getBlockLayer.getName()) &&
                    method.desc.equals("()L"+Type.getInternalName(EnumWorldBlockLayer.EnumWorldBlockLayerClass)+";")) {
                //Hook getBlockLayer -> EventBus.hookFunction_getBlockLayer
                System.out.println("Hook getBlockLayer");
                InsnList insnList = new InsnList();
                LabelNode labelNode = new LabelNode();
                insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class), "getXRayState", "()Z", false));
                insnList.add(new JumpInsnNode(Opcodes.IFEQ, labelNode));
                insnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"TRANSLUCENT", "()Ljava/lang/Object;",false));
                insnList.add(new TypeInsnNode(Opcodes.CHECKCAST, Type.getInternalName(EnumWorldBlockLayer.EnumWorldBlockLayerClass)));
                insnList.add(new InsnNode(Opcodes.ARETURN));
                insnList.add(labelNode);
                method.instructions.insert(insnList);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
