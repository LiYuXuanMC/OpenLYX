package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.WrapperClasses;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapperClasses.class)
public @interface WrapperClass {
    String deobfName();
    Environment[] targetEnvironment();
}
