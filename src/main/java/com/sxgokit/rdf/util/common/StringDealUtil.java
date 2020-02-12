package com.sxgokit.rdf.util.common;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串截取/处理工具类
 */
public class StringDealUtil
{
    /**
     * Description: 获取字符串的后几位并返回<br>
     * Time：2017年2月7日 上午9:25:06<br>
     * @author wx
     * @param str 原始字符串
     * @param num 需要截取的位数
     * @return 按照位数截取后的字符串
     */
    public static String getLastNumStr(String str, Integer num)
    {
        if (StringUtils.isNotEmpty(str) && str.length() > num)
        {
            return str.substring(str.length() - num);
        }
        else
        {
            return str;
        }
    }

    /**
     * Description: 获取字符串的后4位并返回<br>
     * Time：2017年2月7日 上午9:25:06<br>
     * @author wx
     * @param str 原始字符串
     * @return 按照位数截取后的字符串
     */
    public static String getLast4Str(String str)
    {
        return getLastNumStr(str, 4);
    }

    /**
     * Description: 获取字符串中需要显示字符串，其余的字符用*代替 并返回<br>
     * Time：2017年2月7日 上午9:25:06<br>
     * @author wx
     * @param str 原始字符串
     * @param num 需要显示的字符个数
     * @param numFlg 需要显示的字符的截取方式：0：从字符串的后面截取（默认）, 1：从字符串的前面截取
     * @return 按照显示的位数处理之后的的字符串：显示截取之后的字符串，其余的字符用*代替
     */
    public static String getDisNumStr(String str, Integer num, Integer numFlg)
    {
        if (StringUtils.isNotEmpty(str) && str.length() > num)
        {
            if (numFlg == 1)
            {
                return str.substring(0, num)
                       + createAsterisk(str.length() - num);
            }
            else
            {
                return createAsterisk(str.length() - num)
                       + str.substring(str.length() - num);
            }
        }
        else
        {
            return str;
        }
    }

    /**
     * Description: 获取字符串的后4位, 其余的字符用*代替 并返回<br>
     * Time：2017年2月7日 上午9:25:06<br>
     * @author wx
     * @param str 原始字符串
     * @return 显示截取后4位之后的字符串，其余的字符用*代替
     */
    public static String getDisLast4Str(String str)
    {
        return getDisNumStr(str, 4, 0);
    }

    /**
     * Description: 中文只展示第一个汉字，后面的全部隐藏<br>
     * Time：2017年2月7日 上午9:25:06<br>
     * @author wx
     * @param str 原始字符串(只能是中文)
     * @param num 需要截取的位数
     * @return
     */
    public static String getFirstStr(String str)
    {
        if (str.length() <= 1)
        {
            //            System.out.println("*");
            return str;
        }
        else
        {
            //            System.out.println(str.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)",
            //                "$1" + createAsterisk(str.length() - 1)));
            return str.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)",
                "$1" + createAsterisk(str.length() - 1));
        }
    }

    //生成很多个*号  
    public static String createAsterisk(int length)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++ )
        {
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(getLastNumStr("爱新觉罗·赵老大", 4));
        System.out.println(getFirstStr("爱新觉罗·赵老大"));
        System.out.println(getFirstStr("12132affaf新觉罗·赵老大"));
        System.out.println(getFirstStr("adad123456"));
        System.out.println(getDisNumStr("爱新觉罗·赵老大", 3, 1));
        System.out.println(getDisNumStr("爱新觉罗·赵老大", 3, 0));
        System.out.println(getDisNumStr(null, 4, 0));
    }
}
