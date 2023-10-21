package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.render.EventRender3D;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.ghost.Reach;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.utils.FileUtil;
import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.PlayerControllerMP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.EntityRenderer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.GlStateManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectPosition;

import java.io.File;

public class EntityRendererTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return EntityRenderer.EntityRendererClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(EntityRenderer.renderWorldPass.getName()) && method.desc.equals("(IFJ)V")){
                InsnList insnList = new InsnList();
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender3D.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(FLOAD,2));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender3D.class), "<init>", "(F)V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
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
                }
                method.instructions = insnList;
                method.maxLocals++;
            }
            if (method.name.equals(EntityRenderer.getMouseOver.getName())&& method.desc.equals("(F)V") && !Wrapper.getMapper().hasAntiCheat(Environment.MargelesAntiCheat)){
                InsnList insnList = method.instructions;
                LocalVariableNode reach = new LocalVariableNode("reach", Type.getDescriptor(Reach.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(reach);
                MethodInsnNode extendedReach = null;
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (((MethodInsnNode) abstractInsnNode).name.equals(PlayerControllerMP.extendedReach.getName()) &&
                                ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(PlayerControllerMP.PlayerControllerMPClass))
                                && ((MethodInsnNode) abstractInsnNode).desc.equals("()Z")){
                            extendedReach = (MethodInsnNode) abstractInsnNode;
                        }
                    }
                }
                if (extendedReach != null) {
                    AbstractInsnNode jumpInsnNode = extendedReach.getNext();
                    while (jumpInsnNode.getOpcode() != IFLE){
                        jumpInsnNode = jumpInsnNode.getNext();
                    }
                    AbstractInsnNode ICONST = jumpInsnNode.getNext();
                    while (ICONST.getOpcode() != ICONST_1){
                        ICONST = ICONST.getNext();
                    }
                    insnList.remove(ICONST);
                    InsnList hook = new InsnList();
                    LabelNode L1 = new LabelNode();
                    LabelNode L2 = new LabelNode();
                    hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                    hook.add(new LdcInsnNode(Type.getType(Reach.class)));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                    hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Reach.class)));
                    hook.add(new VarInsnNode(ASTORE,reach.index));
                    hook.add(new VarInsnNode(ALOAD,reach.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFNE,L1));
                    hook.add(new InsnNode(ICONST_1));
                    hook.add(new JumpInsnNode(GOTO,L2));
                    hook.add(L1);
                    hook.add(new InsnNode(ICONST_0));
                    hook.add(L2);
                    insnList.insert(jumpInsnNode,hook);
                    method.instructions = insnList;
                }
                FieldInsnNode objectMouseOver = null;
                for (AbstractInsnNode insnNode : insnList) {
                    if (insnNode instanceof FieldInsnNode){
                        if (insnNode.getOpcode() == PUTFIELD && ((FieldInsnNode) insnNode).name.equals(Minecraft.objectMouseOver.getName())
                                && ((FieldInsnNode) insnNode).owner.equals(Type.getInternalName(Minecraft.MinecraftClass))){
                            objectMouseOver = (FieldInsnNode) insnNode;
                            break;
                        }
                    }
                }
                if (objectMouseOver != null){
                    LabelNode L1 = new LabelNode();
                    //    ALOAD 0
                    //    GETFIELD net/minecraft/client/renderer/EntityRenderer.field_78531_r : Lnet/minecraft/client/Minecraft;
                    //    ALOAD 2
                    //    DLOAD 3
                    //    FLOAD 1
                    //    INVOKEVIRTUAL net/minecraft/entity/Entity.func_174822_a (DF)Lnet/minecraft/util/MovingObjectPosition;
                    //    PUTFIELD net/minecraft/client/Minecraft.field_71476_x : Lnet/minecraft/util/MovingObjectPosition;
                    InsnList hook = new InsnList();
                    hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                    hook.add(new LdcInsnNode(Type.getType(Reach.class)));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                    hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Reach.class)));
                    hook.add(new VarInsnNode(ASTORE,reach.index));
                    hook.add(new VarInsnNode(ALOAD,reach.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L1));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new FieldInsnNode(GETFIELD,Type.getInternalName(EntityRenderer.EntityRendererClass),EntityRenderer.mc.getName(),"L"+Type.getInternalName(Minecraft.MinecraftClass)+";"));
                    hook.add(new VarInsnNode(ALOAD,2));
                    hook.add(new VarInsnNode(ALOAD,reach.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Reach.class,"getBlockRange").getName(),"()F"));
                    hook.add(new InsnNode(F2D));
                    hook.add(new VarInsnNode(FLOAD,1));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.rayTrace.getName(),"(DF)L"+Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass)+";"));
                    hook.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Minecraft.MinecraftClass),Minecraft.objectMouseOver.getName(),"L"+Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass)+";"));
                    hook.add(L1);
                    method.instructions.insert(objectMouseOver,hook);
                    AbstractInsnNode func_72438_d = objectMouseOver.getNext();
                    while (!(func_72438_d instanceof MethodInsnNode) ||
                            (!((MethodInsnNode) func_72438_d).owner.equals(Type.getInternalName(Vec3.Vec3Class))) ||
                            (!((MethodInsnNode) func_72438_d).name.equals(Vec3.distanceTo.getName())) ||
                            (!((MethodInsnNode) func_72438_d).desc.equals(Type.getMethodDescriptor(Vec3.distanceTo)))){
                        func_72438_d = func_72438_d.getNext();
                    }
                    //Hit func_72438_d
                    AbstractInsnNode dstore = func_72438_d.getNext();
                    while (!(dstore instanceof VarInsnNode)){
                        dstore = dstore.getNext();
                    }
                    int d1 = ((VarInsnNode) dstore).var;
                    AbstractInsnNode L6  = dstore.getNext();
                    while (!(L6 instanceof LabelNode)){
                        L6 = L6.getNext();
                    }
                    int vec3 = 0;
                    for (AbstractInsnNode instruction : method.instructions) {
                        if (instruction instanceof MethodInsnNode){
                            if (instruction.getOpcode() == INVOKEVIRTUAL){
                                if (((MethodInsnNode) instruction).owner.equals(Type.getInternalName(Entity.EntityClass))){
                                    if (((MethodInsnNode) instruction).name.equals(Entity.getPositionEyes.getName())){
                                        if (((MethodInsnNode) instruction).desc.equals(Type.getMethodDescriptor(Entity.getPositionEyes))){
                                            AbstractInsnNode astore = instruction.getNext();
                                            while (!(astore instanceof VarInsnNode)){
                                                astore = astore.getNext();
                                            }
                                            vec3 = ((VarInsnNode) astore).var;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    hook = new InsnList();
                    LabelNode L2 = new LabelNode();
                    LabelNode L3 = new LabelNode();
                    hook.add(new VarInsnNode(ALOAD,reach.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                    hook.add(new JumpInsnNode(IFEQ,L2));
                    //d1 = reach.getAttackRange()
                    hook.add(new VarInsnNode(ALOAD,reach.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Reach.class,"getAttackRange").getName(),"()F"));
                    hook.add(new InsnNode(F2D));
                    hook.add(new VarInsnNode(DSTORE,d1));
                    //final MovingObjectPosition movingObjectPosition = entity.rayTrace(d1, p_getMouseOver_1_);
                    /*LocalVariableNode movingObjectPosition = new LocalVariableNode("movingObjectPosition",Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass),null,new LabelNode(),new LabelNode(), method.maxLocals + 1);
                    method.localVariables.add(movingObjectPosition);
                    hook.add(new VarInsnNode(ALOAD,2));
                    hook.add(new VarInsnNode(DLOAD,d1));
                    hook.add(new VarInsnNode(FLOAD,1));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Entity.EntityClass),Entity.rayTrace.getName(),"(DF)L"+Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass)+";"));
                    hook.add(new VarInsnNode(ASTORE,movingObjectPosition.index));
                    //if(movingObjectPosition != null)
                    hook.add(new VarInsnNode(ALOAD,movingObjectPosition.index));
                    hook.add(new JumpInsnNode(IFNULL,L3));
                    //d1 = movingObjectPosition.hitVec.distanceTo(vec3);
                    hook.add(new VarInsnNode(ALOAD,movingObjectPosition.index));
                    hook.add(new FieldInsnNode(GETFIELD,Type.getInternalName(MovingObjectPosition.MovingObjectPositionClass),MovingObjectPosition.hitVec.getName(),Type.getDescriptor(Vec3.Vec3Class)));
                    hook.add(new VarInsnNode(ALOAD,vec3));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Vec3.Vec3Class),Vec3.distanceTo.getName(),Type.getMethodDescriptor(Vec3.distanceTo)));
                    hook.add(new VarInsnNode(DSTORE,d1));
                    hook.add(L3);
                    */
                    hook.add(L2);
                    method.instructions.insert(L6,hook);
                }
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
