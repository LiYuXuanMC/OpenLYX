package al.logger.client.wrapper.annotations.repeat;

import al.logger.client.wrapper.annotations.WrapEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapEnums {
    WrapEnum[] value();
}
