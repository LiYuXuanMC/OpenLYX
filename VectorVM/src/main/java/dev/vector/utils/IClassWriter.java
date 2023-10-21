package dev.vector.utils;

import dev.vector.Obfuscator;
import dev.vector.utils.wrappers.ClassWrapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class IClassWriter extends ClassWriter {
    public IClassWriter(int flags) {
        super(flags);
    }

    public IClassWriter(ClassReader classReader, int flags) {
        super(classReader, flags);
    }
    @Override
    protected String getCommonSuperClass(final String type1, final String type2) {
        Obfuscator obfuscator = Obfuscator.getInstance();
        if ("java/lang/Object".equals(type1) || "java/lang/Object".equals(type2))
            return "java/lang/Object";

        String first = deriveCommonSuperName(type1, type2,obfuscator);
        String second = deriveCommonSuperName(type2, type1,obfuscator);
        if (!"java/lang/Object".equals(first))
            return first;

        if (!"java/lang/Object".equals(second))
            return second;

        return getCommonSuperClass(obfuscator.getClass(type1).getSuperName(), obfuscator.getClass(type2).getSuperName());
    }


    private String deriveCommonSuperName(final String type1, final String type2, Obfuscator obfuscator) {
        ClassWrapper first = obfuscator.getClass(type1);
        ClassWrapper second = obfuscator.getClass(type2);
        if (obfuscator.isAssignableFrom(type1, type2))
            return type1;
        else if (obfuscator.isAssignableFrom(type2, type1))
            return type2;
        else if (first.isInterface() || second.isInterface())
            return "java/lang/Object";
        else {
            String temp;

            do {
                temp = first.getSuperName();
                first = obfuscator.getClass(temp);
            } while (!obfuscator.isAssignableFrom(temp, type2));
            return temp;
        }
    }
}
