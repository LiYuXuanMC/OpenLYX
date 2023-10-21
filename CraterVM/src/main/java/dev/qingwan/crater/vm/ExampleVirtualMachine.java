package dev.qingwan.crater.vm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ExampleVirtualMachine extends VirtualMachine{
    private static List<Object> invokeMap = new ArrayList<>();
    private static byte[] codeMap = null;
    private static Object[] staticPool = new Object[0];
    private static Object[] nonstaticPool = new Object[0];
    @Override
    public void init() {
        System.out.println("ExampleVirtualMachine init");
    }
    public static Object accessStaticField(int index){
        return staticPool[index];
    }
    public Object accessField(int index){
        return nonstaticPool[index];
    }
    public static void a() throws ClassNotFoundException {
        byte[] byArray = new byte[]{1,2,3,4};
        ByteBuffer buffer = ByteBuffer.wrap(byArray);
        int len = buffer.getInt();
        byte[] byArray2 = new byte[len];
        buffer.get(byArray2);
        ByteBuffer buf2 = ByteBuffer.wrap(byArray2);
        while (buf2.hasRemaining()){
            int len2 = buf2.getInt();
            byte[] byArray3 = new byte[len2];
            buf2.get(byArray3);
            String s = new String(byArray3);
            String[] s1 = s.split("]");
            if (s1.length == 3){
                Class clazz = Class.forName(s1[0]);
                for (Method declaredMethod : clazz.getDeclaredMethods()) {
                    if (declaredMethod.getName().equals(s1[1])){
                        if (getMethodDescriptor(declaredMethod).equals(s1[2])){
                            if (!declaredMethod.isAccessible())
                                declaredMethod.setAccessible(true);//Abnormal
                            invokeMap.add(declaredMethod);
                        }
                    }
                }
            }else if (s1.length == 2){
                Class clazz = Class.forName(s1[0]);
                for (Field declaredField : clazz.getDeclaredFields()) {
                    if (declaredField.getName().equals(s1[1])){
                        invokeMap.add(declaredField);
                    }
                }
            }else if (s1.length == 3){
                Class clazz = Class.forName(s1[0]);
                invokeMap.add(clazz);
            }
        }
        len = buffer.remaining();
        byArray2 = new byte[len];
        buffer.get(byArray2);
        codeMap = byArray2;
    }
    public static Object invokeVirtualMachine(int offset,Object... args){
        int index = offset;
        ConcurrentLinkedQueue stack = new ConcurrentLinkedQueue<>();
        Map vars = new HashMap();
        for (int i = 0; i < args.length; i++) {
            vars.put(i,args[i]);
        }
        while (index != -1){
            index = ADD_I(index,stack,vars);
            if (index == -2){
                throw new RuntimeException("Op code cannot be process");
            }
        }
        return stack.poll();
    }
    private static int ADD_I(int offset,ConcurrentLinkedQueue stack,Map vars){
        if (codeMap[offset] == 0x01){
            int a = (int) stack.poll();
            int b = (int) stack.poll();
            stack.offer(a + b);
            return offset + 1;
        }
        return otherParser(offset,stack,vars);
    }
    public static int otherParser(int offset,ConcurrentLinkedQueue stack,Map vars){
        return -1;
    }
    public static String getMethodDescriptor(final Method method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        Class<?>[] parameters = method.getParameterTypes();
        for (Class<?> parameter : parameters) {
            appendDescriptor(parameter, stringBuilder);
        }
        stringBuilder.append(')');
        appendDescriptor(method.getReturnType(), stringBuilder);
        return stringBuilder.toString();
    }
    private static void appendDescriptor(final Class<?> clazz, final StringBuilder stringBuilder) {
        Class<?> currentClass = clazz;
        while (currentClass.isArray()) {
            stringBuilder.append('[');
            currentClass = currentClass.getComponentType();
        }
        if (currentClass.isPrimitive()) {
            char descriptor;
            if (currentClass == Integer.TYPE) {
                descriptor = 'I';
            } else if (currentClass == Void.TYPE) {
                descriptor = 'V';
            } else if (currentClass == Boolean.TYPE) {
                descriptor = 'Z';
            } else if (currentClass == Byte.TYPE) {
                descriptor = 'B';
            } else if (currentClass == Character.TYPE) {
                descriptor = 'C';
            } else if (currentClass == Short.TYPE) {
                descriptor = 'S';
            } else if (currentClass == Double.TYPE) {
                descriptor = 'D';
            } else if (currentClass == Float.TYPE) {
                descriptor = 'F';
            } else if (currentClass == Long.TYPE) {
                descriptor = 'J';
            } else {
                throw new AssertionError();
            }
            stringBuilder.append(descriptor);
        } else {
            stringBuilder.append('L').append(getInternalName(currentClass)).append(';');
        }
    }
    public static String getInternalName(final Class<?> clazz) {
        return clazz.getName().replace('.', '/');
    }
}
