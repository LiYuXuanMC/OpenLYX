package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.gui.GuiIngame;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class GuiIngameTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return GuiIngame.GuiIngameClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (EnvironmentDetector.getModifier() != Environment.Forge)
            if (method.name.equals(GuiIngame.renderTooltip.getName()) &&
                    method.desc.equals(Type.getMethodDescriptor(GuiIngame.renderTooltip))){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender2D.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(FLOAD,2));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(F)V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }

        }
    }
}
