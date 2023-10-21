package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.EventType;
import al.logger.client.event.client.world.EventWorldEntityOperation;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class WorldClientTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return WorldClient.WorldClientClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(WorldClient.removeEntityFromWorld.getName()) && method.desc.equals(Type.getMethodDescriptor(WorldClient.removeEntityFromWorld))){
                LocalVariableNode eventEnt = new LocalVariableNode("eventEnt",Type.getDescriptor(EventWorldEntityOperation.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventEnt);
                InsnList hook = new InsnList();

                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventWorldEntityOperation.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Remove).getName(),Type.getDescriptor(EventType.class)));
                hook.add(new VarInsnNode(ILOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventWorldEntityOperation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EventType.class),Type.INT_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventEnt.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventEnt.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction.getOpcode() == RETURN){
                        method.instructions.insertBefore(instruction,hook);
                    }
                }
            }
            if (method.name.equals(WorldClient.addEntityToWorld.getName()) && method.desc.equals(Type.getMethodDescriptor(WorldClient.addEntityToWorld))){
                LocalVariableNode eventEnt = new LocalVariableNode("eventEnt",Type.getDescriptor(EventWorldEntityOperation.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventEnt);
                InsnList hook = new InsnList();

                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventWorldEntityOperation.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Add).getName(),Type.getDescriptor(EventType.class)));
                hook.add(new VarInsnNode(ILOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventWorldEntityOperation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EventType.class),Type.INT_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventEnt.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventEnt.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                method.instructions.insert(hook);
            }
        }
    }
}
