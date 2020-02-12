/*
 * FileName：GsonUtil.java Description： Copyright: Copyright (c) 2013-2020 Company: GOK Technology
 * Author: yangmei Version: V100R01C04 Time:2014年7月20日 下午6:06:14
 */

package com.sxgokit.rdf.util.treeUtil;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


public class GsonUtil
{
    private static Gson specialVersionGson = new GsonBuilder().setVersion(1.2).create();

    private static Gson gson = new Gson();

    private GsonUtil()
    {}

    public static String toVersionJson(Object obj)
    {
        return specialVersionGson.toJson(obj);
    }

    public static String toJson(Object obj)
    {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> c)
    {
        try
        {
            return gson.fromJson(json, c);
        }
        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
