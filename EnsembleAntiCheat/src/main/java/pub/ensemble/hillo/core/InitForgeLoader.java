package pub.ensemble.hillo.core;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import pub.ensemble.hillo.CoreMod;
import pub.ensemble.hillo.handlers.EACCoreHandler;
import pub.ensemble.hillo.messages.*;

@Mod(modid = InitForgeLoader.MODID, version = InitForgeLoader.VERSION,name = InitForgeLoader.NAME)
public class InitForgeLoader {

    public static final String MODID = "eac-core";
    public static final String NAME = "EAC-Core";
    public static final String VERSION = "2.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        if (!(System.getSecurityManager() instanceof EACSMgr)){
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
        }

        MinecraftForge.EVENT_BUS.register(new AntiCheatEvent());
        CoreMod.instance.networkChannel = NetworkRegistry.INSTANCE.newSimpleChannel("EnsembleAntiCheat");
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), SPacketHandShake.class, 0, Side.CLIENT);
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), SPacketPing.class, 1, Side.CLIENT);
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), ConvertPacket.class, 2, Side.CLIENT);
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), CPacketHandShake.class, 3, Side.SERVER);
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), CPacketPing.class, 4, Side.SERVER);
        CoreMod.instance.networkChannel.registerMessage(new EACCoreHandler(), ConvertPacket.class, 5, Side.SERVER);

        System.out.println("Forge Mod Successfully Loaded");

    }
}

