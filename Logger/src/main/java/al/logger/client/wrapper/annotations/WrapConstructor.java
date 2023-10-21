package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapConstructors;
import al.logger.client.wrapper.environment.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapConstructors.class)
public @interface WrapConstructor {
    Environment[] targetMap();
    Class<?>[] signature() default {};
}
