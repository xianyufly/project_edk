package com.aiyounet.basicFunc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.aiyounet.basicFunc.util.StringUtil;

/**
 * @describe : 日期工具类
 * @author : aiyounet.wengzq
 * @date : 2012-9-19
 * @jdk : 1.6
 * @version : 1.0
 */
public class DateUtil {

	/**
	 * "yyyy-MM-dd"
	 */
	public static final String FMT_DATE = "yyyy-MM-dd";

	public static final String FMT_DATE_HOUR = "yyyy-MM-dd HH";

	public static final String FMT_DATE_MONTH = "yyyy-MM";

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String FMT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * "HH:mm:ss"
	 */
	public static final String FMT_TIME = "HH:mm:ss";

	/**
	 * mysql 日期格式
	 */
	public static final String SQL_DATE_DAY = "%Y-%m-%d";

	public static final String SQL_DATE_HOUR = "%H";

	public static final String SQL_DATE_MONTH = "%Y-%m";
	public static final String START_TIME = " 02:00:00";
	public static final String END_TIME = " 01:59:59";

	/**
	 * 获取今天最晚时间
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getMaxDate(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}

	/**
	 * 返回一天最大日期 日期格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getMaxDate(String date) {
		return date + " 23:59:59";
	}

	/**
	 * 返回一天最大日期 日期格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getMinDate(String date) {
		return date + " 00:00:00";
	}

	/**
	 * 获取今天最早时间
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getMinDate(Date date) {
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	/**
	 * 获取当前年份
	 * 
	 * @return yyyy
	 * @throws Exception
	 */
	public static String getCurYear() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date now = new Date();
		return sdf.format(now);
	}

	/**
	 * 获取当前年份月份
	 * 
	 * @return yyyyMM
	 * @throws Exception
	 */
	public static String getCurYearMon() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date now = new Date();
		return sdf.format(now);
	}

	/**
	 * 获取当前年份月份
	 * 
	 * @param str
	 * @return
	 */
	public static String getCurYearMon(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date mydate = null;
		try {
			mydate = df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(mydate);
	}

	/**
	 * 获取当前年月日
	 * 
	 * @return yyyy-MM-dd
	 * @throws Exception
	 */
	public static String getCurYearMonDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		return sdf.format(now);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static Date strToDate(String date, String fmt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.parse(date);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static String dateToStr(Date date, String fmt) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	/**
	 * 获取两个日期间的总天数
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static int getDayNum(String beginDate, String endDate, String fmt) throws Exception {
		Date begin = strToDate(beginDate, fmt);
		Date end = strToDate(endDate, fmt);
		Calendar cBegin = Calendar.getInstance();
		cBegin.setTime(begin);
		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(end);
		return cEnd.get(Calendar.DAY_OF_YEAR) - cBegin.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取两个日期的总小时数
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static int getHourNum(String beginDate, String endDate, String fmt) throws Exception {
		Date begin = strToDate(beginDate, fmt);
		Date end = strToDate(endDate, fmt);
		Calendar cBegin = Calendar.getInstance();
		cBegin.setTime(begin);
		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(end);
		return (cEnd.get(Calendar.DAY_OF_YEAR) - cBegin.get(Calendar.DAY_OF_YEAR)) * 24;
	}

	/**
	 * 获取两个月份间的总月份数
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static int getMonthNum(String beginDate, String endDate, String fmt) throws Exception {
		Date begin = strToDate(beginDate, fmt);
		Date end = strToDate(endDate, fmt);
		Calendar cBegin = Calendar.getInstance();
		cBegin.setTime(begin);
		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(end);
		return cEnd.get(Calendar.MONTH) - cBegin.get(Calendar.MONTH);
	}

	/**
	 * 计算日期
	 * 
	 * @param date
	 *            初始日期
	 * @param num
	 *            天数
	 * @param fmt
	 *            初始日期格式
	 * @return
	 * @throws Exception
	 */
	public static String dayCompute(String date, int num, String fmt) throws Exception {
		Date tmpDate = strToDate(date, fmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmpDate);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return dateToStr(calendar.getTime(), fmt);
	}

	/**
	 * 计算日期
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            小时数
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static String hourComputer(String date, int num, String fmt) throws Exception {
		Date tmpDate = strToDate(date, fmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmpDate);
		calendar.add(Calendar.HOUR_OF_DAY, num);
		return dateToStr(calendar.getTime(), fmt);
	}

	/**
	 * 计算日期
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            分钟数
	 * @param fmt
	 *            格式
	 * @return
	 * @throws Exception
	 */
	public static String minComputer(String date, int num, String fmt) throws Exception {
		Date tmpDate = strToDate(date, fmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmpDate);
		calendar.add(Calendar.MINUTE, num);
		return dateToStr(calendar.getTime(), fmt);
	}

	/**
	 * 计算日期
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            月份数
	 * @param fmt
	 *            日期格式
	 * @return
	 * @throws Exception
	 */
	public static String monthComputer(String date, int num, String fmt) throws Exception {
		Date tmpDate = strToDate(date, fmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmpDate);
		calendar.add(Calendar.MONTH, num);
		return dateToStr(calendar.getTime(), fmt);
	}

	/**
	 * 获取当前时间 默认yyyy-MM-dd HH:mm:ss
	 * 
	 * @param fmt
	 * @return
	 */
	public static String getCurDatetime(String fmt) throws Exception {
		String fmtStr = fmt;
		if (StringUtil.isEmpty(fmt)) {
			fmtStr = FMT_DATETIME;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
		Date now = new Date();
		return sdf.format(now);
	}

	// 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	public static String nDaysAftertoday(int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return df.format(rightNow.getTime());
	}

	// 给定一个日期型字符串，返回加减n天后的日期型字符串
	public static String nDaysAfterOneDateString(String basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n) * (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);

		return df.format(tmpDate);
	}

	/**
	 * 获取某日是这年中的第几周
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Integer getWeekOfYear(String dateString) throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(df.parse(dateString));
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取昨天 默认yyyy-MM-dd HH:mm:ss
	 * 
	 * @param fmt
	 * @return
	 */
	public static String getLastDatetime(String fmt) throws Exception {
		String fmtStr = fmt;
		if (StringUtil.isEmpty(fmt)) {
			fmtStr = FMT_DATETIME;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
		Date now = new Date();
		now.setTime(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		return sdf.format(now);
	}

	public static String getTime(String fmt) throws Exception {
		String fmtStr = fmt;
		if (StringUtil.isEmpty(fmt)) {
			fmtStr = FMT_DATETIME;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
		Date now = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
		return sdf.format(now);
	}

	/**
	 * 获取当前时间 默认 HH:mm:ss
	 * 
	 * @param fmt
	 * @return
	 */
	public static String getCurtime(String fmt) throws Exception {
		String fmtStr = fmt;
		if (StringUtil.isEmpty(fmt)) {
			fmtStr = FMT_TIME;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(fmtStr);
		Date now = new Date();
		return sdf.format(now);
	}

	/**
	 * 获取当前时间周数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getWeekOfMonth() throws Exception {
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH));
	}

	public static String getWeekOfMonth(String time) throws Exception {
		SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formt.parse(time);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH));
	}

	public static String getDateHour(String time) throws Exception {
		SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formt.parse(time);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		StringBuilder sBuffer = new StringBuilder();

		// 年
		sBuffer.append(calendar.get(Calendar.YEAR) + "-");
		// 月
		sBuffer.append((calendar.get(Calendar.MONTH) + 1) + "-");
		// 天
		sBuffer.append(calendar.get(Calendar.DATE) + " ");
		// 时
		sBuffer.append(calendar.get(Calendar.HOUR_OF_DAY) + ":00:00");
		return sBuffer.toString();
	}

	/**
	 * 上半年返回 “1”,下半年返回“2”
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCurHalfYear() throws Exception {
		Calendar calendar = Calendar.getInstance();
		int curMonth = calendar.get(Calendar.MONTH);
		if (curMonth < 6) {
			return "1";
		} else {
			return "2";
		}
	}

	/**
	 * 获取当前时间 （秒数 10位）
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getTimeStamp() {
		Long now = new Date().getTime();
		return String.valueOf(now).substring(0, 10);
	}

	/**
	 * 获得上周日的日期
	 * 
	 * @return
	 */
	public static String lastSunday() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，0.本周，1下本周，-1向前推迟一周，2下周，依次类推
		int n = 0;
		String sunday;
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...） DAY_OF_WEEK
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return sunday;
	}

	/**
	 * 获得周一的日期
	 * 
	 * @return
	 */
	public static String getMonday() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，0.本周，1下本周，-1向前推迟一周，2下周，依次类推
		int n = 0;
		String sunday;
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...） DAY_OF_WEEK
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return sunday;
	}

	/**
	 * 获得上周一的日期
	 * 
	 * @return
	 */
	public static String lastMonday() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		String monday;
		cal.add(Calendar.DATE, n * 7);
		// 想周几，这里就传几Calendar.MONDAY（TUESDAY...） DAY_OF_WEEK
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return monday;
	}

	/**
	 * 获得昨天的日期格式
	 * 
	 * @return
	 */
	public static String getYesterDay() {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		cal.add(Calendar.DATE, n);
		// cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String yesterDay = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterDay;
	}

	/**
	 * 根据当天日期获得前后日期
	 * 
	 * @return
	 */
	public static String getCusterDay(int n) {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		cal.add(Calendar.DATE, n);
		// cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String yesterDay = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterDay;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Date[] getDateLists(String start, String end, int calendarType) throws Exception {
		ArrayList ret = new ArrayList();
		Date begin = strToDate(start, "yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		Date tmpDate = calendar.getTime();
		Date end2 = strToDate(end, "yyyy-MM-dd");
		long endTime = end2.getTime();

		while (tmpDate.before(end2) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(calendarType, 1);
			tmpDate = calendar.getTime();
		}
		Date[] dates = new Date[ret.size()];
		return (Date[]) ret.toArray(dates);
	}

	/**
	 * 获得两个日期的中间数
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> getDateMap(String start, String end) throws Exception {
		List<String> list = new ArrayList<String>();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = df.parse(start);
		startCalendar.setTime(startDate);
		Date endDate = df.parse(end);
		endCalendar.setTime(endDate);
		while (true) {
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
				list.add(df.format(startCalendar.getTime()));
				// map.put(df.format(startCalendar.getTime()),
				// df.format(startCalendar.getTime()));
			} else {
				break;
			}
		}
		return list;
	}

	/**
	 * 转化日期格式2012-02-05 23:59:59为2012-02-05
	 * 
	 * @param strDate
	 *            格式为yyyy-MM-dd HH:mm:ss 的日期字符串
	 * @return 格式为yyyy-MM-dd的日期字符串
	 */
	public static String parseDateEx(String strDate) {
		if (strDate.length() > 12) {
			StringBuffer buffer = new StringBuffer("");
			buffer.append(strDate.substring(0, 10));
			strDate = buffer.toString();
		}
		return strDate;
	}

	/***
	 * 转换 00:00:00 格式的时间为秒
	 * 
	 * @param time
	 * @return
	 */
	public static Integer changeTimeForSeconds(String time) {
		String[] times = time.split(":");
		int hours = Integer.parseInt(times[0]);
		int minite = Integer.parseInt(times[1]);
		int seconds = Integer.parseInt(times[2]);
		return hours * 3600 + minite * 60 + seconds;
	}

	/**
	 * 获取上月末日期
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static String getLastMonth(String formater) {
		if (formater == null) {
			formater = "yyyy-MM-dd HH:mm:ss";
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		// 得到一个月最后一天日期(31/30/29/28)
		int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 按你的要求设置时间
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		String gtime = sdf.format(c.getTime()); // 上月最后一天
		return gtime;
	}

	public static Long getCurTimeStamp() {
		return new Date().getTime();
	}

	public static Long getLongTime(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(time).getTime();
	}

	public static String getStartTime(String startTime) throws Exception {
		if (StringUtil.isNull(startTime)) {
			startTime = DateUtil.getCurDatetime(null);
		}
		String str[] = startTime.split(" ");
		if (str.length == 1) {
			startTime = startTime + " 02:00:01";
		}
		String tem = startTime.split(" ")[0] + " 02:00:00";
		if (startTime.compareTo(tem) >= 0) {
			return tem;
		} else {
			return dayCompute(startTime, -1, "yyyy-MM-dd") + " 02:00:00";
		}
	}

	public static String getStartTime2(String startTime) throws Exception {
		if (StringUtil.isNull(startTime)) {
			startTime = DateUtil.getCurDatetime(null);
			String nowBegin = DateUtil.getCurDatetime("yyyy-MM-dd");
			if (startTime.compareToIgnoreCase(nowBegin + " 00:00:00") >= 0
					&& startTime.compareToIgnoreCase(nowBegin + " 02:59:59") <= 0) {
				startTime = DateUtil.getLastDatetime("yyyy-MM-dd");
			}
		}
		String str[] = startTime.split(" ");
		if (str.length == 1) {
			startTime = startTime + " 02:00:01";
		}
		String tem = startTime.split(" ")[0] + " 02:00:00";
		if (startTime.compareTo(tem) >= 0) {
			return tem;
		} else {
			return dayCompute(startTime, -1, "yyyy-MM-dd") + " 02:00:00";
		}
	}

	public static String getEndTime(String endTime) throws Exception {
		if (StringUtil.isNull(endTime)) {
			endTime = DateUtil.getCurDatetime(null);
		}
		String str[] = endTime.split(" ");
		if (str.length == 1) {
			endTime = endTime + " 23:59:59";
		}
		String tem = endTime.split(" ")[0] + " 01:59:59";
		if (endTime.compareTo(tem) <= 0) {
			return tem;
		} else {
			return dayCompute(endTime, 1, "yyyy-MM-dd") + " 01:59:59";
		}
	}

	public static String getEndTime2(String endTime) throws Exception {
		if (StringUtil.isNull(endTime)) {
			endTime = DateUtil.getCurDatetime(null);
			String nowBegin = DateUtil.getCurDatetime("yyyy-MM-dd");
			if (endTime.compareToIgnoreCase(nowBegin + " 00:00:00") >= 0
					&& endTime.compareToIgnoreCase(nowBegin + " 02:59:59") <= 0) {
				endTime = DateUtil.getLastDatetime("yyyy-MM-dd");
			}
		}
		String str[] = endTime.split(" ");
		if (str.length == 1) {
			endTime = endTime + " 23:59:59";
		}
		String tem = endTime.split(" ")[0] + " 01:59:59";
		if (endTime.compareTo(tem) <= 0) {
			return tem;
		} else {
			return dayCompute(endTime, 1, "yyyy-MM-dd") + " 01:59:59";
		}
	}

	public static String MonthCountNum(String dateTime, Integer monthNum) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, monthNum);

		Date aimDate = cal.getTime();
		return sdf.format(aimDate);
	}

	public static String MonthDay(String dateTime, Integer dayNum) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, dayNum);

		Date aimDate = cal.getTime();
		return sdf.format(aimDate);
	}

	public static String getDaysString(String endDate, int day) {
		if(StringUtil.isNullString(endDate)){
			try {
				endDate = getCurDatetime("yyyy-MM-dd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] days = new String[day];
		String d = "";
		for (int i = 0; i < days.length; i++) {
			String s = nDaysAfterOneDateString(endDate, i);
			if (i != 0) {
				d = d + "," + s.substring(5, 10);
			}else{
				d = d + s.substring(5, 10);
			}
		}
		return d;
	}
	public static String[] getDays(String endDate, int day) {
		if(StringUtil.isNullString(endDate)){
			try {
				endDate = getCurDatetime("yyyy-MM-dd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] days = new String[day];
		for (int i = 0; i < days.length; i++) {
			String s = nDaysAfterOneDateString(endDate, i);
			days[i] = s;
		}
		return days;
	}
	public static void main(String[] args) throws Exception {

		System.out.println(getDaysString(null, 30));
	}

}
