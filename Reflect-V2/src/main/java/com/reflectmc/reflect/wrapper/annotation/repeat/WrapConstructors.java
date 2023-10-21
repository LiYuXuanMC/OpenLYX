package com.reflectmc.reflect.wrapper.annotation.repeat;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapConstructors {
    WrapConstructor[] value();
}
