package al.nya.reflect.wrapper.wraps.annotation.repeat;

import al.nya.reflect.wrapper.wraps.annotation.WrapClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapClasses {
    WrapClass[] value();
}
