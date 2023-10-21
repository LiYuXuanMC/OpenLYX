package com.reflectmc.reflect.bridge.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetMethodWrapping {
    String exportName();
}
