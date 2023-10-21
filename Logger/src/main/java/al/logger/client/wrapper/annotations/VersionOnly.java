package al.logger.client.wrapper.annotations;

import al.logger.client.wrapper.annotations.repeat.WrapFields;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VersionOnly {
    String ver();
}
