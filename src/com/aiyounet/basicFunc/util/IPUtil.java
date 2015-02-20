package com.aiyounet.basicFunc.util;
import javax.servlet.http.HttpServletRequest;

public class IPUtil {

//	private static final Logger log = LoggerFactory.getLogger(IPUtil.class);
	public static String getRequestIp(HttpServletRequest request) {
//        ip = request.getHeader("X-Forwarded-For");
		 String  ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        log.info("HTTP_X_FORWARDED_FOR:"+ip);
        if(ip == null ||ip.equals("")||"unknown".equalsIgnoreCase(ip)){
         ip = request.getHeader("X-Forwarded-For");
//        log.info("X-Forwarded-For:"+ip);
        }
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } 
        ip = request.getHeader("X-Real-IP");
//        log.info("X-Real-IP:"+ip);
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
        	return ip;
        }
        return request.getRemoteAddr();
	}

}