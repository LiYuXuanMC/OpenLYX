package dev.qingwan.crater.vm;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

public class VMManagement {
    @SneakyThrows
    public static VirtualMachine allocVM(byte[] bytes){
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        byte[] keyBytes = new byte[16];
        buffer.get(keyBytes);
        SecretKey key = new SecretKeySpec(bytes, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] vmByte = buffer.array();
        byte[] decrypt = cipher.doFinal(vmByte);
        ByteBuffer classBuffer = ByteBuffer.wrap(decrypt);
        String className = null;
        byte[] classByte = null;
        String SHA256 = null;
        //Read TLV001
        byte[] tlv001 = new byte[classBuffer.getInt()];
        classBuffer.get(tlv001);
        className = new String(tlv001);
        //Read TLV002
        byte[] tlv002 = new byte[classBuffer.getInt()];
        classBuffer.get(tlv002);
        classByte = tlv002;
        //ReadTLV003
        byte[] tlv003 = new byte[classBuffer.getInt()];
        classBuffer.get(tlv003);
        SHA256 = new String(tlv003);
        if (!SHA256.equals(SHA(classByte,"SHA-256"))){
            return null;
        }
        Class vmClass = VMAgent.defineClass(className,classByte);
        Constructor ct = vmClass.getConstructor();
        return (VirtualMachine) ct.newInstance();
    }
    private static String SHA(final byte[] bytes, final String strType) {
        String strResult = null;
        if (bytes != null && bytes.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(bytes);
                byte byteBuffer[] = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
    /**
     * 16 SecKey
     * ... Data
     */
    /**
     * TLV001 ClassName
     * TLV002 ClassData
     * TLV003 ClassSHA256
     */
}
