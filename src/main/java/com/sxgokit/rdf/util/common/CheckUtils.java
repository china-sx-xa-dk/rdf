package com.sxgokit.rdf.util.common;

/**
 * 常用格式检验工具类
 */
public final class CheckUtils
{

    private CheckUtils()
    {
        super();

    }

    /**
     * 验证身份证号码
     * @param cardNum
     * @return
     */
    public static boolean checkCardNum(String cardNum)
    {
        if (null == cardNum)
        {
            return false;
        }
        cardNum = cardNum.toUpperCase();
        String regex = "([0-9]{17}([0-9]|X))|([0-9]{15})";
        return cardNum.matches(regex);
    }

    /**
     * 验证手机号码
     * @param mobileNum
     * @return
     */
    public static boolean checkMobile(String mobileNum)
    {
        if (null == mobileNum)
        {
            return false;
        }
        String regex = "(1)\\d{10}";
        return mobileNum.matches(regex);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email)
    {
        if (null == email)
        {
            return false;
        }
        String regex = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
        return email.matches(regex);
    }
}
