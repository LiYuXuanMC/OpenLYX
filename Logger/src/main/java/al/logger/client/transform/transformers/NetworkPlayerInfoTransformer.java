package al.logger.client.transform.transformers;


import al.logger.client.Logger;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.value.bases.AuthUser;
import al.logger.client.wrapper.LoggerMC.network.NetworkPlayerInfo;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;


public class NetworkPlayerInfoTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return NetworkPlayerInfo.NetworkPlayerInfoClass;
    }

    @Override
    public void transformClass(ClassNode cn) {


    }


    @ExportObfuscate(name = "getCape")
    public static Object getCape(Object networkPlayerInfo) {
        NetworkPlayerInfo tmp = new NetworkPlayerInfo(networkPlayerInfo);
        if (Logger.getInstance().getAuthUserManager().getAuthUsers().containsKey(tmp.getGameProfile().getName())) {
            AuthUser authUser = Logger.getInstance().getAuthUserManager().getAuthUser(tmp.getGameProfile().getName());
            return Logger.getInstance().getResourceManager().getCape(authUser.username).getWrappedObject();
        }
        return null;
    }


}
