package com.sxgokit.rdf.config.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于枚举类进行返回字典列表值
 */
@Data
public class DataDictVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字典key
     */
    private Integer key;
    /**
     * 字典值
     */
    private String value;

    public DataDictVo() { super(); }

    public DataDictVo(Integer key) { this.key = key; }

    public DataDictVo(Integer key, String value) { this.key = key; this.value = value; }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
