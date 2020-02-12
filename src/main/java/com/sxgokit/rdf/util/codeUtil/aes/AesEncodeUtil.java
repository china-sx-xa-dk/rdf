package com.sxgokit.rdf.util.codeUtil.aes;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * Aes加密解密工具类，密匙为16位
 */
public class AesEncodeUtil
{
    public static void main(String[] args)
        throws Exception
    {
        String content = "869059";
        System.out.println("加密前：" + content);

        String key = "IIGfMA0GCSqGSIb3";
        System.out.println("加密密钥和解密密钥：" + key);

        String encrypt = aesEncrypt(content, key);
        System.out.println("加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, key);
        System.out.println("解密后：" + decrypt);
    }

    /**
     * ASE加密
     * @param str
     * @param key
     * @return
     */
    public static String aesEncrypt(String str, String key)
    {
        try
        {
            if (str == null || key == null) return null;
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
            return new BASE64Encoder().encode(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES解密
     * @param str
     * @param key
     * @return
     */
    public static String aesDecrypt(String str, String key)
    {
        try
        {
            if (str == null || key == null) return null;
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
