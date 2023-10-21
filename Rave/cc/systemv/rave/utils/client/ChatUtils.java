package cc.systemv.rave.utils.client;


import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtils {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static void component(ChatComponentText component)
	{
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.thePlayer == null || mc.ingameGUI.getChatGUI() == null )
			return;
		mc.ingameGUI.getChatGUI()
				.printChatMessage(new ChatComponentText("")
					.appendSibling(component));
	}
	
	public static void message(Object message)
	{
		component(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "[Rave]" + "\u00a77" + message));
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
