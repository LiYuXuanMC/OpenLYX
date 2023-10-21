package com.reflectmc.reflect.wrapper.annotation;

import com.reflectmc.reflect.wrapper.annotation.repeat.WrapObjects;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WrapObjects.class)
/**
 * Auto setup field
 */
public @interface WrapObject {
    String deobfName();
    Environment[] targetEnvironment();
}
