package dev.vector.transformers;

import dev.vector.Obfuscator;
import dev.vector.utils.wrappers.ClassWrapper;
import org.objectweb.asm.Opcodes;

import java.util.List;

public abstract class ClassTransformer implements Opcodes {
    public abstract void transform(Obfuscator deobfuscator, List<ClassWrapper> classes);
}
