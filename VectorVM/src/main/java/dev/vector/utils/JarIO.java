package dev.vector.utils;

import dev.vector.utils.wrappers.ClassWrapper;
import dev.vector.utils.wrappers.ResourceWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class JarIO {
    private final File jar;
    private List<ClassWrapper> classes = new ArrayList<>();
    private List<ResourceWrapper> resources = new ArrayList<>();

    public JarIO(File jar){
        this.jar = jar;
        readJar();
    }
    public JarIO(File jar, List<ClassWrapper> classes, List<ResourceWrapper> resources){
        this.jar = jar;
        this.classes = classes;
        this.resources = resources;
    }
    private void readJar(){
        try {
            Profiler profiler = new Profiler();
            ZipInputStream zis = new ZipInputStream(new FileInputStream(jar));
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory())
                    continue;
                int len;
                byte[] buffer = new byte[2048];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = zis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                if (entry.getName().endsWith(".class")) {
                    classes.add(new ClassWrapper(bos.toByteArray()));
                } else {
                    resources.add(new ResourceWrapper(bos.toByteArray(),entry.getName()));
                }
            }
            System.out.println("Read jar finish in "+profiler.end()+"ms "+classes.size()+" classes "+resources.size()+" resources");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return jar;
    }

    public void saveJar(boolean verify){
        try {
            Profiler profiler = new Profiler();
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(jar));
            for (ClassWrapper clazz : classes) {
                zos.putNextEntry(new ZipEntry(clazz.getClassName() + ".class"));
                zos.write(clazz.getClassBytes(verify));
            }
            for (ResourceWrapper resource : resources) {
                zos.putNextEntry(new ZipEntry(resource.getName()));
                zos.write(resource.getBytes());
            }
            zos.close();
            System.out.println("Save jar finish in "+profiler.end()+"ms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResourceWrapper> getResources() {
        return resources;
    }

    public List<ClassWrapper> getClasses() {
        return classes;
    }
    public void addClass(ClassWrapper classWrapper){
        classes.add(classWrapper);
    }
    public void removeClass(ClassWrapper classWrapper){
        classes.remove(classWrapper);
    }
}
