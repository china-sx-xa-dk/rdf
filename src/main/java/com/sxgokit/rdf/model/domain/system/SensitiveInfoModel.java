/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 14:55:38)
 */
package com.sxgokit.rdf.model.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * SensitiveInfoModel
 * @author wgl
 * @date 2019-07-29 14:55:38
 */
@Data
@TableName("sensitive_info")
public class SensitiveInfoModel extends Model<SensitiveInfoModel> implements Serializable {

    private static final long serialVersionUID = SensitiveInfoModel.class.getName().hashCode();

    /** 主键 */
    @TableId(type = IdType.AUTO)
    @Excel(name = "主键", orderNum = "0",width=20.0)
    private Integer id;

    /** 敏感词内容 */
    @Excel(name = "敏感词内容", orderNum = "1",width=20.0)
    private String sensitiveContent;

    /** 替换内容 */
    @Excel(name = "替换内容", orderNum = "2",width=20.0)
    private String replaceContent;

    /** 启用状态（1启用；2禁用） */
    @Excel(name = "启用状态（1启用；2禁用）", orderNum = "3",width=20.0)
    private Integer state;

    /** 排序号 */
    @Excel(name = "排序号", orderNum = "4",width=20.0)
    private Integer sortnum;

    /** 删除状态（0未删除；1已删除） */
    @Excel(name = "删除状态（0未删除；1已删除）", orderNum = "5",width=20.0)
    private Integer delFlag;

    /** 创建人 */
    @Excel(name = "创建人", orderNum = "6",width=20.0)
    private Integer createUser;

    /** 创建时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "创建时间", orderNum = "7",width=20.0)
    private Date createTime;

    /** 更改人 */
    @Excel(name = "更改人", orderNum = "8",width=20.0)
    private Integer updateUser;

    /** 更改时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "更改时间", orderNum = "9",width=20.0)
    private Date updateTime;

    /** 备注Int1 */
    @Excel(name = "备注Int1", orderNum = "10",width=20.0)
    private Integer remarkInt1;

    /** 备注Int2 */
    @Excel(name = "备注Int2", orderNum = "11",width=20.0)
    private Integer remarkInt2;

    /** 备注Int3 */
    @Excel(name = "备注Int3", orderNum = "12",width=20.0)
    private Integer remarkInt3;

    /** 备注Str1 */
    @Excel(name = "备注Str1", orderNum = "13",width=20.0)
    private String remarkStr1;

    /** 备注Str2 */
    @Excel(name = "备注Str2", orderNum = "14",width=20.0)
    private String remarkStr2;

    /** 备注Str3 */
    @Excel(name = "备注Str3", orderNum = "15",width=20.0)
    private String remarkStr3;


}