package al.nya.reflect.wrapper.wraps.annotation;

import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapConstructors;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapConstructors.class)
public @interface WrapConstructor {
    String targetMap();
    Class<?>[] signature() default {};
}
