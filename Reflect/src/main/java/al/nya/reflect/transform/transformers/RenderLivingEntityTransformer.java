package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.entity.RenderLivingEntity;


import java.util.ArrayList;
import java.util.List;

public class RenderLivingEntityTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return RenderLivingEntity.RenderLivingEntityClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            //You should use EntityLivingBase as arg,Entity is Synthetic method
            if (method.name.equals(RenderLivingEntity.doRender.getName()) && method.desc.equals("(L"+ Type.getInternalName(EntityLivingBase.EntityLivingBaseClass)+";DDDFF)V")) {
                InsnList pre = new InsnList();
                LabelNode L0 = new LabelNode();
                pre.add(new VarInsnNode(ALOAD, 1));
                pre.add(new VarInsnNode(ALOAD, 0));
                pre.add(new VarInsnNode(DLOAD, 2));
                pre.add(new VarInsnNode(DLOAD, 4));
                pre.add(new VarInsnNode(DLOAD, 6));
                pre.add(new VarInsnNode(FLOAD, 8));
                pre.add(new VarInsnNode(FLOAD, 9));
                pre.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(EventBus.class), "onPreRenderLiving", "(Ljava/lang/Object;Ljava/lang/Object;DDDFF)Z"));
                pre.add(new JumpInsnNode(IFEQ,L0));
                pre.add(new InsnNode(RETURN));
                pre.add(L0);
                InsnList post = new InsnList();
                post.add(new VarInsnNode(ALOAD, 1));
                post.add(new VarInsnNode(ALOAD, 0));
                post.add(new VarInsnNode(DLOAD, 2));
                post.add(new VarInsnNode(DLOAD, 4));
                post.add(new VarInsnNode(DLOAD, 6));
                post.add(new VarInsnNode(FLOAD, 8));
                post.add(new VarInsnNode(FLOAD, 9));
                post.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(EventBus.class), "onPostRenderLiving", "(Ljava/lang/Object;Ljava/lang/Object;DDDFF)V"));
                method.instructions.insert(pre);
                method.instructions.insertBefore(method.instructions.getLast().getPrevious(),post);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
