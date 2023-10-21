package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import by.radioegor146.nativeobfuscator.Native;

public class PlayerControllerMPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return PlayerControllerMP.PlayerControllerMPClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals(PlayerControllerMP.getBlockReachDistance.getName()) && method.desc.equals("()F")){
                InsnList reach = new InsnList();
                reach.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"getReachRange","()F"));
                reach.add(new InsnNode(Opcodes.FRETURN));
                method.instructions = reach;
            }
            if (method.name.equals(PlayerControllerMP.attackEntity.getName()) &&
                    method.desc.equals("(L"+Type.getInternalName(EntityPlayer.EntityPlayerClass)+";L"+Type.getInternalName(Entity.EntityClass)+";)V")){
                InsnList insnList = method.instructions;
                InsnList attack = new InsnList();
                //    ALOAD 1
                //    ALOAD 2
                //    INVOKESTATIC al/nya/reflect/events/EventBus.attack (Lal/nya/reflect/wrapper/wraps/wrapper/entity/EntityPlayer;Lal/nya/reflect/wrapper/wraps/wrapper/entity/Entity;)V
                attack.add(new VarInsnNode(Opcodes.ALOAD,1));
                attack.add(new VarInsnNode(Opcodes.ALOAD,2));
                attack.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),
                        "attack","(Ljava/lang/Object;Ljava/lang/Object;)V"));
                insnList.insert(attack);
                method.instructions = insnList;
            }
            if (method.name.equals(PlayerControllerMP.windowClick.getName()) &&
                    method.desc.equals("(IIIIL"+Type.getInternalName(EntityPlayer.EntityPlayerClass)+";)V")){
                InsnList insnList = method.instructions;
                InsnList windowClick = new InsnList();
                windowClick.add(new VarInsnNode(Opcodes.ILOAD,1));
                windowClick.add(new VarInsnNode(Opcodes.ILOAD,2));
                windowClick.add(new VarInsnNode(Opcodes.ILOAD,3));
                windowClick.add(new VarInsnNode(Opcodes.ILOAD,4));
                windowClick.add(new VarInsnNode(Opcodes.ALOAD,5));
                windowClick.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"windowClick","(IIIILjava/lang/Object;)V"));
                insnList.insert(windowClick);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
