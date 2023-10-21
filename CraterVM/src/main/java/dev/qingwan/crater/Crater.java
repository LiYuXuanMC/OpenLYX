package dev.qingwan.crater;

import dev.qingwan.crater.annotation.CraterVM;
import dev.qingwan.crater.builder.ProcessProvider;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Crater {
    public static void main(String[] args){
        ProcessProvider processProvider = new ProcessProvider(new File("./build/libs/CraterVM-1.0-SNAPSHOT.jar"));
        processProvider.doObf();
        processProvider.save();
    }

}
