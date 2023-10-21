package com.reflectmc.reflect.transform.transformers;

import lombok.Getter;

public class ClassDumpTransformer extends ClassTransformer{
    @Getter
    private Class<?> target;
    @Getter
    private byte[] classBytes;
    public ClassDumpTransformer(Class<?> target){
        this.target = target;
    }
    @Override
    public Class<?> getTargetClass() {
        return target;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        this.classBytes = classBytes;
        return classBytes;
    }
}
