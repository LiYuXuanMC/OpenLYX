package al.nya.reflect.debugger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Random;

public class Bootstrap {
    private final File rootDir;
    private final File[] jars;
    private final BootType bootType;
    private URLClassLoader classLoader;

    public Bootstrap(File rootDir, File[] jars, BootType bootType) {
        this.rootDir = rootDir;
        this.jars = jars;
        this.bootType = bootType;
    }

    public BootType getBootType() {
        return bootType;
    }

    public File getRootDir() {
        return rootDir;
    }

    public File[] getJars() {
        return jars;
    }

    public URLClassLoader getClassLoader() {
        return classLoader;
    }

    public void loadJars() {
        System.out.println("Load jars");
        URL[] urls = new URL[jars.length];
        for (int i = 0; i < jars.length; i++) {
            try {
                System.out.println(jars[i].getAbsolutePath());
                urls[i] = jars[i].toURI().toURL();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Method m = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            m.setAccessible(true);
            for (URL url : urls) {
                m.invoke(ClassLoader.getSystemClassLoader(), url);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Load jars done");
    }

    private String[] bootArgs() {
        return new String[]{"--username", "Reflect" + new Random().nextInt(5), "--version", "mcp", "--accessToken", "0", "--gameDir", rootDir.getAbsolutePath(), "--assetsDir", rootDir.getAbsolutePath() + "/assets", "--assetIndex", "1.8", "--userProperties", "{}"};
    }

    public void boot() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("Booting");
        String[] args = bootArgs();
        Class<?> main = ClassUtil.getClass(bootType.getMainClass());
        new Thread(new MinecraftLoadingChecker()).start();
        main.getMethod("main", String[].class).invoke(null, (Object) args);
    }

    public enum BootType {
        Forge("net.minecraft.launchwrapper.Launch"),
        Vanilla("net.minecraft.client.main.Main"),
        ;
        private final String mainClass;

        BootType(String mainClass) {
            this.mainClass = mainClass;
        }

        public String getMainClass() {
            return mainClass;
        }
    }
}
