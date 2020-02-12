package com.sxgokit.rdf.config.exception;

/**
 * 响应请求自定义统一异常
 * @author dukang
 */
public class ResponseException extends RuntimeException  {
    public ResponseException(String msg){
        super(msg);
    }
}
