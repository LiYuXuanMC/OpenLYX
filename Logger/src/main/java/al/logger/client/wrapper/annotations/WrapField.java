package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapFields;
import al.logger.client.wrapper.environment.Environment;

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
    Environment[] targetMap();
    String customSrgName() default "none";
}
