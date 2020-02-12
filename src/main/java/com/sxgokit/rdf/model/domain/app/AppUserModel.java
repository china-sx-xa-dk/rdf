package com.sxgokit.rdf.model.domain.app;

import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.util.Date;

/**
 * @Author: Dukang
 * @Date: 2019/5/29/0029 14:11
 * @Description: 用于app登录用户
 */
@Data
@TableName("app_user")
public class AppUserModel extends Model<AppUserModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 头像
     */
    private String headPhoto;

    /**
     * 登录名
     */
    @Excel(name = "登录名", orderNum = "0")
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 用户状态1启动2停用
     */
    @Excel(name = "用户状态", orderNum = "1",dict = "adminState")
    private Integer userState;

    /**
     * 用户名
     */
    @Excel(name = "用户名", orderNum = "2")
    private String userName;

    /**
     * 身份证
     */
    @Excel(name = "身份证", orderNum = "3",width=20.0)
    private String userCardNo;

    /**
     * 手机号
     */
    @Excel(name = "手机号", orderNum = "4",width=15.0)
    private String userMobile;

    /**
     * 用户性别1男2女
     */
    @Excel(name = "性别", orderNum = "5",dict = "sex")
    private Integer userSex;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "6",width=20.0)
    private String userEmail;

    /**
     * 用户登录次数
     */
    private Integer userLoginNum;

    /**
     * 描述
     */
    private String userDesc;

    /**
     * 最后一次登录时间
     */
    private Date userLastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     */
    private Integer delFlag;

}
