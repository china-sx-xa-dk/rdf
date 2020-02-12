package com.sxgokit.rdf.config.entity;

import com.sxgokit.rdf.config.interf.CodeMessageSource;

/**
 * Author: ZhangXiao
 * Created: 2017/6/26
 */
public final class CodeMessageSourceFactory {
    
    public static CodeMessageSource create() {
        return new ConstantCodeMessage();
    }
}
