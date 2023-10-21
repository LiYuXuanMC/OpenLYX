package al.logger.client.wrapper.LoggerMC.block.state;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.block.state.BlockState", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class BlockState extends IWrapper {
    @ClassInstance
    public static Class BlockStateClass;

    @WrapMethod(mcpName = "getBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlock;

    public BlockState(Object obj) {
        super(obj);
    }
}
