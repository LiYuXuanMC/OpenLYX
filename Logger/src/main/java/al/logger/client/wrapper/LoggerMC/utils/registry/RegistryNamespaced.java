package al.logger.client.wrapper.LoggerMC.utils.registry;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.util.registry.RegistryNamespaced",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RegistryNamespaced extends RegistrySimple{
    public RegistryNamespaced(Object obj) {
        super(obj);
    }
}
