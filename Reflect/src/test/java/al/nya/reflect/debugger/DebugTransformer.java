package al.nya.reflect.debugger;

import al.nya.reflect.transform.TransformManager;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class DebugTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming class: " + className + " null:" + (classBeingRedefined == null));
        return TransformManager.onTransform(classBeingRedefined, classfileBuffer);
    }
}
