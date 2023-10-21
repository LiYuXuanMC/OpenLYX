package pub.ensemble.hillo.obf;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import net.minecraft.launchwrapper.IClassTransformer;
import pub.ensemble.hillo.utils.FileUtils;
import java.io.File;

public class EACJoinServerTransformer implements IClassTransformer {


    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService")){
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();

            cr.accept(cn,0);

            for (MethodNode method: cn.methods) {
                if (method.name.equals("joinServer")) {
                    InsnList hook = new InsnList();
                    hook.add(new VarInsnNode(Opcodes.ALOAD,1));
                    hook.add(new VarInsnNode(Opcodes.ALOAD,2));
                    hook.add(new VarInsnNode(Opcodes.ALOAD,3));
                    hook.add(new MethodInsnNode(Opcodes.INVOKESTATIC,"pub/ensemble/hillo/EACMain","joinServer","(Lcom/mojang/authlib/GameProfile;Ljava/lang/String;Ljava/lang/String;)V",false));
                    method.instructions.insert(hook);
                }
            }
            dump(cn,0);
        }
        return basicClass;
    }
    public static void dump(ClassNode cn, int flag) {
        ClassWriter cw = new ClassWriter(flag);
        cn.accept(cw);
        FileUtils.writeFile(new File("./"+cn.name.replace("/",".")+".class"),cw.toByteArray());
        System.out.println("Dump written "+"./"+cn.name.replace("/",".")+".class");
    }
}
