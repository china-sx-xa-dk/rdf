package com.sxgokit.rdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: dukang
 * 自定义注解
 * 类加此注解类中所有方法都变为免登陆
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenAnnotationController {
    boolean value() default true;
}
