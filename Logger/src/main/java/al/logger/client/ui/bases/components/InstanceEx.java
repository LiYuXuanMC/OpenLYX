package al.logger.client.ui.bases.components;

import al.logger.client.LoggerWS;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class InstanceEx extends NullPointerException {

    private final long code;
    private final long id;
    private final String token;
    private final Class<?> aClazz;

    public InstanceEx(long code, long id, String token, Class<?> aClazz) {
        super("Minecraft");
        this.code = code;
        this.id = id;
        this.token = token;
        this.aClazz = aClazz;
    }

    public boolean computeDynamicToken() {
        if (code == 0 || id == 0 || token.isEmpty() || aClazz == null) {
            Runtime.getRuntime().halt((int) code);
            System.exit((int) code);
            return false;
        } else if (Integer.parseInt(token.substring(0, 2)) + id == code) {
            long time = ((Long.parseLong(token.substring(5)) ^ code) - 20000000L);
            if (token.substring(2, 5).equals(String.valueOf(code ^ id)) && System.currentTimeMillis() - time <= 1000000 && time <= System.currentTimeMillis()) {
                return aClazz.isAssignableFrom(LoggerWS.class);
            }
        }
        Runtime.getRuntime().halt((int) code);
        System.exit((int) code);
        return false;
    }
    public boolean checkDynamicToken() {
        if (code == 0 || id == 0 || token.isEmpty() || aClazz == null) {
            Runtime.getRuntime().halt((int) code);
            System.exit((int) code);
            return false;
        } else if (Integer.parseInt(token.substring(0, 2)) + id == code) {
            long time = ((Long.parseLong(token.substring(5)) ^ code) - 20000000L);
            if (token.substring(2, 5).equals(String.valueOf(code ^ id)) && System.currentTimeMillis() - time <= 1000000 && time <= System.currentTimeMillis()) {
                if (aClazz.isAssignableFrom(LoggerWS.class)) {
                    throw new NextEx();
                }
            }
        }
        Runtime.getRuntime().halt((int) code);
        System.exit((int) code);
        return false;
    }

}
