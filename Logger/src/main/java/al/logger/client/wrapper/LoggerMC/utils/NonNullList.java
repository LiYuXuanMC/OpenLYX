package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.NonNullList", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class NonNullList extends IWrapper {
@ClassInstance    
public static Class NonNullListClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Constructor NonNullListConstructor;

    public NonNullList(Object obj) {
        super(obj);
    }

    public NonNullList() {
        super(construction(NonNullListConstructor));
    }
}
