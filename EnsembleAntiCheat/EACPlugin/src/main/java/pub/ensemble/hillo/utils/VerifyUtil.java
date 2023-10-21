package pub.ensemble.hillo.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VerifyUtil {

    public static String getSha256(String str) throws NoSuchAlgorithmException {
        MessageDigest object = MessageDigest.getInstance("SHA-256");
        byte[] hash = object.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String getMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] b = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (byte value : b) {
            i = value;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static byte[] decryptBody(byte[] buffer) {
        if (buffer.length == 0) {
            return null;
        }
        byte[] newBuffer = new byte[buffer.length - 12];
        System.arraycopy(buffer, 0, newBuffer, 0, newBuffer.length);
        buffer = newBuffer;
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (((buffer[i] ^ 0xFF) ^ 0xE9) + 0x7F);
        }
        return buffer;
    }

    public static void verifyAuthenticationBody(byte[] authenticationBody, String username, String serverHash) throws NoSuchAlgorithmException {
        JByteBuf buffer = new JByteBuf();
        buffer.writeBytes(authenticationBody, authenticationBody.length);
        String header = buffer.readString() + buffer.readString();
        if (!header.equals("EnsembleAntiCheat")) {
            throw new RuntimeException("步骤一未通过");
        }
        buffer.readLong();
        if (!buffer.readString().equals(getMD5("Q4dd4u9O" + serverHash + "p9ysNM").toLowerCase())) {
            throw new RuntimeException("步骤二未通过");
        } else if (!buffer.readString().equals(username)) {
            throw new RuntimeException("步骤三未通过");
        } else if (!buffer.readString().equals("Mjp9ysNMQ4dd4u9O")) {
            throw new RuntimeException("步骤四未通过");
        }
        //过滤，只是要求符合格式
        buffer.readString();
        if(buffer.readInt() != 2){
            throw new RuntimeException("步骤五");
        }
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        buffer.readByte();
        //下一个格式
        String cToken = buffer.readString();
        long code = Integer.parseInt(cToken.substring(0, 3));
        long id = Integer.parseInt(cToken.substring(3, 5));
        String token = cToken.substring(5);
        if (code == 0 || id == 0 || token.isEmpty()) {
            throw new RuntimeException("步骤六未通过");
        } else if (Integer.parseInt(token.substring(0, 2)) + id != code) {
            throw new RuntimeException("步骤七未通过");
        }
        long time = ((Long.parseLong(token.substring(5)) ^ code) - 100000L);
        if (token.substring(2, 5).equals(String.valueOf(code ^ id))) {
            if(!(System.currentTimeMillis() - time <= 300000)){
                throw new RuntimeException("步骤八未通过");
            }
            //过滤，只是要求符合格式
            buffer.readString();
            //下一个格式
            String tokenMD5 = buffer.readString();
            if (tokenMD5.equals(getMD5(cToken + username + "Cjp9uiNMQ4II4dO").toLowerCase())) {
                return;
            }
        } else {
            throw new RuntimeException("步骤九未通过");
        }
        throw new RuntimeException("步骤十未通过");
    }


}
