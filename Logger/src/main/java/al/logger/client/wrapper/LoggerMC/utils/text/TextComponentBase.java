package al.logger.client.wrapper.LoggerMC.utils.text;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.util.text.TextComponentBase",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class TextComponentBase extends IChatComponent{
    public TextComponentBase(Object obj) {
        super(obj);
    }
}
