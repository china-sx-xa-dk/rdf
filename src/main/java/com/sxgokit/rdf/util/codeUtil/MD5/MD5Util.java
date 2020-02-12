package com.sxgokit.rdf.util.codeUtil.MD5;


import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Util
{

    /**
     * MD5字符串加密 16位小写
     * @param sourceStr
     * @param charset
     * @return
     * @throws Exception
     */
    public static String MD5_16_LowerCase(String sourceStr, String charset)
            throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sourceStr.getBytes(charset));
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++ )
        {
            i = b[offset];
            if (i < 0)
            {
                i += 256;
            }
            if (i < 16)
            {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().substring(8, 24);
    }

    /**
     * MD5字符串加密 16位大写
     * @param sourceStr
     * @param charset
     * @return
     * @throws Exception
     */
    public static String MD5_16_UpperCase(String sourceStr, String charset)
            throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sourceStr.getBytes(charset));
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++ )
        {
            i = b[offset];
            if (i < 0)
            {
                i += 256;
            }
            if (i < 16)
            {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().substring(8, 24).toUpperCase();
    }

    /**
     * MD5字符串加密 32位小写
     * @param sourceStr
     * @param charset
     * @return
     * @throws Exception
     */
    public static String MD5_32_LowerCase(String sourceStr, String charset)
            throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sourceStr.getBytes(charset));
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++ )
        {
            i = b[offset];
            if (i < 0)
            {
                i += 256;
            }
            if (i < 16)
            {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * MD5字符串加密 32位大写
     * @param sourceStr
     * @param charset
     * @return
     * @throws Exception
     */
    public static String MD5_32_UpperCase(String sourceStr, String charset)
            throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sourceStr.getBytes(charset));
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++ )
        {
            i = b[offset];
            if (i < 0)
            {
                i += 256;
            }
            if (i < 16)
            {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().toUpperCase();
    }

    public static void main(String[] args)
    {
        String str = "123456";
        try
        {
            str = MD5_16_LowerCase(str,"utf-8");
            //str = MD5_16_UpperCase(str,"utf-8");
            //str = MD5_32_LowerCase(str,"utf-8");
            //str = MD5_32_UpperCase(str,"utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
