package com.sxgokit.rdf.config.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Code Message定义类
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"code"})
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class CodeMessage {
    
    @XmlAttribute(name = "id", required = true)
    private String id;
    
    @XmlElement(name = "code", required = true)
    private String code;
    
    @XmlElement(name = "message", required = true)
    private String message;
    
    public CodeMessage() {
    }
    
    public CodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public CodeMessage(String id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }
}