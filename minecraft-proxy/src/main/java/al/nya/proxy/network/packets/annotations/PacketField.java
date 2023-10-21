package al.nya.proxy.network.packets.annotations;

import al.nya.proxy.utils.EnumDataType;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketField {
    int seq();
    EnumDataType type();
    Class<? extends Enum> enumClass() default Enum.class;

}
