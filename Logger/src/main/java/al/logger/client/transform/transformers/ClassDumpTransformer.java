package al.logger.client.transform.transformers;

import al.logger.client.transform.ClassTransformer;
import al.logger.client.transform.OneTimeTransformer;
import al.logger.libs.asm.tree.ClassNode;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;

@Native
public class ClassDumpTransformer extends OneTimeTransformer {
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
    public void transformClass(ClassNode cn) {

    }
    @Override
    public byte[] callTransformClass(byte[] classBytes){
        this.classBytes = classBytes;
        System.out.println("Dumping class " + target.getCanonicalName());
        return classBytes;
    }
}
