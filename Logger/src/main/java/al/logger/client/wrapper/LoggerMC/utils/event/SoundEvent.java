package al.logger.client.wrapper.LoggerMC.utils.event;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.SoundEvent",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class SoundEvent extends IWrapper {
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {ResourceLocation.class})
    public static Constructor SoundEvent_ResourceLocation;
    public SoundEvent(Object obj) {
        super(obj);
    }
    public SoundEvent(ResourceLocation rl){
        super(ReflectUtil.construction(SoundEvent_ResourceLocation,rl.getWrappedObject()));
    }
}
