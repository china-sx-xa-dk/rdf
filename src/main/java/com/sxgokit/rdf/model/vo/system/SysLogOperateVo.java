package com.sxgokit.rdf.model.vo.system;


import com.sxgokit.rdf.model.domain.system.SysLogOperateModel;
import lombok.Data;

/**
 * 返回前端的自定义配置类
 */
@Data
public class SysLogOperateVo extends SysLogOperateModel {
    /**
     * 操作用户账户（登录名）
     */
    private String loginName;
}
