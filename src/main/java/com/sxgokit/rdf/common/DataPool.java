package com.sxgokit.rdf.common;

public class DataPool {

    /**
     * 已登录用户
     */
    public static final String SESSION_USER = "currentUser";


    /**
     * 登录用户权限
     */
    public static final String SESSION_USER_PER = "perList";

    /**
     * 登录的乐高用户权限
     */
    public static final String SESSION_LEGO_USER_PER = "legoPerList";

    /**
     * 已打开菜单ID
     */
    public static final String MENU_ID = "menuId";

    /**
     * 父级菜单名称
     */
    public static final String PERMISSION_FNAME = "permissionFname";

    /**
     * 子级菜单名称
     */
    public static final String PERMISSION_NAME = "permissionName";

    /**
     * 请求超时时间(毫秒)
     */
    public final static long REQUEST_TIME = 40000L;

    /**
     * 字符编码格式 目前支持 utf-8
     */
    public final static String INPUT_CHARSET = "utf-8";

    /**
     * 用户初始密码
     */
    public static final String LOGIN_PASS = "49ba59abbe56e057";


}
