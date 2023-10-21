package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapMethods;
import al.logger.client.wrapper.environment.Environment;

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
    Environment[] targetMap();
    String customSrgName() default "none";
    String signature() default "none";
}
