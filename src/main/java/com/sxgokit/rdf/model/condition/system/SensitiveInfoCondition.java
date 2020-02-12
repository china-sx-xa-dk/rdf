/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 14:55:38)
 */
package com.sxgokit.rdf.model.condition.system;

import lombok.Data;
import java.io.Serializable;

import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;

/**
 * SensitiveInfoCondition
 * @author wgl
 * @date 2019-07-29 14:55:38
 */
@Data
public class SensitiveInfoCondition extends SensitiveInfoModel implements Serializable {

    private static final long serialVersionUID = SensitiveInfoCondition.class.getName().hashCode();

    /**
     * 每页显示的记录数
     */
    private Integer showCount;

    /**
     * 当前页
     */
    private Integer currentPage;

    private String createTimeStr;
    private String updateTimeStr;
}