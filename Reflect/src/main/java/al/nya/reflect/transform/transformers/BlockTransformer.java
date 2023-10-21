package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumWorldBlockLayer;
import al.nya.reflect.wrapper.wraps.wrapper.world.IBlockAccess;
import by.radioegor146.nativeobfuscator.Native;

public class BlockTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Block.BlockClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes){
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(Block.shouldSideBeRendered.getName()) &&
                    method.desc.equals("(L"+Type.getInternalName(IBlockAccess.IBlockAccessClass)+";L"+Type.getInternalName(BlockPos.BlockPosClass)+
                            ";L"+Type.getInternalName(EnumFacing.EnumFacingClass)+";)Z")){
                //Hook shouldSideBeRendered -> EventBus.hookFunction_shouldSideBeRendered
                System.out.println("Hook shouldSideBeRendered");
                InsnList hookInsn = new InsnList();
                LabelNode end = new LabelNode();
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,1));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,2));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,3));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.maxX.getName(),"D"));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.maxY.getName(),"D"));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.maxZ.getName(),"D"));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.minX.getName(),"D"));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.minY.getName(),"D"));
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Block.BlockClass),Block.minZ.getName(),"D"));
                hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"hookFunction_shouldSideBeRendered",
                        "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Enum;DDDDDD)Z",false));
                hookInsn.add(new JumpInsnNode(Opcodes.IFEQ,end));
                hookInsn.add(new InsnNode(Opcodes.ICONST_1));
                hookInsn.add(new InsnNode(Opcodes.IRETURN));
                hookInsn.add(end);
                method.instructions.insert(hookInsn);
            }
            if (method.name.equals(Block.getBlockLayer.getName()) &&
                    method.desc.equals("()L"+Type.getInternalName(EnumWorldBlockLayer.EnumWorldBlockLayerClass)+";")){
                //Hook getBlockLayer -> EventBus.hookFunction_getBlockLayer
                System.out.println("Hook getBlockLayer");
                InsnList hookInsn = new InsnList();
                hookInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"hookFunction_getBlockLayer",
                        "(Ljava/lang/Object;)Ljava/lang/Object;",false));
                hookInsn.add(new TypeInsnNode(Opcodes.CHECKCAST,Type.getInternalName(EnumWorldBlockLayer.EnumWorldBlockLayerClass)));
                hookInsn.add(new InsnNode(Opcodes.ARETURN));
                method.instructions = hookInsn;
            }
            if (method.name.equals(Block.getMixedBrightnessForBlock.getName()) &&
                    method.desc.equals("(L"+Type.getInternalName(IBlockAccess.IBlockAccessClass)+";L"+Type.getInternalName(BlockPos.BlockPosClass)+
                            ";)I")){
                System.out.println("Hook getMixedBrightnessForBlock");
                AbstractInsnNode point1 = null;
                AbstractInsnNode point2 = null;
                int count1 = 0;
                int count2 = 0;
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction instanceof VarInsnNode){
                        VarInsnNode temp = (VarInsnNode) instruction;
                        if (temp.getOpcode() == Opcodes.ALOAD && temp.var == 2){
                            count1++;
                            if (count1 == 2&&point1 == null){
                                point1 = instruction;
                            }
                        }
                    }
                    if (instruction.getOpcode() == Opcodes.INVOKEINTERFACE){
                        count2++;
                        if (count2 == 3&&point2 == null){
                            point2 = instruction;
                        }
                    }
                }
                if (point1 != null && point2 != null){
                    System.out.println("Insert");
                    InsnList hookInsn = new InsnList();
                    LabelNode end = new LabelNode();
                    LabelNode L1 = new LabelNode();
                    hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"getXRayState","()Z",false));
                    hookInsn.add(new JumpInsnNode(Opcodes.IFEQ,L1));
                    hookInsn.add(new LdcInsnNode(100000));
                    hookInsn.add(new JumpInsnNode(Opcodes.GOTO,end));
                    hookInsn.add(L1);
                    method.instructions.insert(point1,hookInsn);
                    method.instructions.insertBefore(point2,end);
                }
            }
            if (method.name.equals(Block.getAmbientOcclusionLightValue.getName()) && method.desc.equals("()F")){
                System.out.println("Hook getAmbientOcclusionLightValue");
                InsnList hookInsn = new InsnList();
                LabelNode L1 = new LabelNode();
                hookInsn.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"getXRayState","()Z",false));
                hookInsn.add(new JumpInsnNode(Opcodes.IFEQ,L1));
                hookInsn.add(new InsnNode(Opcodes.FCONST_1));
                hookInsn.add(new InsnNode(Opcodes.FRETURN));
                hookInsn.add(L1);
                method.instructions.insert(hookInsn);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
