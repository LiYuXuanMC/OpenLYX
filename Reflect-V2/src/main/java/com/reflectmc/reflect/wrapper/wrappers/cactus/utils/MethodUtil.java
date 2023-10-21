package com.reflectmc.reflect.wrapper.wrappers.cactus.utils;

import com.reflectmc.libraries.asm.tree.AbstractInsnNode;
import com.reflectmc.libraries.asm.tree.FieldInsnNode;
import com.reflectmc.libraries.asm.tree.MethodInsnNode;
import com.reflectmc.libraries.asm.tree.MethodNode;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class MethodUtil {
    @SneakyThrows
    public static byte[] methodToBytes(MethodNode methodNode){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for (AbstractInsnNode instruction : methodNode.instructions) {
            if (instruction.getOpcode() != -1){
                bos.write(instruction.getOpcode());
                if (instruction instanceof MethodInsnNode){
                    bos.write(((MethodInsnNode) instruction).owner.getBytes(StandardCharsets.UTF_8));
                    bos.write(((MethodInsnNode) instruction).name.getBytes(StandardCharsets.UTF_8));
                    bos.write(((MethodInsnNode) instruction).desc.getBytes(StandardCharsets.UTF_8));
                }
                if (instruction instanceof FieldInsnNode){
                    bos.write(((FieldInsnNode) instruction).owner.getBytes(StandardCharsets.UTF_8));
                    bos.write(((FieldInsnNode) instruction).name.getBytes(StandardCharsets.UTF_8));
                    bos.write(((FieldInsnNode) instruction).desc.getBytes(StandardCharsets.UTF_8));
                }
            }
        }
        return bos.toByteArray();
    }
    public static String getHash(byte[] bytes){
        return new String(bytes);
    }
    public static float getSimilarityRatio(String str, String target) {
        int d[][];
        int n = str.length();
        int m = target.length();
        int i;
        int j;
        char ch1;
        char ch2;
        int temp;
        if (n == 0 || m == 0) {
            return 0;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            ch1 = str.charAt(i - 1);
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {

                    temp = 0;
                } else {
                    temp = 1;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + temp);
            }
        }
        return (1 - (float) d[n][m] / Math.max(str.length(), target.length())) * 100F;
    }
}
