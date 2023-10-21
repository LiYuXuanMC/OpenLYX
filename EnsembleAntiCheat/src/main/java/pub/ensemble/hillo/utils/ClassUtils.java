package pub.ensemble.hillo.utils;

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.VMOption;
import java.lang.management.ManagementFactory;

public class ClassUtils {



    public static String getPackageName(String className) {
        int lastDotIndex = className.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return className.substring(0, lastDotIndex);
        }
        return "";
    }
    public static boolean checkAttachMechanism() {
        try {
            VMOption vMOption = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class)
                    .getVMOption("DisableAttachMechanism");

            return vMOption != null && ("false".equals(vMOption.getValue()) || vMOption.isWriteable())
                    || !ManagementFactory.getRuntimeMXBean().getInputArguments().contains("-XX:+DisableAttachMechanism");
        } catch (Exception ignored) {
            return false;
        }
    }
}
