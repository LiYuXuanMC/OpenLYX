package dev.vector;

import dev.vector.transformers.ClassTransformer;
import dev.vector.utils.JarIO;
import dev.vector.utils.Profiler;
import dev.vector.utils.wrappers.ClassWrapper;
import dev.vector.utils.wrappers.ResourceWrapper;
import sun.misc.Launcher;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Obfuscator {
    private final List<ClassWrapper> libraries = new CopyOnWriteArrayList<>();
    private final List<ClassWrapper> classes = new CopyOnWriteArrayList<>();
    private final List<ClassTransformer> transformers = new ArrayList<>();
    private final List<ResourceWrapper> resources = new CopyOnWriteArrayList<>();
    private static Obfuscator instance;
    private final Configuration configuration;
    public Obfuscator(Configuration configuration) {
        instance = this;
        this.configuration = configuration;
        if (configuration.isUseBootstrapClassPath()){
            for (URL url : Launcher.getBootstrapClassPath().getURLs()) {
                try {
                    File file = new File(url.toURI().getPath());
                    if (file.isFile()){
                        JarIO jar = new JarIO(file);
                        inputLibrary(jar);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Obfuscator getInstance() {
        return instance;
    }
    public void addTransformers(ClassTransformer transformer){
        transformers.add(transformer);
    }
    public void runTransform(){
        Profiler profiler = new Profiler();
        for (ClassTransformer transformer : transformers) {
            try {
                transformer.transform(this,classes);
            }catch (Exception e){
                e.printStackTrace();
                if (configuration.isStopOnException()){
                    System.out.println("Deobfuscator stopped due to exception");
                    break;
                }
            }
        }
        System.out.println("Transform finish in "+profiler.end()+"ms");
    }
    public void inputLibrary(JarIO jar){
        System.out.println("Input library "+jar.getFile().getName());
        for (ClassWrapper clazz : jar.getClasses()) {
            clazz.setLibrary(true);
            libraries.add(clazz);
        }
    }
    public void input(JarIO jar){
        System.out.println("Input "+jar.getFile().getName());
        classes.addAll(jar.getClasses());
        resources.addAll(jar.getResources());
    }
    public ClassWrapper getLibraryClass(String name){
        for (ClassWrapper clazz : libraries) {
            if(clazz.getClassName().equals(name)){
                return clazz;
            }
        }
        return null;
    }
    public void save(File file){
        Profiler profiler = new Profiler();
        JarIO jar = new JarIO(file,classes,resources);
        jar.saveJar(configuration.isVerify());
        System.out.println("Save finish in "+profiler.end()+"ms");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ClassWrapper getClass(String name){
        for (ClassWrapper clazz : classes) {
            if(clazz.getClassName().equals(name)){
                return clazz;
            }
        }
        for (ClassWrapper clazz : libraries) {
            if(clazz.getClassName().equals(name)){
                return clazz;
            }
        }
        return null;
    }
    public boolean isAssignableFrom(String type1, String type2){
        if ("java/lang/Object".equals(type1))
            return true;
        if (type1.equals(type2))
            return true;

        List<String> allChildren = new ArrayList<>();
        allChildren.add(type1);
        boolean modified = true;
        while (modified){
            modified = false;
            List<String> add = new ArrayList<>();
            for (String allChild : allChildren) {
                ClassWrapper clazz = getClass(allChild);
                if (clazz != null){
                    for (String s : clazz.getInterfaces()) {
                        if (allChildren.stream().noneMatch(s::equals)){
                            add.add(s);
                            modified = true;
                        }
                    }
                    if (!allChildren.contains(clazz.getSuperName())){
                        add.add(clazz.getSuperName());
                        modified = true;
                    }
                }
            }
            allChildren.addAll(add);
        }
        return allChildren.contains(type2);
    }
    public List<ClassWrapper> getLibraries() {
        return libraries;
    }

    public List<ClassWrapper> getClasses() {
        return classes;
    }

}
