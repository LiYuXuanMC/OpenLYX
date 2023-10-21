package dev.vector.utils.wrappers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldNode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FieldWrapper {
    private FieldNode node;
    private ClassWrapper clazz;
    public FieldWrapper(ClassWrapper clazz, FieldNode node) {
        super();
        this.clazz = clazz;
        this.node = node;
    }
    public static List<FieldWrapper> wrap(ClassWrapper clazz){
        List<FieldWrapper> fieldWrappers = new CopyOnWriteArrayList<>();
        for (FieldNode field : clazz.getClassNode().fields) {
            fieldWrappers.add(new FieldWrapper(clazz,field));
        }
        return fieldWrappers;
    }
    public String getDesc(){
        return node.desc;
    }
    public boolean isPublic(){
        return (node.access & Opcodes.ACC_PUBLIC) != 0;
    }
    public boolean isPrivate(){
        return (node.access & Opcodes.ACC_PRIVATE) != 0;
    }
    public boolean isProtected(){
        return (node.access & Opcodes.ACC_PROTECTED) != 0;
    }
    public String getOwner(){
        return clazz.getClassName();
    }
    public boolean isStatic(){
        return (node.access & Opcodes.ACC_STATIC) != 0;
    }
    public boolean isFinal(){
        return (node.access & Opcodes.ACC_FINAL) != 0;
    }
    public String getName(){
        return node.name;
    }
    public String getFullName(){
        return clazz.getClassName() +"."+ node.name;
    }
    public ClassWrapper getClazz() {
        return clazz;
    }

    public FieldNode getNode() {
        return node;
    }
}
