package al.nya.reflect.socket;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Cryptor_AES_CFB8_NoPadding {
    private final Cipher cipher;
    private final SecretKey keySpec;
    private final IvParameterSpec ivSpec;
    private final Charset CHARSET = StandardCharsets.UTF_8; // ISO-8859-1 vs. UTF-8
    public Cryptor_AES_CFB8_NoPadding(String secretKey, byte[] iv){
        keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET), "AES");
        ivSpec = new IvParameterSpec(iv);
        try {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new SecurityException(e);
        }
    }
    public Cryptor_AES_CFB8_NoPadding(String secretKey){
        keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET), "AES");
        ivSpec = new IvParameterSpec(new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
        try {
            cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new SecurityException(e);
        }
    }
    public String decrypt(String input){

        try {
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return new String(Base64.getDecoder().decode(cipher.doFinal(Base64.getDecoder().decode(input.getBytes(CHARSET)))), CHARSET).trim();
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    public String encrypt(String input){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return new String(Base64.getEncoder().encode(cipher.doFinal(Base64.getEncoder().encode(input.getBytes(CHARSET)))), CHARSET).trim();
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}
