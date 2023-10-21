package al.logger.client.wrapper.LoggerMC.utils.event;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.event.HoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class HoverEvent extends IWrapper {
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {HoverEventAction.class, IChatComponent.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {HoverEventAction.class, IChatComponent.class})
    public static Constructor<?> HoverEvent_HoverEventAction_IChatComponent;

    public HoverEvent(Enum<?> e, IChatComponent c) {
        super(ReflectUtil.construction(HoverEvent_HoverEventAction_IChatComponent, e, c.getWrappedObject()));
    }

    public HoverEvent(Object obj) {
        super(obj);
    }
}
