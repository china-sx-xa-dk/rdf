package com.sxgokit.rdf.model.domain.system;



import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  ptconfig
 * @author liwei 2019-06-05
 * @Description: 配置表
 */
@Data
@TableName("sys_config")
public class SysConfigModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * configId
     */
    @TableId
    private String configId;

    /**
     * configKey
     */
    private String configKey;

    /**
     * configValue
     */
    private String configValue;

    /**
     * remarks
     */
    private String remarks;

    /**
     * sortNum
     */
    private Integer sortNum;

    public SysConfigModel() {
    }

}