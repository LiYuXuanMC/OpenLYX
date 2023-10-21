package dev.vector.utils;

public class Profiler {
    private long startTime;
    public Profiler(){
        this.startTime = System.currentTimeMillis();
    }
    public long end(){
        return System.currentTimeMillis() - startTime;
    }
}
