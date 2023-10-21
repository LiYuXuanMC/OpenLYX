package al.nya.reflect.debugger.attach;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class SelfAttachAgent {
    private static volatile Instrumentation globalInstrumentation;

    public static void premain(String agentArgs, Instrumentation inst) {
        globalInstrumentation = inst;
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        globalInstrumentation = inst;
    }

    public static Instrumentation getInstrumentation() {
        if (globalInstrumentation == null) {
            loadAgent();
        }
        return globalInstrumentation;
    }

    public static void loadAgent() {
        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        int index = nameOfRunningVM.indexOf('@');
        String pid = nameOfRunningVM.substring(0, index);

        VirtualMachine vm = null;
        try {
            String jarPath = createTempJarFile().getPath();
            System.out.println(jarPath);

            vm = VirtualMachine.attach(pid);
            vm.loadAgent(jarPath, "");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (vm != null) {
                try {
                    vm.detach();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static File createTempJarFile() throws IOException {
        File jar = File.createTempFile("agent", ".jar");
        jar.deleteOnExit();
        createJarFile(jar);
        return jar;
    }

    private static void createJarFile(File jar) throws IOException {
        String className = SelfAttachAgent.class.getName();

        Manifest manifest = new Manifest();
        Attributes attrs = manifest.getMainAttributes();
        attrs.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        attrs.put(new Attributes.Name("Premain-Class"), className);
        attrs.put(new Attributes.Name("Agent-Class"), className);
        attrs.put(new Attributes.Name("Can-Retransform-Classes"), "true");
        attrs.put(new Attributes.Name("Can-Redefine-Classes"), "true");

        JarOutputStream jos = new JarOutputStream(new FileOutputStream(jar), manifest);
        jos.flush();
        jos.close();
    }
}
