package al.logger.client.wrapper.LoggerMC.utils.text;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.ChatComponentText",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.TextComponentString",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ChatComponentText extends TextComponentBase {
    @ClassInstance
    public static Class ChatComponentTextClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {String.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {String.class})
    public static Constructor ChatComponentText_S;
    private ChatComponentText(Object obj) {
        super(obj);
    }
    public ChatComponentText(String text){
        super(construction(ChatComponentText_S,text));
    }
    public static ChatComponentText wrap(Object obj){
        return new ChatComponentText(obj);
    }
}
