package com.reflectmc.reflect.wrapper.annotation.repeat;

import com.reflectmc.reflect.wrapper.annotation.WrapperClass;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapperClasses {
    WrapperClass[] value();
}
