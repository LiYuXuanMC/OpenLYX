package al.nya.reflect.wrapper.wraps.wrapper;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = Maps.Srg1_12_2)
public class Profiler extends IWrapper{
    @WrapClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = Maps.Srg1_12_2)
    public static Class ProfilerClass;
    @WrapMethod(mcpName = "endSection", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "endSection", targetMap = Maps.Srg1_12_2)
    public static Method endSection;
    @WrapMethod(mcpName = "startSection", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "startSection", targetMap = Maps.Srg1_12_2)
    public static Method startSection;
    public void startSection(String s){
        invoke(startSection,s);
    }
    public void endSection(){
        invoke(endSection);
    }
    public Profiler(Object obj) {
        super(obj);
    }
}
