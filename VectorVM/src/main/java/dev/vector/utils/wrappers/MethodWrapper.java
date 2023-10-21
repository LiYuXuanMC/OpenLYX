package dev.vector.utils.wrappers;


import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MethodWrapper {
    private MethodNode node;
    private ClassWrapper clazz;
    public MethodWrapper(ClassWrapper clazz, MethodNode node) {
        super();
        this.clazz = clazz;
        this.node = node;
    }

    public static List<MethodWrapper> wrap(ClassWrapper clazz){
        List<MethodWrapper> methodWrappers = new CopyOnWriteArrayList<>();
        for (MethodNode method : clazz.getClassNode().methods) {
            methodWrappers.add(new MethodWrapper(clazz,method));
        }
        return methodWrappers;
    }
    public String getDesc(){
        return node.desc;
    }
    public String getName(){
        return node.name;
    }
    public String getOwner(){
        return clazz.getClassName();
    }
    public boolean isPublic(){
        return (node.access & Opcodes.ACC_PUBLIC) != 0;
    }
    public boolean isPrivate(){
        return (node.access & Opcodes.ACC_PRIVATE) != 0;
    }
    public boolean isStatic(){
        return (node.access & Opcodes.ACC_STATIC) != 0;
    }

    public void setNode(MethodNode node) {
        this.node = node;
    }

    public String getFullName(){
        return clazz.getClassName() +"."+ node.name + node.desc;
    }
    public ClassWrapper getClazz() {
        return clazz;
    }

    public MethodNode getNode() {
        return node;
    }
}
