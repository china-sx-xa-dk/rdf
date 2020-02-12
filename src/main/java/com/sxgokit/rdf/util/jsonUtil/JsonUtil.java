package com.sxgokit.rdf.util.jsonUtil;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JSON字符串转换工具类
 */
public final class JsonUtil
{
    private static Gson gson = new Gson();

    private static Gson gsonInter = new GsonBuilder().setDateFormat(
        "yyyy-MM-dd HH:mm:ss").create();

    private JsonUtil()
    {
        super();
    }

    public static String toJson(Object obj)
    {
        return gson.toJson(obj);
    }

    public static String toJsonInter(Object obj)
    {
        return gsonInter.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> cls)
    {
        return gson.fromJson(json, cls);
    }
    
    /**
     * 
     * Description: json字符串转List
     * Time：2018年4月13日 下午5:04:24<br>
     * @author wgl
     * @param jsonarr
     * @param x
     * @return
     */
    public static <T> List<T> getObjectList(String jsonarr, Class<T> x) {
        List<T> objList=new ArrayList<T>();
        JSONArray jsonArray = new JSONArray();
        if (jsonarr != null && !"".equals(jsonarr)) {
            jsonArray = JSONArray.fromObject(jsonarr);
        }
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T obj = (T) JSONObject.toBean(jsonObject, x);
                objList.add(obj);
            }
        }
        return objList;
    }

    /**
     * 
     * Description: json字符串转对象
     * Time：2018年4月13日 下午5:04:41<br>
     * @author wgl
     * @param jsonarr
     * @param x
     * @return
     */
    public static <T> T getObject(String jsonarr, Class<T> x) {

        JSONObject jsonObject = new JSONObject();
        if (jsonarr != null && !"".equals(jsonarr)) {
            jsonObject = JSONObject.fromObject(jsonarr);
        }
        T t = (T) JSONObject.toBean(jsonObject, x);
        return t;
    }

}
