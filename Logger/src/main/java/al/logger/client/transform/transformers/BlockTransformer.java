package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.world.EventBlockBB;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.transform.OneTimeTransformer;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.state.BlockState;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.libs.asm.Opcodes;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;

public class BlockTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Block.BlockClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(Block.addCollisionBoxesToList.getName()) && method.desc.equals(Type.getMethodDescriptor(Block.addCollisionBoxesToList))){
                AbstractInsnNode aSTORE = null;
                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof VarInsnNode){
                        if (node.getOpcode() == ASTORE){
                            aSTORE = node;
                        }
                    }
                }
                if (aSTORE != null){
                    InsnList insnList = new InsnList();

                    //AxisAlignedBB -> EventBB(2) AxisAlignedBB
                    insnList.add(new TypeInsnNode(NEW, Type.getInternalName(EventBlockBB.class)));
                    insnList.add(new InsnNode(DUP));
                    insnList.add(new InsnNode(DUP2_X1));
                    insnList.add(new InsnNode(POP2));

                    //EventBB(2) AxisAlignedBB -> EventBB(2) WrappedAxisAlignedBB
                    insnList.add(new TypeInsnNode(NEW, Type.getInternalName(AxisAlignedBB.class)));
                    insnList.add(new InsnNode(DUP));
                    insnList.add(new InsnNode(DUP2_X1));
                    insnList.add(new InsnNode(POP2));
                    insnList.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(AxisAlignedBB.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)), false));

                    //EventBB(2) WrappedAxisAlignedBB -> EventBB(2) WrappedAxisAlignedBB BlockPos
                    insnList.add(new VarInsnNode(ALOAD, 2));

                    //EventBB(2) WrappedAxisAlignedBB BlockPos -> EventBB(2) WrappedAxisAlignedBB WrappedBlockPos
                    insnList.add(new TypeInsnNode(NEW, Type.getInternalName(BlockPos.class)));
                    insnList.add(new InsnNode(DUP));
                    insnList.add(new InsnNode(DUP2_X1));
                    insnList.add(new InsnNode(POP2));
                    insnList.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(BlockPos.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)), false));

                    //EventBB(2) WrappedAxisAlignedBB WrappedBlockPos -> EventBB(2) WrappedAxisAlignedBB WrappedBlockPos BlockState
                    insnList.add(new VarInsnNode(ALOAD, 0));
                    insnList.add(new FieldInsnNode(GETFIELD, Type.getInternalName(Block.BlockClass),Block.blockState.getName(), Type.getDescriptor(BlockState.BlockStateClass)));

                    //EventBB(2) WrappedAxisAlignedBB WrappedBlockPos BlockState -> EventBB(2) WrappedAxisAlignedBB WrappedBlockPos Block
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(BlockState.BlockStateClass),BlockState.getBlock.getName(), Type.getMethodDescriptor(BlockState.getBlock), false));

                    //EventBB(2) WrappedAxisAlignedBB WrappedBlockPos Block -> EventBB(2) WrappedAxisAlignedBB WrappedBlockPos WrappedBlock
                    insnList.add(new TypeInsnNode(NEW, Type.getInternalName(Block.class)));
                    insnList.add(new InsnNode(DUP));
                    insnList.add(new InsnNode(DUP2_X1));
                    insnList.add(new InsnNode(POP2));
                    insnList.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(Block.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)), false));

                    //EventBB(2) -> EventBB
                    insnList.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventBlockBB.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(AxisAlignedBB.class),Type.getType(BlockPos.class),Type.getType(Block.class)), false));

                    //EventBB -> EventBB(2)
                    insnList.add(new InsnNode(DUP));

                    //Post Event
                    insnList.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    insnList.add(new InsnNode(SWAP));
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                    //EventBB -> WrappedAxisAlignedBB
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBlockBB.class), ObfuscateHelper.targetObfuscatedMethod(EventBlockBB.class,"getBoundingBox").getName(), Type.getMethodDescriptor(Type.getType(AxisAlignedBB.class))));

                    //WrappedAxisAlignedBB -> AxisAlignedBB
                    insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(AxisAlignedBB.class),ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName(),Type.getMethodDescriptor(Type.getType(Object.class))));
                    insnList.add(new TypeInsnNode(CHECKCAST, Type.getInternalName(AxisAlignedBB.AxisAlignedBBClass)));


                    method.instructions.insertBefore(aSTORE, insnList);
                }
            }
        }
    }
}
