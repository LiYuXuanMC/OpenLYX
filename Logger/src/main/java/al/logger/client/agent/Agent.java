package al.logger.client.agent;

import java.lang.instrument.UnmodifiableClassException;

public interface Agent {
    int retransform(Class<?> target);
    Class<?> defineClass(ClassLoader classLoader,String name,byte[] classBytes);
    void nativeLog(String msg);
}
