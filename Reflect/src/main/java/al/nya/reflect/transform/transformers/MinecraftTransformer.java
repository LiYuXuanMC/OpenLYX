package al.nya.reflect.transform.transformers;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.events.events.EventKey;
import al.nya.reflect.events.events.EventLoop;
import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import by.radioegor146.nativeobfuscator.Native;

public class MinecraftTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Minecraft.MinecraftClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(Minecraft.runTick.getName()) && method.desc.equals("()V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList runTick = new InsnList();
                runTick.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                runTick.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                runTick.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventTick.class)));
                runTick.add(new InsnNode(Opcodes.DUP));
                runTick.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventTick.class),"<init>","()V"));
                runTick.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                insnList.insert(runTick);
                method.instructions = insnList;
            }
            if (method.name.equals(Minecraft.dispatchKeypresses.getName())&& method.desc.equals("()V")){
                InsnList insnList = method.instructions;
                InsnList eventKey = new InsnList();
                eventKey.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                eventKey.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                eventKey.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventKey.class)));
                eventKey.add(new InsnNode(Opcodes.DUP));
                eventKey.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventKey.class),"<init>","()V"));
                eventKey.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                insnList.insert(eventKey);
                method.instructions = insnList;
            }
            if(method.name.equals(Minecraft.runGameLoop.getName())&& method.desc.equals("()V")){
                InsnList insnList = method.instructions;
                InsnList eventLoop = new InsnList();
                eventLoop.add(new FieldInsnNode(Opcodes.GETSTATIC,Type.getInternalName(Reflect.class),"Instance","L"+Type.getInternalName(Reflect.class)+";"));
                eventLoop.add(new FieldInsnNode(Opcodes.GETFIELD,Type.getInternalName(Reflect.class),"eventBus","L"+Type.getInternalName(EventBus.class)+";"));
                eventLoop.add(new TypeInsnNode(Opcodes.NEW,Type.getInternalName(EventLoop.class)));
                eventLoop.add(new InsnNode(Opcodes.DUP));
                eventLoop.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,Type.getInternalName(EventLoop.class),"<init>","()V"));
                eventLoop.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventBus.class),"callEvent","(L"+Type.getInternalName(Event.class)+";)V"));
                insnList.insert(eventLoop);
                method.instructions = insnList;
            }
            if(method.name.equals(Minecraft.loadWorld.getName())&& method.desc.equals("(L"+Type.getInternalName(WorldClient.WorldClientClass)+";)V")){
                InsnList insnList = method.instructions;
                InsnList loadWorld = new InsnList();
                loadWorld.add(new VarInsnNode(Opcodes.ALOAD,1));
                loadWorld.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"loadWorld","(Ljava/lang/Object;)V"));
                insnList.insert(loadWorld);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS+ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
