package al.logger.client.agent;

import est.builder.annotations.Export;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

@Export(name = "al_logger_client_agent_NativeAgent_className")
public class NativeAgent implements Agent {

    @Override
    public int retransform(Class<?> target) {
        return retransform0(target);
    }

    @Override
    //ToDO:adapt to java 9+
    public Class<?> defineClass(ClassLoader classLoader, String name, byte[] classBytes) {
        return defineClass0(classLoader,name,classBytes);
    }
    @Override
    public void nativeLog(String msg){
        nativeLog0(msg);
    }
    protected final Class<?> DefineClassWithJavaReflect(String name, byte[] b, int off, int len,ClassLoader classloader)
            throws ClassFormatError, NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method preDefineClass = ClassLoader.class.getDeclaredMethod("preDefineClass", String.class,ProtectionDomain.class );
        Method defineClassSourceLocation = ClassLoader.class.getDeclaredMethod("defineClassSourceLocation", ProtectionDomain.class );
        Method defineClass1 = ClassLoader.class.getDeclaredMethod("defineClass1", String.class, byte[].class, Integer.TYPE,Integer.TYPE,ProtectionDomain.class,String.class);
        Method postDefineClass = ClassLoader.class.getDeclaredMethod("postDefineClass", Class.class,ProtectionDomain.class);
        preDefineClass.setAccessible(true);
        defineClassSourceLocation.setAccessible(true);
        defineClass1.setAccessible(true);
        postDefineClass.setAccessible(true);
        ProtectionDomain protectionDomain = (ProtectionDomain)preDefineClass.invoke(classloader,name, classloader.getClass().getProtectionDomain());
        String source = (String)defineClassSourceLocation.invoke(classloader,protectionDomain);
        Class<?> c = (Class<?>) defineClass1.invoke(classloader,name, b, off, len, protectionDomain, source);
        postDefineClass.invoke(classloader,c, protectionDomain);
        System.out.println("Defined");
        return c;
    }
    @Export(name = "al_logger_client_agent_NativeAgent_retransform0_methodName")
    private static native int retransform0(Class<?> target);
    @Export(name = "al_logger_client_agent_NativeAgent_defineClass0_methodName")
    private static native Class<?> defineClass0(ClassLoader classLoader, String name, byte[] classBytes);
    @Export(name = "al_logger_client_agent_NativeAgent_nativeLog0_methodName")
    private static native void nativeLog0(String msg);
}
