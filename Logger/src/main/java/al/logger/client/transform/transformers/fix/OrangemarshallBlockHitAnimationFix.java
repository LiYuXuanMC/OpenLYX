package al.logger.client.transform.transformers.fix;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.Visual.BlockAnimation;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class OrangemarshallBlockHitAnimationFix extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EnvironmentDetector.getOrangemarshallBlockHitAnimation();
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals("onRenderFirstHand") && method.desc.equals("(Lnet/minecraftforge/client/event/RenderHandEvent;)V")){
                LocalVariableNode blockAnimation = new LocalVariableNode("ba", Type.getDescriptor(BlockAnimation.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                hook.add(new LdcInsnNode(Type.getType(BlockAnimation.class)));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(BlockAnimation.class)));
                hook.add(new VarInsnNode(ASTORE,blockAnimation.index));
                hook.add(new VarInsnNode(ALOAD,blockAnimation.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(BlockAnimation.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
        }
    }
}
