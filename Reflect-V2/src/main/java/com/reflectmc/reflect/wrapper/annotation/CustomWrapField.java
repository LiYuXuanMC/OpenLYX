package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.CustomWrapFields;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CustomWrapFields.class)
public @interface CustomWrapField {
    String customName();
    Environment[] targetEnvironment();
}
