package com.sxgokit.rdf.util.common;

/**
 *获取随机数工具类
 */
public final class RandomUtils
{
    private RandomUtils()
    {
        super();
    }

    /**
     * 获取3位随机数
     * @return
     */
    public static String random3()
    {

        int i = (int)(Math.random() * 900 + 100);
        return i + "";
    }

    /**
     * 获取4位随机数
     * @return
     */
    public static String random4()
    {

        int i = (int)(Math.random() * 9000 + 1000);
        return i + "";
    }

    /**
     * 获取5位随机数
     * @return
     */
    public static String random5()
    {

        int i = (int)(Math.random() * 90000 + 10000);
        return i + "";
    }

    /**
     * 获取6位随机数
     * @return
     */
    public static String random6()
    {
        int i = (int)(Math.random() * 900000 + 100000);
        return i + "";
    }
}