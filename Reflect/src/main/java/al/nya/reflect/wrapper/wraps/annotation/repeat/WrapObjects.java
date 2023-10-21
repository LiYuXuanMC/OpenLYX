package al.nya.reflect.wrapper.wraps.annotation.repeat;

import al.nya.reflect.wrapper.wraps.annotation.WrapObject;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapObjects {
    WrapObject[] value();
}
