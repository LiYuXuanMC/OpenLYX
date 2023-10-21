package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapObjects;
import al.logger.client.wrapper.environment.Environment;

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
    Environment[] targetMap();
    String customSrgName() default "none";
}
