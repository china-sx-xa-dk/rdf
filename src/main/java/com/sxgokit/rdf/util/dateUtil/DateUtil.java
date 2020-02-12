package com.sxgokit.rdf.util.dateUtil;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import sun.applet.Main;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 常用时间处理工具类
 */
public class DateUtil implements Serializable {

	private static final long serialVersionUID = 5433415117564094621L;

	/**
	 * 把String类型时间转换为Date类型
	 * 
	 * @param strDate
	 *            带转换字符串
	 * @return 返回Date类型的时间
	 *  例如：starDate = '2015-09-24 15:28:22'
	 * @throws ParseException
	 */
	public static Date converStringToDate(String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(strDate);
		return date;

	}

	/**
	 * 把Date类型转换为String类型
	 * 
	 * @param date
	 *            将要转化的时间
	 * @return
	 * @throws ParseException
	 *  返回时间格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String converDateToString(Date date) throws ParseException {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 判断时间是否是当天
	 * 
	 * @param date
	 * @return true:是同一天；false：不是同一天
	 */
	public static boolean isDateThisDay(Date date) {
		String _date = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String _thisDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (_date.equals(_thisDay)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前时间字符串
	 * 
	 * @param date 将要转化的时间  
	 * 		  pattern 时间字符串格式
	 * 
	 * @return String 指定格式的时间字符串
	 */
	public static String getDateString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}


	/**
	 * 判断当前是星期几
	 * @return
	 */
	public static int dayForWeek() {
		Integer week = null;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
			case 1:
				week = 7;
				break;
			case 2:
				week = 1;
				break;
			case 3:
				week = 2;
				break;
			case 4:
				week = 3;
				break;
			case 5:
				week = 4;
				break;
			case 6:
				week = 5;
				break;
			case 7:
				week = 6;
				break;
		}
		return week;
	}

	/**
	 * 格式化当前系统时间为yyyy-MM-dd HH:mm:ss
	 * */
	public static String formatNowDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 *格式化当前系统时间为yyyy
	 */
	public static String formatNowYear(){
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	/**
	 *格式化当前系统时间为yyyy
	 */
	public static String formatNowYearMonth(){
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}

	/**
	 *格式化当前系统时间为yyyy-MM-dd HH:mm
	 */
	public static String formatNowYearMonthDay(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}


	/**
	 * 获取当前时间30天之前的时间
	 * @return
	 */
	public static String getDateAddDays(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, - 30);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}

	/**
	 * 获取当年的第一天
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrYearFirst() throws ParseException{
		Calendar currCal=Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 * @throws ParseException
	 */
	public static String getYearFirst(int year) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date date = calendar.getTime();
		String currYearLast = converDateToString(date);
		return currYearLast;
	}

	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 * @throws ParseException
	 */
	public static String getYearLast(int year) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
		String currYearLast = converDateToString(date);
		return currYearLast;
	}
	/**
	 * yyyy-MM-dd HH:mm:ss Description: <br> Time：2015年3月31日 上午1:11:23<br>
	 *
	 * @author zhangliang
	 * @param format
	 * @param dateStr
	 * @return
	 */
	public static Date formatString(String format, String dateStr)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 获取某月的第一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthFirst(Date date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDayOfMonth = calendar.getTime();
		return converDateToString(firstDayOfMonth);
	}

	/**
	 * 把String类型时间转换为Date类型   yyyy-MM
	 * @param strDate
	 *            带转换字符串
	 * @return 返回Date类型的时间
	 *  例如：starDate = '2015-09-24 15:28:22'
	 * @throws ParseException
	 */
	public static Date converStringToYearMonth(String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = format.parse(strDate);
		return date;
	}

	/**
	 * 把String类型时间转换为Date类型  yyyy-MM-dd
	 *
	 * @param strDate
	 *            带转换字符串
	 * @return 返回Date类型的时间
	 *  例如：starDate = '2015-09-24 15:28:22'
	 * @throws ParseException
	 */
	public static Date converStringToYearMonthDay(String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(strDate);
		return date;
	}

	/**
	 * 获取某月的最后一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthLast(Date date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		return converDateToString(lastDayOfMonth);
	}

	/**
	 * 获取当天的开始时间
	 * @return
	 * @throws ParseException
	 */
	public static String getNowDayHouerFirst() throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return converDateToString(calendar.getTime());
	}

	/**
	 * 获取某天的开始时间
	 * @return
	 * @throws ParseException
	 */
	public static String getDayHouerFirst(Date date) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return converDateToString(calendar.getTime());
	}

	/**
	 * 获取某天的结束时间
	 * @return
	 * @throws ParseException
	 */
	public static String getDayHouerLast(Date date) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return converDateToString(calendar.getTime());
	}

	/**
	 * 根据传入的两个日期获取中间所有的日期集合
	 * @param dBegin
	 * @param dEnd
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateBetweenList(String dBegin,String dEnd) throws Exception
	{
		List<String> list = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(dBegin == null || dEnd == null)
		{
			return null;
		}

		Date startDate = dateFormat.parse(dBegin);
		Date endDate = dateFormat.parse(dEnd);
		if (startDate.getTime () > endDate.getTime ())
		{
			startDate = dateFormat.parse(dEnd);
			endDate = dateFormat.parse(dBegin);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		list.add (dateFormat.format(startDate));
		while(calendar.getTime().before(endDate))
		{
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			list.add (dateFormat.format(calendar.getTime()));
		}
		return list;
	}


	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 *
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	public static List<Date> getAssembleDateInfo(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}

	/**
	 * 传入时间获取相差的时间
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime,
								String format, String str) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒

			if (str.equalsIgnoreCase("h")) {
				return hour;
			} else {
				return min;
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		if (str.equalsIgnoreCase("h")) {
			return hour;
		} else {
			return min;
		}
	}

	public static void main(String[] args) throws Exception{
		String startDate = "2019-07-10";
		String endDate = "2019-07-19";
		List<String> list = getDateBetweenList(startDate,endDate);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
