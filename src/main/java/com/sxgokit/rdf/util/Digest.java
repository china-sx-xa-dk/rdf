package com.sxgokit.rdf.util;

import com.sxgokit.rdf.util.common.ConvertUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用于appuser-token加密
 */
public class Digest
{
    public static final String ENCODE = "UTF-8";

    public static String digest(String aValue)
    {
        return digest(aValue, ENCODE);

    }

    public static String digest(String aValue, String encoding)
    {
        aValue = aValue.trim();
        byte value[];
        try
        {
            value = aValue.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        return ConvertUtils.toHex(md.digest(value));
    }
}
