package com.sxgokit.rdf.util.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 根据传入时间获取相对时间
 */
public class RelativeDateFormat
{
    private static final long ONE_MINUTE = 60000L;  
    private static final long ONE_HOUR = 3600000L;  
    private static final long ONE_DAY = 86400000L;  
    private static final long ONE_WEEK = 604800000L;  
  
    private static final String ONE_SECOND_AGO = "秒前";  
    private static final String ONE_MINUTE_AGO = "分钟前";  
    private static final String ONE_HOUR_AGO = "小时前";  
    private static final String ONE_DAY_AGO = "天前";  
    private static final String ONE_MONTH_AGO = "月前";  
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * 根据传入时间获取相对时间，几分前、几天前、几月前
     * @param date
     * @return
     */
    public static String format(Date date) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //以20180523格式获取当前日与传入日，用于判断是否属于同一天
        String dateNowStr = sdf.format(new Date());
        String dateOldStr = sdf.format(date);
        //以2018-05-23格式获取当前日用以拼接当前日期
        SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStrRel = sdfNew.format(new Date());
        //拼接当前日期最大时间，避免因毫秒计算时无法准确判断为昨天或今天
        String nowDayStr = dateNowStrRel+" 23:59:59";
        Date nowDayDate = null;
        try
        {
            //获取当前日期最大时间
            SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            nowDayDate = sdfAll.parse(nowDayStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        long delta = 0L;
        if(dateNowStr.equals(dateOldStr)){
            delta = new Date().getTime() - date.getTime();  
        }else{
            delta = nowDayDate.getTime() - date.getTime();  
        }
        if (delta < 1L * ONE_MINUTE) {  
            long seconds = toSeconds(delta);  
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;  
        }  
        if (delta < 45L * ONE_MINUTE) {  
            long minutes = toMinutes(delta);  
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;  
        }  
        if (delta < 24L * ONE_HOUR) {  
            long hours = toHours(delta);  
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;  
        }  
        if (delta < 48L * ONE_HOUR) {  
            return "昨天";  
        }  
        if (delta < 30L * ONE_DAY) {  
            long days = toDays(delta);  
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;  
        }  
        if (delta < 12L * 4L * ONE_WEEK) {  
            long months = toMonths(delta);  
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;  
        } else {  
            long years = toYears(delta);  
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;  
        }  
    }  
  
    private static long toSeconds(long date) {  
        return date / 1000L;  
    }  
  
    private static long toMinutes(long date) {  
        return toSeconds(date) / 60L;  
    }  
  
    private static long toHours(long date) {  
        return toMinutes(date) / 60L;  
    }  
  
    private static long toDays(long date) {  
        return toHours(date) / 24L;  
    }  
  
    private static long toMonths(long date) {  
        return toDays(date) / 30L;  
    }  
  
    private static long toYears(long date) {  
        return toMonths(date) / 365L;  
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2017-05-22 19:50:35");
        System.out.println(format(date));
    }
}
