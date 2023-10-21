package com.reflectmc.reflect.wrapper.wrappers.cactus;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.tree.ClassNode;
import com.reflectmc.libraries.asm.tree.FieldNode;
import com.reflectmc.libraries.asm.tree.MethodNode;
import com.reflectmc.reflect.wrapper.wrappers.cactus.utils.MethodUtil;

import java.util.ArrayList;
import java.util.List;

public class Cactus {
    private ClassNode deobfClass;
    private ClassNode originClass;
    private String deobfConfigure;
    public Cactus(){

    }
    public byte[] simpleDeobfuscation(byte[] classBytes,String map){
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (String line : map.split("\r\n")) {
            String[] mapNode = line.split(" ");
            if (mapNode[0].equals("MD")){
                for (MethodNode method : cn.methods) {
                    if (method.name.equals(mapNode[1]) && method.desc.equals(mapNode[2])){
                        System.out.println(method.name +" -> "+mapNode[3] +" "+ method.desc);
                        method.name = mapNode[3];
                    }
                }
            }
            if (mapNode[0].equals("FD")){
                for (FieldNode field : cn.fields) {
                    if (field.name.equals(mapNode[1])){
                        field.name = mapNode[2];
                    }
                }
            }
        }
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        return cw.toByteArray();
    }
    public void input(byte[] deobfClassByte,byte[] originClassByte,String deobfConfigure){
        ClassReader cr = new ClassReader(deobfClassByte);
        deobfClass = new ClassNode();
        cr.accept(deobfClass,0);
        cr = new ClassReader(originClassByte);
        originClass = new ClassNode();
        cr.accept(originClass,0);
        this.deobfConfigure = deobfConfigure;
    }
    public DeobfuscationResult run(){
        List<DeobfuscationResult.DeobfuscationItem> items = new ArrayList<>();
        for (String line : deobfConfigure.split("\r\n")) {
            String[] node = line.split(" ");
            if (node[0].equals("m")){
                DeobfuscationResult.DeobfuscationItem item;
                try {
                    item = deobfMethod(node[1],node[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new DeobfuscationResult(DeobfuscationResult.Status.Fail,e.getMessage(),null);
                }
                items.add(item);
            }
        }
        return new DeobfuscationResult(DeobfuscationResult.Status.Success,"",items);
    }
    private DeobfuscationResult.DeobfuscationItem deobfMethod(String deobf,String orgDesc) throws Exception {
        //Compare desc
        List<MethodNode> descHit = new ArrayList<>();
        for (MethodNode method : originClass.methods) {
            if (method.desc.equals(orgDesc)){
                descHit.add(method);
            }
        }
        if (descHit.size() == 0){
            throw new Exception("Unable to find same signature in origin class : "+orgDesc);
        }
        MethodNode deobfDescHit = null;
        for (MethodNode method : deobfClass.methods) {
            if (method.name.equals(deobf) && method.desc.equals(orgDesc)){
                deobfDescHit = method;
            }
        }
        if (deobfDescHit == null){
            throw new Exception("Unable to find method in deobfuscated class : "+deobf+"("+orgDesc+")");
        }
        if (descHit.size() == 1){
            System.out.println(deobf +" -> "+descHit.get(0).name);
            return new DeobfuscationResult.DeobfuscationItem(DeobfuscationResult.DeobfuscationItem.Type.Method,descHit.get(0),deobfDescHit);
        }
        System.out.println("[!]"+descHit.size()+" methods have same signature");
        for (MethodNode methodNode : descHit) {
            System.out.println("[+]"+methodNode.name+" "+methodNode.desc);
        }
        //Multi desc hit
        float highestSimilarity = 0f;
        MethodNode highestSimilarityMethod = null;
        String deobfHash = MethodUtil.getHash(MethodUtil.methodToBytes(deobfDescHit));
        System.out.println("Deobf method hash "+deobfHash);
        for (MethodNode methodNode : descHit) {
            String obfHash = MethodUtil.getHash(MethodUtil.methodToBytes(methodNode));
            System.out.println("Comparing "+methodNode.name+" "+methodNode.desc+" to "+deobfDescHit.name+" "+deobfDescHit.desc);
            System.out.println("Hash " + obfHash);
            float similarity = MethodUtil.getSimilarityRatio(deobfHash,obfHash);
            System.out.println("Similarity "+similarity+"%");
            if (similarity > highestSimilarity){
                highestSimilarity = similarity;
                highestSimilarityMethod = methodNode;
            }
        }
        System.out.println(highestSimilarityMethod.name+" "+highestSimilarityMethod.desc+" maybe the obfuscated method");
        return new DeobfuscationResult.DeobfuscationItem(DeobfuscationResult.DeobfuscationItem.Type.Method,highestSimilarityMethod,deobfDescHit);
    }
}
