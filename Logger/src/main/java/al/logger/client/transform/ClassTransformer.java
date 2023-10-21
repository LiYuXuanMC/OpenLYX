package al.logger.client.transform;

import al.logger.client.utils.FileUtils;
import al.logger.libs.asm.ClassReader;
import al.logger.libs.asm.ClassWriter;
import al.logger.libs.asm.Opcodes;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;

import java.io.File;
import java.lang.reflect.Method;

public abstract class ClassTransformer implements Opcodes {

    private int flags = ClassWriter.COMPUTE_FRAMES;

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFlags() {
        return this.flags;
    }

    public ClassTransformer() {

    }
    public abstract Class<?> getTargetClass();

    public abstract void transformClass(ClassNode cn);

    public byte[] callTransformClass(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        this.transformClass(cn);
        ClassWriter cw = new ClassWriter(this.flags);
        cn.accept(cw);
        return cw.toByteArray();
    }
    public static void dump(ClassNode cn, int flag) {
        ClassWriter cw = new ClassWriter(flag);
        cn.accept(cw);
        FileUtils.writeFile(new File("./"+cn.name.replace("/",".")+".class"),cw.toByteArray());
        System.out.println("Dump written "+"./"+cn.name.replace("/",".")+".class");
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
    public AbstractInsnNode findMethodCall(String owner,String name,String desc, InsnList insnNodes){
        for (AbstractInsnNode insnNode : insnNodes) {
            if (insnNode instanceof MethodInsnNode){
                if (((MethodInsnNode) insnNode).owner.equals(owner)){
                    if (((MethodInsnNode) insnNode).name.equals(name)){
                        if (((MethodInsnNode) insnNode).desc.equals(desc)){
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
    public AbstractInsnNode findGOTO(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getNext();
        while (insnNode != null){
            if (insnNode instanceof AbstractInsnNode){
                if (insnNode.getOpcode() == GOTO){
                    return insnNode;
                }
            }
            insnNode = insnNode.getNext();
        }
        return null;
    }
    public AbstractInsnNode backFindFLOAD(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getPrevious();
        while (insnNode != null){
            if (insnNode instanceof AbstractInsnNode){
                if (insnNode.getOpcode() == FLOAD){
                    return insnNode;
                }
            }
            insnNode = insnNode.getPrevious();
        }
        return null;
    }
    public AbstractInsnNode backFindALOAD(AbstractInsnNode provide){
        AbstractInsnNode insnNode = provide.getPrevious();
        while (insnNode != null){
            if (insnNode instanceof AbstractInsnNode){
                if (insnNode.getOpcode() == ALOAD){
                    return insnNode;
                }
            }
            insnNode = insnNode.getPrevious();
        }
        return null;
    }
    public AbstractInsnNode backFindALOAD(AbstractInsnNode provide,int index){
        AbstractInsnNode insnNode = provide.getPrevious();
        while (insnNode != null){
            if (insnNode instanceof VarInsnNode){
                if (insnNode.getOpcode() == ALOAD && ((VarInsnNode) insnNode).var == index){
                    return insnNode;
                }
            }
            insnNode = insnNode.getPrevious();
        }
        return null;
    }
}
