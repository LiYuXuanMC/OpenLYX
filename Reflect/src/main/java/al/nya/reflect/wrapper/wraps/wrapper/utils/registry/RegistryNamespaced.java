package al.nya.reflect.wrapper.wraps.wrapper.utils.registry;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.util.registry.RegistryNamespaced",targetMap = Maps.Srg1_12_2)
public class RegistryNamespaced extends RegistrySimple{
    public RegistryNamespaced(Object obj) {
        super(obj);
    }
}
