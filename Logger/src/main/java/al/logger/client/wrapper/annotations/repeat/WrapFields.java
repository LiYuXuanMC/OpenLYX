package al.logger.client.wrapper.annotations.repeat;

import al.logger.client.wrapper.annotations.WrapField;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Auto setup field
 */
public @interface WrapFields {
    WrapField[] value();
}
