package dev.qingwan.crater.builder;

import dev.qingwan.crater.Log;
import dev.qingwan.crater.annotation.CraterVM;
import lombok.SneakyThrows;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ProcessProvider {
    private Map<String,byte[]> classes = new HashMap<>();
    private File file;
    public ProcessProvider(File file){
        if (!file.exists()){
            Log.error("File "+file.getAbsolutePath()+" not exists");
            throw new RuntimeException();
        }
        this.file = file;
        processJar(file);
    }
    @SneakyThrows
    private void processJar(File file){
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry ze = null;
        while ((ze = zis.getNextEntry()) != null){
            if (!ze.getName().endsWith(".class"))
                continue;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (zis.available() != 0){
                bos.write(zis.read());
            }
            byte[] bytes = bos.toByteArray();
            classes.put(ze.getName().replace(".class",""),bytes);
            Log.info("Read class "+ze.getName().replace(".class","")+" "+bytes.length+" bytes");
        }
        zis.close();
    }
    public void addClass(String name,byte[] bytes){
        classes.put(name,bytes);
    }
    public void doObf(){
        Map<String,String> classesNeedVM = new HashMap<>();
        classes.forEach((name,bytes) -> {
            ClassReader cr = new ClassReader(bytes);
            ClassNode cn = new ClassNode();
            cr.accept(cn,0);
            if (cn.visibleAnnotations != null){
                for (AnnotationNode visibleAnnotation : cn.visibleAnnotations) {
                    if (visibleAnnotation.desc.equals(Type.getDescriptor(CraterVM.class))){
                        classesNeedVM.put(name,visibleAnnotation.values.get(1).toString());
                    }
                }
            }
        });
        for (Map.Entry<String, String> entry : classesNeedVM.entrySet()) {
            Log.info("Create VM for class "+entry.getKey()+" args:"+entry.getValue());
            VMBuilder builder = new VMBuilder(classes.get(entry.getKey()),entry.getValue(),this);
            classes.replace(entry.getKey(),builder.buildVM());
        }
    }
    @SneakyThrows
    public void save(){
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(file.getAbsolutePath().replace(".jar", ".vm.jar"))));
        for (Map.Entry<String, byte[]> entry : classes.entrySet()) {
            zos.putNextEntry(new ZipEntry(entry.getKey()+".class"));
            zos.write(entry.getValue());
            Log.info("Write "+entry.getKey());
        }
        zos.close();
    }
}
