package al.logger.client.bridge.transformers;

import al.logger.client.bridge.BridgeBuildInfo;
import al.logger.client.transform.ClassTransformer;
import al.logger.libs.asm.ClassWriter;
import al.logger.libs.asm.tree.ClassNode;
import lombok.Getter;

import java.util.function.Consumer;

public class BridgeGetDataTransformer extends ClassTransformer {
    @Getter
    private final BridgeBuildInfo target;
    private final Consumer<BridgeBuildInfo> hook;
    public BridgeGetDataTransformer(BridgeBuildInfo target,Consumer<BridgeBuildInfo> hook){
        this.target = target;
        this.hook = hook;
    }
    @Override
    public Class<?> getTargetClass() {
        return target.getBridge();
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        byte[] classBytes = null;
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        classBytes = cw.toByteArray();
        target.setBytes(classBytes);
        new Thread(() -> {
            hook.accept(target);
        }).start();
    }
}
