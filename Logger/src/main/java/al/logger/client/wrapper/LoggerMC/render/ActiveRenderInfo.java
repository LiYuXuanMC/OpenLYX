package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.world.World;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.environment.Environment;


import java.lang.reflect.Method;

public class ActiveRenderInfo extends IWrapper {
    @WrapMethod(mcpName = "getBlockAtEntityViewpoint",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getBlockAtEntityViewpoint;
    public ActiveRenderInfo(Object obj) {
        super(obj);
    }
    public static Block getBlockAtEntityViewpoint(World world, Entity entity, float p_getBlockAtEntityViewpoint_2_) {
        return new Block(invokeStatic(getBlockAtEntityViewpoint,world.getWrappedObject(),entity.getWrappedObject(),p_getBlockAtEntityViewpoint_2_));
    }
}
