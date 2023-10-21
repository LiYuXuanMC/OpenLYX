package com.reflectmc.reflect.event.annotation;

import com.reflectmc.reflect.event.Priority;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTarget {
    Priority priority() default Priority.Normal;
}
