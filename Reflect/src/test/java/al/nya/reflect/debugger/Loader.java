package al.nya.reflect.debugger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Loader {
    public static String CLIENT_ROOT = "./debug";
    private static final String LIBRARY_DIR = "/libraries";
    private static final Bootstrap.BootType bootType = Bootstrap.BootType.Vanilla;

    public static void main(String[] args) {
        //-Djava.library.path=debug/natives
        //You can use this to set the library path.
        System.out.println("\n" +
                " ______     ______     ______   __         ______     ______     ______  \n" +
                "/\\  == \\   /\\  ___\\   /\\  ___\\ /\\ \\       /\\  ___\\   /\\  ___\\   /\\__  _\\ \n" +
                "\\ \\  __<   \\ \\  __\\   \\ \\  __\\ \\ \\ \\____  \\ \\  __\\   \\ \\ \\____  \\/_/\\ \\/ \n" +
                " \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\    \\ \\_____\\  \\ \\_____\\  \\ \\_____\\    \\ \\_\\ \n" +
                "  \\/_/ /_/   \\/_____/   \\/_/     \\/_____/   \\/_____/   \\/_____/     \\/_/ \n" +
                "                                                                         \n");
        System.out.println("Reflect Debugger - by ReflectHackTeam");
        Bootstrap bootstrap = prepareClient();
        if (bootstrap == null) {
            System.out.println("Failed to prepare client");
            return;
        }
        System.out.println("Prepare client done");
        bootstrap.loadJars();
        try {
            bootstrap.boot();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            System.out.println("Failed to boot");
            e.printStackTrace();
        }
    }

    public static Bootstrap prepareClient() {
        File rootDir = new File(CLIENT_ROOT);
        File libDir = new File(rootDir, LIBRARY_DIR);
        System.out.println("Prepare client in " + rootDir.getAbsolutePath());
        if (!rootDir.exists()) {
            System.out.println("Client root dir not exists");
            rootDir.mkdir();
        }
        if (!libDir.exists()) {
            System.out.println("Client lib dir not exists");
            libDir.mkdir();
        }
        if (libDir.listFiles().length == 0) {
            System.out.println("No jars");
            return null;
        }
        File[] jars = Arrays.stream(libDir.listFiles()).filter(File::isFile).filter(file -> file.getName().endsWith(".jar")).toArray(File[]::new);
        return new Bootstrap(rootDir, jars, bootType);
    }

}
