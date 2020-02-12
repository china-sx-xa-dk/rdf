package com.sxgokit.rdf.util.numUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数值格式化工具类
 */
public class NumFormat
{
    /**
     * 数值转换(小于10000的不转换，大于10000的转换为1万)
     * @param num
     * @return
     */
    public static String numFormat(Integer num)
    {
        String str = "";
        if (num != null)
        {
            int n = num.intValue();
            if (n < 10000)
            {
                str = "" + n;
            }
            else
            {
                double nd = (double)n / 10000;
                nd = formatDouble(nd);
                str = nd + "万";
            }
        }
        return str;
    }

    public static double formatDouble(double d)
    {
        BigDecimal bg = new BigDecimal(d).setScale(1, RoundingMode.DOWN);  
        return bg.doubleValue();  
    }

    public static void main(String[] args)
    {
        Integer a = 15623;
        String str = numFormat(a);
        System.out.println(str);
    }

}