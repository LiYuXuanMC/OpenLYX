package dev.vector.utils.wrappers;

import dev.vector.utils.IClassWriter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;

public class ClassWrapper {
    private ClassNode classNode;
    private String originClassName;
    private List<MethodWrapper> methods;
    private List<FieldWrapper> fields;
    private boolean isLibrary = false;
    public ClassWrapper(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        this.classNode = new ClassNode();
        cr.accept(classNode,0);
        this.originClassName = classNode.name;
        wrap();
    }
    public void wrap(){
        this.methods = MethodWrapper.wrap(this);
        this.fields = FieldWrapper.wrap(this);
    }
    public boolean hasMethod(String name, String desc){
        for (MethodWrapper method : methods) {
            if(method.getName().equals(name) && method.getDesc().equals(desc)){
                return true;
            }
        }
        return false;
    }
    public void setClassNode(ClassNode classNode) {
        this.classNode = classNode;
    }
    public MethodWrapper getMethod(String name, String desc){
        for (MethodWrapper method : methods) {
            if(method.getName().equals(name) && method.getDesc().equals(desc)){
                return method;
            }
        }
        return null;
    }
    public FieldWrapper getField(String name, String desc){
        for (FieldWrapper field : fields) {
            if(field.getName().equals(name) && field.getDesc().equals(desc)){
                return field;
            }
        }
        return null;
    }
    public List<FieldWrapper> getFields() {
        return fields;
    }
    public List<MethodWrapper> getMethods() {
        return methods;
    }
    public void setLibrary(boolean library) {
        isLibrary = library;
    }
    public List<String> getInterfaces(){
        return classNode.interfaces;
    }
    public boolean isLibrary() {
        return isLibrary;
    }
    public String getSuperName(){
        return classNode.superName;
    }
    public String getClassName(){
        return classNode.name;
    }
    public String getOriginClassName(){
        return originClassName;
    }

    public ClassNode getClassNode() {
        return classNode;
    }

    public boolean isInterface(){
        return (classNode.access & Opcodes.ACC_INTERFACE) != 0;
    }
    public byte[] getClassBytes(boolean verify){
        ClassWriter cw = new IClassWriter(verify ? ClassWriter.COMPUTE_FRAMES : 0);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
