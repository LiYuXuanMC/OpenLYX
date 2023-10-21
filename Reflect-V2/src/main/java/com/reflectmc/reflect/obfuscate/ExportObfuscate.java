package com.reflectmc.reflect.obfuscate;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportObfuscate {
    String name();
}
