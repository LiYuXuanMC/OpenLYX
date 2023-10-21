package al.nya.reflect.transform;


import al.nya.reflect.libraries.reflectasm.Opcodes;

public abstract class ClassTransformer implements Opcodes {
    public abstract Class<?> getTargetClass();

    public abstract byte[] transform(byte[] bytes);
}
