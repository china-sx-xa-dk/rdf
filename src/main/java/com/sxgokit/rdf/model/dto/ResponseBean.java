package com.sxgokit.rdf.model.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * ResponseBean
 * @date 2018/8/30 11:39
 */
@Data
public  class ResponseBean implements Serializable {

    /**
     * HTTP状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public ResponseBean() {
    }

    public ResponseBean(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
