package al.nya.reflect.wrapper.wraps.annotation;

import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapFields;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapFields.class)
/**
 * Auto setup field
 */
public @interface WrapField {
    String mcpName();
    String targetMap();
    String customSrgName() default "none";
}
