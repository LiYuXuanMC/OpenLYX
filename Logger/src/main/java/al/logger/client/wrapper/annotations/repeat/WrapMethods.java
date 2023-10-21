package al.logger.client.wrapper.annotations.repeat;

import al.logger.client.wrapper.annotations.WrapMethod;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapMethods {
    WrapMethod[] value();
}
