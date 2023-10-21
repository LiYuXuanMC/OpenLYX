package al.logger.client.transform.transformers;

import al.logger.client.transform.ClassTransformer;
import al.logger.client.transform.OneTimeTransformer;
import al.logger.libs.asm.ClassReader;
import al.logger.libs.asm.tree.ClassNode;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;

@Native
public class ClassReloadTransformer extends OneTimeTransformer {
    @Getter
    private Class targetClass;
    private byte[] classBytes;
    public ClassReloadTransformer(byte[] classByte) {
        this.classBytes = classByte;
        ClassReader classReader = new ClassReader(classByte);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode,0);
        try {
            targetClass = Class.forName(classNode.name.replace("/","."));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }
    @Override
    public byte[] callTransformClass(byte[] classBytes) {
        return this.classBytes;
    }
    @Override
    public void transformClass(ClassNode cn) {

    }
}
