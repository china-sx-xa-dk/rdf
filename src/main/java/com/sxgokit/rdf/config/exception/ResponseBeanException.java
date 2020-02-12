package com.sxgokit.rdf.config.exception;

import com.sxgokit.rdf.config.CodeMessageManageFactory;

/**
 * 响应请求构造返回参数异常
 * @author dukang
 */
public class ResponseBeanException extends RuntimeException  {

    private final String code;

    private final String message;

    public ResponseBeanException(String code) {
        this.code = code;
        this.message = CodeMessageManageFactory.getInstance().message(code);
    }

    /**
     * Code Message中message有占位符的使用此方法
     */
    public ResponseBeanException(String code, Object... params) {
        this.code = code;
        this.message = String.format(CodeMessageManageFactory.getInstance().message(code), params);
    }

    public ResponseBeanException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
