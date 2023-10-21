package com.reflectmc.reflect.wrapper.mapper.node;

import lombok.Getter;

public class Node {
    @Getter
    private String obfClassName;
    @Getter
    private String deobfClassName;
    public Node(String obfClassName,String deobfClassName){
        this.obfClassName = obfClassName;
        this.deobfClassName = deobfClassName;
    }
    public static Node wrap(String line){
        String[] s = line.split(" ");
        if (s.length > 0){
            if (s[0].equals("CL:")){
                //Class node
                if (s.length == 3){
                    return new ClassNode(s[2].replace("/","."),s[1].replace("/","."));
                }
            }
            if (s[0].equals("FD:")){
                if (s.length == 3){
                    String obf = s[2];
                    String deobf = s[1];
                    String obfFieldName = obf.split("/")[obf.split("/").length - 1];
                    String deobfFieldName = deobf.split("/")[deobf.split("/").length - 1];
                    String obfClassName = new StringBuffer(
                            new StringBuffer(obf).reverse().toString().replaceFirst(new StringBuffer("/"+obfFieldName).reverse().toString(),""))
                            .reverse().toString();
                    String deobfClassName = new StringBuffer(
                            new StringBuffer(deobf).reverse().toString().replaceFirst(new StringBuffer("/"+deobfFieldName).reverse().toString(),""))
                            .reverse().toString();
                    return new FieldNode(obfClassName.replace("/","."),deobfClassName.replace("/","."),obfFieldName,deobfFieldName);
                }
            }
            if (s[0].equals("MD:")){
                String obf = s[3];
                String deobf = s[1];
                String obfMethodName = obf.split("/")[obf.split("/").length - 1];
                String deobfMethodName = deobf.split("/")[deobf.split("/").length - 1];
                String obfClassName = new StringBuffer(
                        new StringBuffer(obf).reverse().toString().replaceFirst(new StringBuffer("/"+obfMethodName).reverse().toString(),""))
                        .reverse().toString();
                String deobfClassName = new StringBuffer(
                        new StringBuffer(deobf).reverse().toString().replaceFirst(new StringBuffer("/"+deobfMethodName).reverse().toString(),""))
                        .reverse().toString();
                Signature obfSignature = new Signature(s[4]);
                Signature deobfSignature = new Signature(s[2]);
                return new MethodNode(obfClassName.replace("/","."),deobfClassName.replace("/","."),obfMethodName,deobfMethodName,obfSignature,deobfSignature);
            }
        }
        return null;
    }
}
