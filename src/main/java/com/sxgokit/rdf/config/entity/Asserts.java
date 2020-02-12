package com.sxgokit.rdf.config.entity;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * 参数校验辅助类
 * Author : secondriver
 * Date   : 2016/7/17
 */
public final class Asserts {
    
    private Asserts() {
    }
    
    public static void isTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notEmpty(Object value, String message) {
        if (value == null ||
                (value.getClass().isArray() && Array.getLength(value) == 0) ||
                (value instanceof Collection && ((Collection) value).isEmpty()) ||
                (value instanceof CharSequence && ((CharSequence) value).length() == 0)) {
            throw new IllegalArgumentException(message);
        }
    }
}