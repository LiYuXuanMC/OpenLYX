package al.logger.client.wrapper.LoggerMC.model;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class ModelBase extends IWrapper {
@ClassInstance
public static Class<?> ModelBaseClass;
    @WrapMethod(mcpName = "copyModelAngles",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "copyModelAngles",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method copyModelAngles;
    @WrapField(mcpName = "isChild",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field isChild;
    @WrapField(mcpName = "isRiding",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field isRiding;

    public ModelBase(Object obj) {
        super(obj);
    }
    public void setIsChild(boolean value){
        setField(isChild,value);
    }

    public void setIsRiding(boolean value){setField(isRiding ,value);}
}
