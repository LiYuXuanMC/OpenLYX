package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Opcodes;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.utils.FileUtil;
import lombok.Getter;

import java.io.File;
import java.lang.reflect.Method;

public abstract class ClassTransformer implements Opcodes {
    public ClassTransformer(){
        ;
    }
    public abstract Class<?> getTargetClass();
    public abstract byte[] transform(byte[] classBytes);
    public void dump(ClassNode cn,int flag){
        ClassWriter cw = new ClassWriter(flag);
        cn.accept(cw);
        FileUtil.writeFile(new File("./"+cn.name.replace("/",".")+".class"),cw.toByteArray());
    }
    public AbstractInsnNode findMethodCall(Method method, InsnList insnNodes){
        for (AbstractInsnNode insnNode : insnNodes) {
            if (insnNode instanceof MethodInsnNode){
                if (((MethodInsnNode) insnNode).owner.equals(Type.getInternalName(method.getDeclaringClass()))){
                    if (((MethodInsnNode) insnNode).name.equals(method.getName())){
                        if (((MethodInsnNode) insnNode).desc.equals(Type.getMethodDescriptor(method))){
                            return insnNode;
                        }
                    }
                }
            }
        }
        return null;
    }
    public AbstractInsnNode findMethodCall(Method method, AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getNext();
        while (insnNode != null){
            if (insnNode instanceof MethodInsnNode){
                if (((MethodInsnNode) insnNode).owner.equals(Type.getInternalName(method.getDeclaringClass()))){
                    if (((MethodInsnNode) insnNode).name.equals(method.getName())){
                        if (((MethodInsnNode) insnNode).desc.equals(Type.getMethodDescriptor(method))){
                            return insnNode;
                        }
                    }
                }
            }
            insnNode = insnNode.getNext();
        }
        return null;
    }
    public AbstractInsnNode findVarOperate(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getNext();
        while (insnNode != null){
            if (insnNode instanceof VarInsnNode){
                return insnNode;
            }
            insnNode = insnNode.getNext();
        }
        return null;
    }
    public AbstractInsnNode findFSUB(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getNext();
        while (insnNode != null){
            if (insnNode instanceof InsnNode){
                if (insnNode.getOpcode() == FSUB){
                    return insnNode;
                }
            }
            insnNode = insnNode.getNext();
        }
        return null;
    }
    public AbstractInsnNode backFindFADD(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getNext();
        while (insnNode != null){
            if (insnNode instanceof InsnNode){
                if (insnNode.getOpcode() == FADD){
                    return insnNode;
                }
            }
            insnNode = insnNode.getPrevious();
        }
        return null;
    }
}
