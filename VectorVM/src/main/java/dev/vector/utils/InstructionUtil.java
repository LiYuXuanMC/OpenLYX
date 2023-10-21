package dev.vector.utils;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class InstructionUtil {
    public static AbstractInsnNode copy(AbstractInsnNode insnNode) {
        if (insnNode instanceof InsnNode){
            return new InsnNode(insnNode.getOpcode());
        }
        if (insnNode instanceof VarInsnNode){
            return new VarInsnNode(insnNode.getOpcode(),((VarInsnNode) insnNode).var);
        }
        if (insnNode instanceof MethodInsnNode){
            return new MethodInsnNode(insnNode.getOpcode(),((MethodInsnNode) insnNode).owner,((MethodInsnNode) insnNode).name,((MethodInsnNode) insnNode).desc,((MethodInsnNode) insnNode).itf);
        }
        return insnNode;
    }
}
