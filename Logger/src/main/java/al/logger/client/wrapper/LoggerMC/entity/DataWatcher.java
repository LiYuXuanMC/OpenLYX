package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.DataWatcher", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.datasync.EntityDataManager", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class DataWatcher extends IWrapper {
    @ClassInstance
    public static Class<?> DataWatcherClass;
    @WrapMethod(mcpName = "getWatchableObjectByte", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getWatchableObjectByte;
    @WrapMethod(mcpName = "updateObject", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method updateObject;
    public DataWatcher(Object obj) {
        super(obj);
    }

    public void updateObject(int index, Object value) {
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            throw new UnsupportedOperationException("1.8.9 does not support this method");
        }
        invoke(updateObject, index, value);
    }

    public byte getWatchableObjectByte(int i1) {
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            throw new UnsupportedOperationException("1.8.9 does not support this method");
        }
        return (byte) invoke(getWatchableObjectByte, i1);
    }

    public static boolean isDataWatcher(Object obj) {
        return DataWatcherClass.isInstance(obj);
    }
}
