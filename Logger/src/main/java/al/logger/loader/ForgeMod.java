package al.logger.loader;

import al.logger.client.Logger;
import al.logger.client.utils.LoggerUser;
import al.logger.client.utils.ReflectUtil;
import lombok.SneakyThrows;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.lang.reflect.Field;

@Mod(modid = ForgeMod.MODID, name = ForgeMod.NAME, version = ForgeMod.VERSION, acceptableRemoteVersions = "*")
public class ForgeMod {
	public static final String MODID = "logger";
	public static final String NAME = "Logger";
	public static final String VERSION = "0.0.1";
	public static FontRenderer fontRenderer = null;

	@SneakyThrows
	@Mod.EventHandler
	private static void init(FMLInitializationEvent E) {
		//Logger Belongs to QianHeJ
		//Kendall & CX are QianHeJ‘s Dads
		//Logger.setAgent(new AttachAgent());
		System.load(new File(new File(System.getProperty("user.dir")).getParentFile(),"DebugNative/x64/Debug/DebugNative.dll").getAbsolutePath());
		new Logger();
		//String username = "Kendall";
		String passowrd = "12345678";
		//Logger.getInstance().setLoggerUser(new LoggerUser(username, passowrd));
		//Logger.getInstance().Logger_Verify();
	}

	@SneakyThrows
	public static void setLibraryPath(String path){
		System.out.println("set path to "+path);
		System.setProperty("java.library.path", path);
		//set sys_paths to null so that java.library.path will be reevalueted next time it is needed
		final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
		sysPathsField.setAccessible(true);
		sysPathsField.set(null, null);
	}

}
