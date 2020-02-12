package com.sxgokit.rdf.model.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 *  sys_log_operate
 * @author liwei 2019-06-11
 * @Description: 系统日志
 */
@Data
@TableName("sys_log_operate")
public class SysLogOperateModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 日志id
     */
    private String logId;

    /**
     * 访问用户id
     */
    private String userId;

    /**
     * 社区编码
     */
    private Integer communityId;

    /**
     * 功能id
     */
    private String funcId;

    /**
     * 模块名称
     */
    private String funcSysName;

    /**
     * 功能名称
     */
    private String funcName;

    /**
     * 操作路径
     */
    private String pageUrl;

    /**
     * 操作ip
     */
    private String operateIp;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 访问描述
     */
    private String operateDesc;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 访问类型 登录、登出、功能
     */
    private String visitType;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    public SysLogOperateModel() {
    }
}
