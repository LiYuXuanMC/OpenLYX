package dev.qingwan.crater;

import java.util.Date;

public class Log {
    public static boolean disableDebug = false;
    public static void info(String info){
        Date date = new Date();
        System.out.println("[Crater]["+Thread.currentThread().getName()+"/Info]["+date+"]"+info);
    }
    public static void warn(String info){
        Date date = new Date();
        System.out.println("[Crater]["+Thread.currentThread().getName()+"/Warn]["+date+"]"+info);
    }
    public static void debug(String info){
        if (disableDebug) return;;
        Date date = new Date();
        System.out.println("[Crater]["+Thread.currentThread().getName()+"/DEBUG]["+date+"]"+info);
    }
    public static void error(String info){
        Date date = new Date();
        System.out.println("[Crater]["+Thread.currentThread().getName()+"/Error]["+date+"]"+info);
    }
}
