package com.reflectmc.reflect.bridge.transformers;

import com.reflectmc.reflect.bridge.BridgeBuildInfo;
import com.reflectmc.reflect.transform.transformers.ClassTransformer;
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
    public byte[] transform(byte[] classBytes) {
        target.setBytes(classBytes);
        new Thread(() -> {
            hook.accept(target);
        }).start();
        return classBytes;
    }
}
