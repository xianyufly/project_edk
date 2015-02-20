package com.aiyounet.basicFunc.service.interceptor;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
public class InteceptorController implements HandlerInterceptor {

	public static final Log log = LogFactory.getLog(InteceptorController.class);
	

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		String url = request.getServletPath();
		if (url.startsWith("/api/")) {
			log.info("end:"+url);
		}
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 拦截session是否存在，拦截请求URL是否有访问权限
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String url = request.getServletPath();
		System.out.println("com.aiyounet.client所请求的url=====================:"+url);
		if (url.startsWith("/sys/")) {
			return true;
		}
		if (url.startsWith("/api/")) {
			log.info("begin:"+url);
			return true;
		}
		Map<String, String> map=new HashMap<String, String>();
  
			return true;
 
	}
	
	

}
