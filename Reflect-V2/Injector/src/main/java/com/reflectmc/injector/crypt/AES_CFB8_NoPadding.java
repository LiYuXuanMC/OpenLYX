package com.reflectmc.injector.crypt;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES_CFB8_NoPadding {
    private final Cipher cipher;
    private final SecretKey keySpec;
    private final IvParameterSpec ivSpec;
    private final Charset CHARSET = StandardCharsets.UTF_8; // ISO-8859-1 vs. UTF-8
    public AES_CFB8_NoPadding(String secretKey, byte[] iv){
        keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET), "AES");
        ivSpec = new IvParameterSpec(iv);
        try {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new SecurityException(e);
        }
    }
    public AES_CFB8_NoPadding(String secretKey){
        keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET), "AES");
        ivSpec = new IvParameterSpec(new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
        try {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new SecurityException(e);
        }
    }
    public byte[] decrypt(byte[] bytes) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }
    public byte[] encrypt(byte[] bytes) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }
}
