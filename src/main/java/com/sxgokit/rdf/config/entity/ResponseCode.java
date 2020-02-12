package com.sxgokit.rdf.config.entity;

/**
 * @Author: Dukang
 * @Date: 2019/5/31/0031 14:39
 * @Description: web及app统一返回的code和msg映射类
 */
public final class ResponseCode{
    /**
     * 项目共用异常
     */
    public static final CodeMessage Server_Handle_Successful = new CodeMessage("200", "成功");
    public static final CodeMessage Server_Inner_Error = new CodeMessage("500", "服务内部异常");
    public static final CodeMessage Invalid_Parameter = new CodeMessage("1001", "无效请求参数");
    public static final CodeMessage App_Token_Failed = new CodeMessage("1002", "无效请求令牌");
    public static final CodeMessage Invalid_File_Type = new CodeMessage("1003", "文件格式无效");
    public static final CodeMessage Upload_Image_Failed = new CodeMessage("1004", "上传文件失败");
    public static final CodeMessage Access_Denied = new CodeMessage("1005", "拒绝访问");


    /**
     * 后台异常，以4开头
     */
    public static final CodeMessage Children_Is_Not_Null=  new CodeMessage("4000","子节点不为空，不能删除");
    public static final CodeMessage ROOT_CAN_NOT_EDIT = new CodeMessage("4001","根组织不能编辑！");
    public static final CodeMessage Parameter_Not_Full = new CodeMessage("4002","必填信息为空");

    /**
     * app异常，以6开头
     */
    public static final CodeMessage NOT_FOUND_USER_LOGIN_NAME = new CodeMessage("6000","用户名密码错误");
    public static final CodeMessage PASSWORD_WRONG = new CodeMessage("6001","密码错误");
    public static final CodeMessage PASSWORD_MODIFY = new CodeMessage("6002","旧密码输入错误");
}
