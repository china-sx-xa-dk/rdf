/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 14:55:38)
 */
package com.sxgokit.rdf.model.vo.system;

import lombok.Data;
import java.io.Serializable;

import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;

/**
 * SensitiveInfoVo
 * @author wgl
 * @date 2019-07-29 14:55:38
 */
@Data
public class SensitiveInfoVo extends SensitiveInfoModel implements Serializable {

    private static final long serialVersionUID = SensitiveInfoVo.class.getName().hashCode();

    private String createUserName;
    private String updateUserName;

}