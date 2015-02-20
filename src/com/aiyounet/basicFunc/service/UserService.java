package com.aiyounet.basicFunc.service;

import java.util.Map;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.UserInfo;

/**
 * @describe : 用户管理
 * @author : aiyounet.zhengzhenwen
 * @date : 2013-1-07
 * @jdk : 1.6
 * @version : 1.0
 */
public interface UserService {
	/**
	 * 查询管理用户列表
	 * @param User 
	 * @return Map
	 * @throws Exception 
	 */
	public Object getUsers();

	/**
	 * 设置用户组
	 * @param User 
	 * @return Map
	 * @throws Exception 
	 */
	public Object setGroups(String id, String groupIds)throws Exception;

	public Object addGroup(String groupName)throws Exception;

	public Map<String, Object> createAuthcode();

	public Object login(String userName, String passWord, String code, String ip)throws Exception;

	public Object testUserName(String userName, String nickName,Integer platId);

	public Object regist(String code, UserInfo user, String ip)throws Exception;

	public Object vipmanager(PageBean page, String name, String value, String ispro,String sucCon,String successValue,String bankInfo,UserInfo user)throws Exception;
 	public Object addVip(UserInfo user)throws Exception;

	public Object updatePass(Integer id, String newPass)throws Exception;

	public Object isStop(Integer id, Integer isStop)throws Exception;

	public Object addManager(String userName, String passWord)throws Exception;

	public Object deletedManager(Integer id)throws Exception ;

 
  
}
