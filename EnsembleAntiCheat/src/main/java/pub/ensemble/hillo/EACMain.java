package pub.ensemble.hillo;

import est.builder.annotations.Export;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public final class EACMain {
    public native void initAntiCheat();
    public native int checkAntiCheat(int a,int b);
    public native int getHYCCode();
    public native byte[] getAuthenticationBody(String username,String serverHash);

/*    public static void joinServer(GameProfile profile, String authenticationToken, String serverId){
        System.out.println("JoinServer");
    }*/

    static {
        try {
            String dllResourcePath = System.getProperty("sun.arch.data.model").contains("64")
                    ? "/dev/eac/EACDLL.dll"
                    : "/dev/eac/EACDLL_x86.dll";
            File tempDllFile = File.createTempFile("eacdll", ".dll");
            tempDllFile.deleteOnExit();
            try (InputStream in = EACMain.class.getResourceAsStream(dllResourcePath);
                 FileOutputStream out = new FileOutputStream(tempDllFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            System.load(tempDllFile.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EAC.dll Load Failed!", "EAC", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
