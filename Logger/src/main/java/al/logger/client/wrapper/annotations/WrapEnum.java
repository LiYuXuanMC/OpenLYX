package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapEnums;
import al.logger.client.wrapper.environment.Environment;

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
    Environment[] targetMap();
    String customSrgName() default "none";
}
