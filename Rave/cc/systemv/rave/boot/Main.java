package cc.systemv.rave.boot;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        //java.library.path=./natives
        System.setProperty("java.library.path", "./natives");
        //set sys_paths to null so that java.library.path will be reevalueted next time it is needed
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);

        net.minecraft.client.main.Main.main(concat(new String[] {"--version", "mcp", "--accessToken", "0", "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}"}, args));
    }
    public static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}