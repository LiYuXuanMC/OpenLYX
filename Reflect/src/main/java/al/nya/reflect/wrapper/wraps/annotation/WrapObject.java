package al.nya.reflect.wrapper.wraps.annotation;

import al.nya.reflect.wrapper.wraps.annotation.repeat.WrapObjects;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapObjects.class)
/**
 * Auto setup field
 */
public @interface WrapObject {
    String mcpName();
    String targetMap();
    String customSrgName() default "none";
    Class makeWrap() default Void.class;
}
