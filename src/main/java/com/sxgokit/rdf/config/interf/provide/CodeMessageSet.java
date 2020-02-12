package com.sxgokit.rdf.config.interf.provide;

import com.sxgokit.rdf.config.entity.CodeMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Author: ZhangXiao
 * Created: 2017/7/4
 */
@XmlRootElement(name = "code_message_set")
@XmlAccessorType(value = XmlAccessType.FIELD)
@Getter
@Setter
@ToString
class CodeMessageSet {

    @XmlElement(name = "code_message")
    private List<CodeMessage> codeMessageList;

}

