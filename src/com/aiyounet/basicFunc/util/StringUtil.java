package com.aiyounet.basicFunc.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtil {

	/**
	 * 字符串非空 不为null 且内容不为“”
	 * 
	 * @param str
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isEmpty(String str) throws Exception {
		if (str != null) {
			if (str.trim().equals("")) {
				return true;
			}
			return false;
		}
		return true;
	}

	public static boolean isNull(Object obj) {
		if (null != obj && !obj.equals("")) {
			return false;
		} else {
			return true;
		}

	}
	public static boolean isNullString(String obj) {
		if (null != obj && !obj.trim().equals("")) {
			return false;
		} else {
			return true;
		}

	}
	/**
	 * 
	 * @Description: 转成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		if (obj == null)
			return "";
		return obj.toString();
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @return
	 * @describe 日期格式 yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
	public static String dateToStr(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Date strToDate(String date) throws Exception {
		if (isEmpty(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}

	/**
	 * 去掉字符串的分隔符，返回数组
	 * 
	 * @param string
	 * @param c
	 * @return
	 */
	public static String[] stringAnalytical(String string, char c) {
		// 字符串中分隔符的个数
		int count = 0;

		// 如果不含分割符则返回字符本身
		if (string.indexOf(c) == -1) {
			return new String[] { string };
		}
		char[] cs = string.toCharArray();
		// 过滤掉第一个和最后一个是分隔符的情况
		for (int i = 1; i < cs.length - 1; i++) {
			if (cs[i] == c) {
				count++; // 得到分隔符的个数
			}
		}
		String[] strArray = new String[count + 1];
		int k = 0, j = 0;
		String str = string;

		// 去掉第一个字符是分隔符的情况
		if ((k = str.indexOf(c)) == 0) {
			str = string.substring(k + 1);
		}
		// 检测是否包含分割符，如果不含则返回字符串
		if (str.indexOf(c) == -1) {
			return new String[] { str };
		}
		while ((k = str.indexOf(c)) != -1) {
			strArray[j++] = str.substring(0, k);
			str = str.substring(k + 1);
			if ((k = str.indexOf(c)) == -1 && str.length() > 0) {
				strArray[j++] = str.substring(0);
			}
		}
		return strArray;
	}

	public static Double formatDouble(Double value, String fmt) {
		if (StringUtil.isNull(fmt)) {
			fmt = "0.00";
		}
		DecimalFormat df1 = new DecimalFormat(fmt);
		return Double.parseDouble(df1.format(value));
	}

	public static String formatDoubleZero(Double value, String fmt) {
		if (StringUtil.isNull(fmt)) {
			fmt = ".##";
		}
		DecimalFormat df1 = new DecimalFormat(fmt);

		df1.applyPattern(fmt);
		return df1.format(value);
	}

	public static Double formatDoubleFour(Double value) {
		return StringUtil.formatDouble(value, "0.0000");
	}

	public static String fmtString(String str, int first, int end, String reStr) {
		if (StringUtil.isNull(reStr)) {
			reStr = "****";
		}
		if (StringUtil.isNull(str)) {
			return reStr;
		}
		if (str.length() < first) {
			first = str.length();
		}
		if (str.length() < end) {
			end = str.length();
		}
		String firstStr = str.substring(0, first);
		String endStr = str.substring(str.length() - end, str.length());
		return firstStr + reStr + endStr;
	}

	public static String fmtNameString(String str) {
		String rstr = "";
		int count = 1;
		if (str.length() > 3) {
			count = 2;
		}
		for (int i = 0; i < str.length() - count; i++) {
			rstr = rstr + "*";
		}
		String end = str.substring(str.length() - count, str.length());
		return rstr + end;
	}

	public static String getDomain(String url) {
		String regEx = "[\\w-]+.(com|net|org|gov|cc|biz|info|cn|co|me)(.(cn|hk|uk))*";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(url);
		boolean rs = mat.find();
		if (rs == true) {
			return mat.group();
		}
		return "";
	}

	public static String getDomain2(String url) {
		String regEx2 = ".+[./]([A-z]+.[A-z]+)/[^/].+/";
		//
		// CheckUrl = CheckUrl.replace("\\","\/");
		// CheckUrl = CheckUrl.replace(//,"$1");"
		String regEx = "[\\w-]+.(com|net|org|gov|cc|biz|info|cn|co|me)(.(cn|hk|uk))*";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(url);
		String rStr = "";
		int count = 0;
		while (mat.find()) {
			rStr = mat.group();
			count++;
		}
		if (count > 1) {
			return "";
		}
		return rStr;

	}

	public static String cleanXSS(String value) {
		if (StringUtil.isNull(value)) {
			return value;
		}
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	public static String getRandomString(int lth, String baseString) {
		String sRand = "";
		Random random = new Random();
		if (StringUtil.isNull(baseString)) {
			baseString = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
		}
		for (int i = 0; i < lth; i++) {
			int rand = random.nextInt(baseString.length());
			// 随即生成验证码
			String strRand = baseString.substring(rand, rand + 1);
			sRand += strRand;
		}
		return sRand;
	}
	public static String replace(String content){
		  StringBuffer sb = new StringBuffer(content);
		  int start = 0;
		  int end = 0;
		  for(int i=0;i<sb.length();i++){
		   char cha = sb.charAt(i);
		   if(cha=='<'){
		    start = i;
		   }else if(cha=='>'){
		    end = i;
		    sb = sb.replace(start, end+1, "");
		    i=0;
		    start = 0;
		   }else if(i==(sb.length()-1)){
			   if(start!=0){
				   sb = sb.replace(start, sb.length(), "");
			   }
		   }
		  }
		  return sb.toString();
	}
	public static void main(String[] args) {
		boolean flag = true;
		String orderContent = "01,01,02|03,02,01";
		String[] orders = orderContent.split("\\|");
		for (int i = 0; i < orders.length; i++) {
			String[] nums = orders[i].split(",|-");
		for (String num : nums) {
			System.out.println(num);
			Pattern p = Pattern.compile(num);
			Matcher m = p.matcher(orders[i]);
			int count = 0;
			while (m.find()) {
				count++;
			}
			if (count > 1) {
				flag = false;
				break;
			}
		}		}
		System.out.println(flag);
	}

}
