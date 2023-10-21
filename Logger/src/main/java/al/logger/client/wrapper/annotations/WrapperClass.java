package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapperClasses;
import al.logger.client.wrapper.environment.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapperClasses.class)
public @interface WrapperClass {
    String mcpName();
    Environment[] targetMap();

}
