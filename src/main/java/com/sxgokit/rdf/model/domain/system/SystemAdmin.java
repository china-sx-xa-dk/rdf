package com.sxgokit.rdf.model.domain.system;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息Model
 *
 * @author WANGPENG
 */
@Data
public class SystemAdmin extends SystemAdminRole {
    
    private static final long serialVersionUID = 1L;
    /**
     * 登录名
     */
    @Excel(name = "登录名", width=20.0)
    private String loginName;
    /**
     * 密码
     */
    private String loginPass;
    /**
     * 管理员状态 1：启动；2：停用
     */
    @Excel(name = "管理员状态", orderNum = "1",dict = "adminState")
    private Integer adminState;
    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", orderNum = "2")
    private String adminName;
    /**
     * 身份证号
     */
    @Excel(name = "身份证号", orderNum = "3", width=25.0)
    private String adminCardno;
    /**
     * 手机
     */
    @Excel(name = "手机", orderNum = "4", width=15.0)
    private String adminMobile;
    /**
     * 性别
     */
    @Excel(name = "性别", orderNum = "5", dict="sex")
    private Integer adminSex;
    /**
     * 生日
     */
    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "6")
    private Date adminBirthday = new Date();
    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "7",width=20.0)
    private String adminEmail;
    /**
     * 登录次数
     */
    private Integer adminLoginNum;
    /**
     * 注册时间
     */
    private Date adminCreateDate;
    /**
     * 当前登录时间
     */
    private Date adminLoginDate;
    /**
     * 上次登录时间
     */
    private Date adminLoginOlddate;
    /**
     * 当前IP
     */
    private String adminLoginIp;
    /**
     * 上次IP
     */
    private String adminLoginOldip;
    /**
     * 是否删除 0:否；1：是
     */
    private Integer delflag;
    /**
     * 所属单位
     */
    private String company;
    /**
     * 单位名称
     */
    private String companyName;
    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 组织名称
     */
    private String orgName;

    public SystemAdmin(){
        super();
    }

}