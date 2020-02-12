package com.sxgokit.rdf.model.vo.system;

import com.sxgokit.rdf.model.domain.system.SysDict;
import lombok.Data;

/**
 * 数据字典 vo
 */

@Data
public class SysDictVo extends SysDict {

    /**
     * 创建者名称
     */
    private String createByName;

    /**
     * 更新者名称
     */
    private String updateByName;
}
