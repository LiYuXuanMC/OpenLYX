package al.logger.client.wrapper.annotations.repeat;

import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapperClasses {
    WrapperClass[] value();
}
