package al.nya.reflect.wrapper.wraps.annotation.repeat;

import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapMethods {
    WrapMethod[] value();
}
