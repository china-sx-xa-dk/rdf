/*
 * FileName：KeyWordsIntercepts.java
 * Description：
 * Copyright: Copyright (c) 2013-2020
 * Company: GOK Technology
 * Author:  zhengjian
 * Version: V100R01C02
 * Time:2015年12月25日 下午2:34:36
 */

package com.sxgokit.rdf.util.keyWordUtil;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;


/**
 * 
 * public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ParameterHandler parameterHandler,
 *     ResultHandler resultHandler, BoundSql boundSql) {
 *   ResultSetHandler resultSetHandler = new DefaultResultSetHandler(executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds);
 *   resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
 *   return resultSetHandler;
 * }
 * 
 * 根据这段源码我们可以看到
 * resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler)
 * 即mybaits对ResultSetHandler 进行重新load后才返回，因此我们可以通过编写plugin来代理resultSetHandler的一些方法
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhengjian
 * @version V100R01C02
 * @see KeyWordsIntercepts
 */

@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}))
public class KeyWordsIntercepts implements Interceptor
{

    private final static String GET_METHOD = "get";

    private final static String SET_METHOD = "set";

    @Override
    public Object intercept(Invocation invocation)
        throws Throwable
    {
        Object o = invocation.proceed();
        freightStation(o);
        return o;
    }

    @Override
    public Object plugin(Object target)
    {
        if (target instanceof ResultSetHandler)
        {
            return Plugin.wrap(target, this);
        }
        else
        {
            return target;
        }
    }

    @Override
    public void setProperties(Properties arg0)
    {}

    
    /**
     * 递归中转站，通过反射获取需要过滤的字段
     * Description: <br>
     * Time：2015年12月28日 下午5:53:55<br>
     * @author zhengjian
     * @param o
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    private static void freightStation(Object o) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if (o instanceof List && o != null)
        {
            List<?> list = (List<?>)o;
            for (int i = 0; i < list.size(); i++ )
            {
                Object bean = list.get(i);
                Field[] fieldArray = bean.getClass().getDeclaredFields();
                for (int j = 0; j < fieldArray.length; j++ )
                {
                    if (fieldArray[j].getType() == String.class)
                    {
                        String method_field = captureName(fieldArray[j].getName());
                        //获取bean的get方法
                        Method m_get = bean.getClass().getMethod(GET_METHOD + method_field);
                        //获取bean的set方法
                        Method m_set = bean.getClass().getMethod(SET_METHOD + method_field, fieldArray[j].getType());
                        replaceKeyWords(bean, m_set, m_get);
                    }else if(fieldArray[j].getType() == List.class){
                        String method_field = captureName(fieldArray[j].getName());
                        Method m_get = bean.getClass().getMethod(GET_METHOD + method_field);
                        freightStation(m_get.invoke(bean));
                    }
                }
            }
        }
    }
    
    /**
     * 首字母大写
     * Description: <br>
     * Time：2015年12月28日 下午5:16:38<br>
     * @author zhengjian
     * @param name
     * @return
     */
    public static String captureName(String name)
    {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
    
    /**
     * 替换关键字
     * Description: <br>
     * Time：2015年12月28日 下午5:50:09<br>
     * @author zhengjian
     * @param obj
     * @param m_set
     * @param m_get
     */
    public static void replaceKeyWords(Object obj, Method m_set, Method m_get){
        try
        {
            String s;
            Object o = m_get.invoke(obj);
            if(o != null){
                //s = o.toString().replace("合阳", "**");
                s = KeyWordsUtil.getEntity().replaceSensitiveWord(o.toString(), KeyWordsUtil.maxMatchType, "*");
                m_set.invoke(obj, s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
