package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.World.InputFix;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Opcodes;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;

@Native
public class GuiScreenTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiScreen.GuiScreenClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(GuiScreen.handleKeyboardInput.getName()) && method.desc.equals("()V")
                    && Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla},EnvironmentDetector.getMinecraft())) {
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                LocalVariableNode inputFix = new LocalVariableNode("inputFix", Type.getDescriptor(InputFix.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                hook.add(new LdcInsnNode(Type.getType(InputFix.class)));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(InputFix.class)));
                hook.add(new VarInsnNode(ASTORE,inputFix.index));
                hook.add(new VarInsnNode(ALOAD,inputFix.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(InputFix.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                hook.add(new JumpInsnNode(Opcodes.IFEQ, L1));
                hook.add(new VarInsnNode(ALOAD,inputFix.index));//Input fix
                hook.add(new VarInsnNode(ALOAD,0));//Screen
                hook.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(InputFix.class),ObfuscateHelper.targetObfuscatedMethod(InputFix.class,"inputFix").getName(), "(L" + Type.getInternalName(Object.class) + ";)V"));
                hook.add(new InsnNode(Opcodes.RETURN));
                hook.add(L1);

                method.instructions.insert(hook);
            }

        }
    }
}
