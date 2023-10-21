package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.GameProfile;
import al.logger.client.wrapper.LoggerMC.world.World;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.entity.EntityOtherPlayerMP", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityOtherPlayerMP extends AbstractClientPlayer {
@ClassInstance    
public static Class<?> EntityOtherPlayerMPClass;
    @WrapConstructor(signature = {World.class, GameProfile.class}, targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> EntityOtherPlayerMP_Constructor;

    public EntityOtherPlayerMP(Object obj) {
        super(obj);
    }

    public EntityOtherPlayerMP(World worldIn, GameProfile gameProfile) {
        this(ReflectUtil.construction(EntityOtherPlayerMP_Constructor, worldIn.getWrappedObject(), gameProfile.getWrappedObject()));
    }

    public static boolean isEntityOtherPlayerMP(Object c) {
        return EntityOtherPlayerMPClass.isInstance(c);
    }
}
