package al.nya.reflect.wrapper.wraps.annotation;

import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapEnums;
import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapFields;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapEnums.class)
/**
 * Auto setup Enum
 */
public @interface WrapEnum {
    String mcpName();
    String targetMap();
    String customSrgName() default "none";
}
