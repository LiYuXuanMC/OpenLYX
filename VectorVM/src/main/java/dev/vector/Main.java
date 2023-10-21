package dev.vector;

import dev.vector.transformers.CopyClassTransformer;
import dev.vector.transformers.VMTransformer;
import dev.vector.utils.JarIO;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setStopOnException(true);
        configuration.setDebug(true);
        configuration.setVerify(true);
        configuration.setInvokeVirtualize(false);
        Obfuscator obfuscator = new Obfuscator(configuration);
        obfuscator.input(new JarIO(new File("./TEST.jar")));
        obfuscator.addTransformers(new VMTransformer());
        obfuscator.addTransformers(new CopyClassTransformer());
        obfuscator.runTransform();
        obfuscator.save(new File("./output.jar"));
    }
}