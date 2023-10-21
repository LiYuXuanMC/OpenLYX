package al.logger.client.wrapper.LoggerMC.shader;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.shader.ShaderGroup", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ShaderGroup extends IWrapper {
@ClassInstance
public static Class<?> ShaderGroupClass;

    public ShaderGroup(Object obj) {
        super(obj);
    }


}
