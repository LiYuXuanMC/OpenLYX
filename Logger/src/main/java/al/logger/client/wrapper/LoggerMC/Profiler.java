package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.CactusWrapping;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.profiler.Profiler",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Profiler extends IWrapper {
    @ClassInstance
    public static Class ProfilerClass;
    @WrapMethod(mcpName = "endSection", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "endSection", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method endSection;
    @WrapMethod(mcpName = "startSection", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Ljava/lang/String;)V")
    @WrapMethod(mcpName = "startSection", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
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
