package com.aiyounet.basicFunc.service;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.UserInfo;

/**
 * @describe : 菜单管理
 * @author : aiyounet.zhengzhenwen
 * @date : 2013-1-07
 * @jdk : 1.6
 * @version : 1.0
 */
public interface MenuService {
	/**
	 * 初始化菜单
	 * @param User 
	 * @return Map
	 * @throws Exception 
	 */
	public Object initMenu(UserInfo user) throws Exception;
	/**
	 * 查询所以用户组
	 * @return Map
	 * @throws Exception 
	 */
	public Object initMenuOfGroup();
	/**
	 * 查询用户原有组
	 * @return Map
	 * @throws Exception 
	 */
	public Object getGroupOfUser(String id);
	/**
	 * 查询用户组
	 * @return Map
	 * @throws Exception 
	 */
	public Object getGroups();
	/**
	 * 查询特点组的菜单
	 * @return Map
	 * @throws Exception 
	 */
	public Object getMenuOfGroup(String id);
	/**
	 * 设置用户组拥有菜单
	 * @param id
	 * @param groupIds
	 * @return
	 * @throws Exception
	 */
	public Object setMenus(String id, String groupIds) throws Exception;
	/**
	 * 搜索菜单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Object getMenuContent(PageBean page)throws Exception;
	/**
	 * 设置权限
	 * @param id
	 * @param hasUrl
	 * @return
	 * @throws Exception
	 */
	public Object getMenuContent(Integer id, String hasUrl)throws Exception;
}
