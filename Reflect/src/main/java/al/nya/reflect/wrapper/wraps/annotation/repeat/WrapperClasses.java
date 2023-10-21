package al.nya.reflect.wrapper.wraps.annotation.repeat;

import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapperClasses {
    WrapperClass[] value();
}
