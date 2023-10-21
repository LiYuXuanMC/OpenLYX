package com.reflectmc.injector;

import com.reflectmc.injector.socket.LocalSocket;

public class Main {
    public static String debugUsername = "CanYingisme";
    public static String debugPassword = "3885351a";
    public static int debugChannel = 0;
    public static void main(String[] args){
        LocalSocket socket = new LocalSocket();
        socket.start();
    }
}
