package al.nya.reflect.wrapper.wraps.annotation;

import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapMethods;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapMethods.class)
/**
 * Auto setup method
 */
public @interface WrapMethod {
    String mcpName();
    String targetMap();
    String customSrgName() default "none";
    String signature() default "none";
}
