package com.aiyounet.basicFunc.ctrl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiyounet.basicFunc.constant.StaticValue;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.dao.hibernate.UserInfoDao;
import com.aiyounet.basicFunc.service.SysService;
import com.aiyounet.basicFunc.service.UserService;
import com.aiyounet.basicFunc.util.IPUtil;

@Controller
public class SysController {
	public static final Logger logger = LoggerFactory
			.getLogger(SysController.class);

	@Resource
	private UserService userService;
	@Resource
	private SysService service;
	@Resource
	UserInfoDao userInfoDao;
 
	/**
	 * 加载初始化信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> initIndex(HttpServletRequest request)
			throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute(
				StaticValue.USER);
		return service.initIndex(user);
	}

	/**
	 * 管理员修改自身密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> updatePass(HttpServletRequest request,
			String oldPass, String newPass) throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute(
				StaticValue.USER);
		logger.info("管理员修改自己密码:" +user.getId() );
		return service.updatePass(user, oldPass, newPass);
	}

	/**
	 * 验证码生成
	 * 
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping
	public void createAuthcode(HttpServletResponse response) throws IOException {
		logger.info("验证码生成,,,,！");
		Map<String, Object> map = userService.createAuthcode();
		response.setHeader("P3P", "CP=CAO PSA OUR");
		BufferedImage image = (BufferedImage) map.get(StaticValue.AUTHCODE_IMAGE);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 登入验证
	 * 
	 * @param userName
	 *            ,passWord,code
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public Object login(String userName, String passWord, String code,
			HttpServletRequest request) throws Exception {
		logger.info("登录验证,,,,！");
		String ip = IPUtil.getRequestIp(request);
		return userService.login(userName, passWord, code, ip);
	}

	/**
	 * 退出登录
	 */
	@RequestMapping
	@ResponseBody
	public Object outLogin(HttpServletRequest request) {
		logger.info("退出登录");
		request.getSession().setAttribute(StaticValue.USER, null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(StaticValue.SUCCESS, 0);
		return map;
	}

	/**
	 * 检查帐号是否可用
	 * 
	 * @param userName
	 * 
	 */
	@RequestMapping
	@ResponseBody
	public Object testUserName(String userName, String nickName,Integer platId) {
		return null;
	}


	public static String formatListToString(List<String> agentIdList) {
		String temp = "";
		for (Integer i = 0; i < agentIdList.size(); i++) {
			if (i != agentIdList.size() - 1) {
				temp = temp + agentIdList.get(i) + ",";
			} else {
				temp = temp + agentIdList.get(i);
			}
		}
		logger.info(temp);
		return temp;
	}
 
 
 
	
}
