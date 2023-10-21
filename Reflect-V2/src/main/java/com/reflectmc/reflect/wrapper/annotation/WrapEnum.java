package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.WrapEnums;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapEnums.class)
/**
 * Auto setup Enum
 */
public @interface WrapEnum {
    String deobfName();
    Environment[] targetEnvironment();
}
