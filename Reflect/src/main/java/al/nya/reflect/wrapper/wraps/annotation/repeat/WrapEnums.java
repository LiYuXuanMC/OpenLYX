package al.nya.reflect.wrapper.wraps.annotation.repeat;

import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapEnums {
    WrapEnum[] value();
}
