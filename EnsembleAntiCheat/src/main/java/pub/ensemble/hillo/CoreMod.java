package pub.ensemble.hillo;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pub.ensemble.hillo.core.EACSMgr;
import pub.ensemble.hillo.obf.EACTransformer;
import sun.misc.Unsafe;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


public final class CoreMod implements IFMLLoadingPlugin {

    public static EACMain eac;
    public static CoreMod instance;
    public String coreName = "EAC.dll";
    public String coreOffset = "0xFFFFB3";
    public String coreName16;
    public String coreOffset16;
    public SimpleNetworkWrapper networkChannel;
    public final static Logger LOG = LogManager.getLogger(CoreMod.class);

    public CoreMod() {
        LOG.info("EAC Starting...");
        instance = this;
        loadEAC();
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    private static boolean loaded = false;

    private static void loadEAC() {

        if (!loaded) {
            SecurityManager eacSMgr = new EACSMgr();
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                Unsafe theUnsafe = (Unsafe)f.get(null);
                theUnsafe.putObject(System.class,116,eacSMgr);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            eac = new EACMain();
            eac.initAntiCheat();
            loaded = true;


            try{
                LaunchClassLoader launchClassLoader = Launch.classLoader;
                Field field = LaunchClassLoader.class.getDeclaredField("transformers");
                field.setAccessible(true);
                List list = (List)field.get(launchClassLoader);
                IClassTransformer eactransformer = (IClassTransformer)Class.forName(EACTransformer.class.getName(), true, launchClassLoader).newInstance();
                list.add(eactransformer);
                field.set(launchClassLoader, list);
                CoreModManager.getIgnoredMods().clear();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}