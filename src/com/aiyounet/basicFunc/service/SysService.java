package com.aiyounet.basicFunc.service;

import java.util.Map;

import com.aiyounet.basicFunc.dao.bean.UserInfo;

/**
 * @describe : SysService
 * @author : aiyounet.wengzq
 * @date : 2013-1-7
 * @jdk : 1.6
 * @version : 1.0
 */
public interface SysService {

	/**
	 * 系统首页初始化
	 * @param user 
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initIndex(UserInfo user) throws Exception;

	public Map<String, Object> updatePass(UserInfo user, String oldPass,
			String newPass)throws Exception;
}
