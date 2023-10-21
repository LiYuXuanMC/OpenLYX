package dev.vector.transformers;

import dev.vector.Obfuscator;
import dev.vector.utils.FileUtil;
import dev.vector.utils.wrappers.ClassWrapper;
import dev.vector.vm.*;

import java.util.List;

public class CopyClassTransformer extends ClassTransformer{
    @Override
    public void transform(Obfuscator deobfuscator, List<ClassWrapper> classes) {
        try {
            classes.add(new ClassWrapper(FileUtil.readStream(ClassTransformer.class.getClassLoader().getResource(VMClass.class.getName().replace(".","/")+".class").openStream())));
            classes.add(new ClassWrapper(FileUtil.readStream(ClassTransformer.class.getClassLoader().getResource(VMMethod.class.getName().replace(".","/")+".class").openStream())));
            classes.add(new ClassWrapper(FileUtil.readStream(ClassTransformer.class.getClassLoader().getResource(VMInstruction.class.getName().replace(".","/")+".class").openStream())));
            classes.add(new ClassWrapper(FileUtil.readStream(ClassTransformer.class.getClassLoader().getResource(ObjectContainer.class.getName().replace(".","/")+".class").openStream())));
            classes.add(new ClassWrapper(FileUtil.readStream(ClassTransformer.class.getClassLoader().getResource(VMPool.class.getName().replace(".","/")+".class").openStream())));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
