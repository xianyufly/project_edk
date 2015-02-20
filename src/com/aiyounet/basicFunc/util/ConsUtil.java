package com.aiyounet.basicFunc.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @describe : 常量类
 * @author : aiyounet.wengzq
 * @date : 2013-1-6
 * @jdk : 1.6
 * @version : 1.0
 */
public class ConsUtil {

	public static final String SUCCESS = "success";

	public static final String RESULT = "result";

	public static final String MSG = "msg";

	public static final String ROWS = "rows";

	public static final String USER = "user";

	public static final String RETSING = "retSign";

	public static final String USER_TOKEN = "userToken";

	public static final String IS_WINNING = "isWinning";
 
 
	/**
	 * 否 中奖停止追单
	 */
	public static final int NO_HIT_OVER = 0;
	/**
	 * 撤单
	 */
	public static final int STATE_OVER = 2;
	/**
	 * 取款审核通过
	 */
	public static final int GET_MONEY_YES= 2;
	/**
	 * 取款未审核通过
	 */
	public static final int GET_MONEY_NO= 1;
	/**
	 * 取款未审核
	 */
	public static final int GET_MONEY= 0;
	/**
	 * 未兑奖
	 */
	public static final int STATE_NO = 0;
	/**
	 * 兑奖
	 */
	public static final int STATE_YES = 1;

	/**
	 * 一注基本金额 元模式下 1：元模式，2：分模式
	 */
	public static final Double BASE_MONEY = 2.0;
	/**
	 * @Fields TOTAL : 返回记录数
	 */
	public static final String TOTAL = "total";

	/**
	 * 会员
	 */
	public static final int HUIYUAN = 0;

	/**
	 * 代理
	 */
	public static final int DAILI = 1;

	/**
	 * 管理员
	 */
	public static final int GUANLIYUAN = 2;
	/**
	 * 允许注册
	 */
	public static final int CANREG = 0;
	/**
	 * 不允许注册
	 * 
	 */
	public static final int NOTREG = 1;
	/**
	 * session 中保存验证码图片的名称
	 */
	public static final String AUTHCODE_IMAGE = "authcodeImage";

	/**
	 * session 中保存验证码的名称
	 */
	public static final String AUTHCODE = "authcode";
	/**
	 * 上线
	 */
	public static final int LOGIN = 0;

	/**
	 * 1 ： IS_BET_ON 开启投注
	 */
	public static final int IS_BET_ON = 1;

	/**
	 * 0 ： IS_BET_OFF 关闭投注
	 */
	public static final int IS_BET_OFF = 0;
	/**
	 * 1： IS_OPEN_ON 开启开奖
	 */
	public static final int IS_OPEN_ON = 1;

	/**
	 * 0： IS_OPEN_OFF 关闭开奖
	 */
	public static final int IS_OPEN_OFF = 0;
	public static final String USER_PLATID = "user_platId";
	
	public static Map<String, Object> getReturnMap(Integer result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ConsUtil.RESULT, result);
		return map;
	}
	/**
	 * 获取返回的Map
	 * 
	 * @Title: geReturnMap
	 * @param result
	 *            返回结果，0失败，1成功
	 * @return Map<String, Object>
	 * @throws
	 * @author 2013-4-15
	 */
	public static Map<String, Object> getReturnMap(Integer result,Integer errType,String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ConsUtil.RESULT, result);
		if(!StringUtil.isNull(errType)){
			map.put("errType", errType);
		}
		if(!StringUtil.isNull(msg)){
			map.put("msg", msg);
		}
		return map;
	}
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
 

}
