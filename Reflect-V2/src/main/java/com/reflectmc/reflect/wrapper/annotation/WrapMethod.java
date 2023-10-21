package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.WrapMethods;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapMethods.class)
/**
 * Auto setup method
 */
public @interface WrapMethod {
    String deobfName();
    Environment[] targetEnvironment();
    String signature() default "none";
}
