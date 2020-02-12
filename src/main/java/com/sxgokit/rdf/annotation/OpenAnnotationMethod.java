package com.sxgokit.rdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: dukang
 * 自定义注解
 * 方法加此注解为免登录可访问接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenAnnotationMethod {

    boolean value() default true;
}
