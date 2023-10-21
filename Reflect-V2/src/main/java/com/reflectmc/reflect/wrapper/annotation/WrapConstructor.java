package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.WrapConstructors;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapConstructors.class)
public @interface WrapConstructor {
    Environment[] targetEnvironment();
    Class<?>[] signature() default {};
}
