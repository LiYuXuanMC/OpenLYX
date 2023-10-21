package al.logger.client.utils.obfuscate.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportObfuscate {
    String name();
}
