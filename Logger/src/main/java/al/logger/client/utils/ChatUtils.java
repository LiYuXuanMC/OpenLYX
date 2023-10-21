package al.logger.client.utils;

import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.utils.text.ChatComponentText;
import al.logger.client.wrapper.LoggerMC.utils.EnumChatFormatting;

public class ChatUtils {	// TODO Rewrite to LogManager
	public static Minecraft mc = Minecraft.getMinecraft();
	public static void component(ChatComponentText component)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.getThePlayer().isNull() || mc.getIngameGUI().getGuiChat().isNull() )
			return;
		mc.getIngameGUI().getGuiChat()
				.printChatMessage(new ChatComponentText("")
					.appendSibling(component));
	}
	
	public static void message(Object message)
	{
		component(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "[LOGGER]" + "\u00a77" + message));
	}
	public static void irc(String username,String rank,String message)
	{
		component(new ChatComponentText(EnumChatFormatting.GOLD + "["+rank+EnumChatFormatting.GOLD +"]" + "\u00a77" +username+ ": "+EnumChatFormatting.WHITE+message));
	}
	public static void report(String message) {
		
		message(EnumChatFormatting.GREEN + message);
	}
	
	
	public static void warning(Object message)
	{
		message("\u00a78[\u00a7eWARNING\u00a78]\u00a7e " + message);
	}
	
	public static void error(Object message)
	{
		message("\u00a78[\u00a74ERROR\u00a78]\u00a7c " + message);
	}
	public static void VelocityCheck(Object message) {
		message("\u00a78[\u00a7eVELOCITY\u00a78]\u00a7e " + message);
	}
	
	
	
	
}
