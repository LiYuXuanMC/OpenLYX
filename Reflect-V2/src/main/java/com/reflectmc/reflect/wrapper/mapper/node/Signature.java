package com.reflectmc.reflect.wrapper.mapper.node;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Signature {
    @Getter
    private String signature;
    @Getter
    private Class<?>[] parameter;
    @Getter
    private Class<?> returnClass;
    public Signature(String signature){
        this.signature = signature;
    }
    public void parse(){
        try {
            signature2Class(signature);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void signature2Class(String signature) throws ClassNotFoundException {
        List<Class<?>> arg = new ArrayList<Class<?>>();
        Class<?> ret = void.class;
        boolean readClassName = false;
        boolean readArg = true;
        String className = "";
        StringStream ss = new StringStream(signature);
        while (ss.available()){
            String t = ss.read();
            if (readArg){
                if (readClassName){
                    if (!t.equals(";")){
                        className += t;
                    }else {
                        readClassName = false;
                        Class<?> target = Class.forName(className.replace("/","."));
                        arg.add(target);
                    }
                }else
                if (t.equals("(")){

                }else if (t.equals("Z")){
                    arg.add(boolean.class);
                }else if (t.equals("C")){
                    arg.add(char.class);
                }else if (t.equals("B")){
                    arg.add(byte.class);
                }else if (t.equals("S")){
                    arg.add(short.class);
                }else if (t.equals("I")){
                    arg.add(int.class);
                }else if (t.equals("F")){
                    arg.add(float.class);
                }else if (t.equals("J")){
                    arg.add(long.class);
                }else if (t.equals("D")){
                    arg.add(double.class);
                }else if (t.equals("L")){
                    readClassName = true;
                    className = "";
                }else if (t.equals(")")){
                    readArg = false;
                }
            }else {
                if (readClassName){
                    if (!t.equals(";")){
                        className += t;
                    }else {
                        readClassName = false;
                        ret = (Class.forName(className.replace("/",".")));
                    }
                }else if (t.equals("Z")){
                    ret = (boolean.class);
                }else if (t.equals("C")){
                    ret = (char.class);
                }else if (t.equals("B")){
                    ret = (byte.class);
                }else if (t.equals("S")){
                    ret = (short.class);
                }else if (t.equals("I")){
                    ret = (int.class);
                }else if (t.equals("F")){
                    ret = (float.class);
                }else if (t.equals("J")){
                    ret = (long.class);
                }else if (t.equals("D")){
                    ret = (double.class);
                }else if (t.equals("L")){
                    readClassName = true;
                    className = "";
                }else if (t.equals("V")){
                    ret = void.class;
                }
            }

        }
        Class<?>[] classes = new Class[arg.size()];
        classes = arg.toArray(classes);
        for (Class<?> aClass : classes) {
            if (aClass == null){
                System.out.println("class = null");
            }
        }
        this.parameter = classes;
        this.returnClass = ret;
    }
}
