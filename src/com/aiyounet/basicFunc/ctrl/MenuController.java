package com.aiyounet.basicFunc.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.service.MenuService;
import com.aiyounet.basicFunc.service.UserService;
import com.aiyounet.basicFunc.util.ConsUtil;

@Controller
public class MenuController {
	private static final Logger log = LoggerFactory
			.getLogger(MenuController.class);
	@Resource
	private MenuService menuService;
	@Resource
	private UserService userService;
	
	
	@RequestMapping
	@ResponseBody
	public Object initMenu(HttpServletRequest request) throws Exception{
		log.info("初始化菜单");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConsUtil.USER);
		return menuService.initMenu(user);
	}

	@RequestMapping
	@ResponseBody
	public Object getGroupOfUser(String id) throws Exception{
		log.info("查询用户原有组");
		return menuService.getGroupOfUser(id);
	}
	@RequestMapping
	@ResponseBody
	public Object getUsers(HttpServletRequest req){
		log.info("查询管理用户");
		return userService.getUsers();
	}
	@RequestMapping
	@ResponseBody
	public Object initMenuOfGroup(){
		log.info("查询所有用户组");
		return menuService.initMenuOfGroup();
	}
	@RequestMapping
	@ResponseBody
	public Object setGroups(String id,String groupIds) throws Exception{
		log.info("设置用户组");
		return userService.setGroups(id,groupIds);
	}
	@RequestMapping
	@ResponseBody
	public Object setMenus(String id,String MenusId) throws Exception{
		log.info("设置组菜单");
		return menuService.setMenus(id,MenusId);
	}
	@RequestMapping
	@ResponseBody
	public Object getGroups(){
		log.info("查询所有用户组");
		return menuService.getGroups();
	}
	@RequestMapping
	@ResponseBody
	public Object getMenuOfGroup(String id){
		log.info("查询特点组所有菜单");
		return menuService.getMenuOfGroup(id);
	}
	@RequestMapping
	@ResponseBody
	public Object addGroup(String groupName) throws Exception{
		log.info("添加用户组");
		return userService.addGroup(groupName);
	}
	@RequestMapping
	@ResponseBody
	public Object addManager(String userName,String passWord,HttpServletRequest req) throws Exception{
		log.info("添加管理员");
		return userService.addManager(userName,passWord);
	}
	@RequestMapping
	@ResponseBody
	public Object deletedManager(Integer id) throws Exception{
		log.info("删除管理员");
		return userService.deletedManager(id);
	}
	@RequestMapping
	@ResponseBody
	public Object getMenuContent(PageBean page) throws Exception{
		log.info("搜索菜单内容");
		return menuService.getMenuContent(page);
	}
	@RequestMapping
	@ResponseBody
	public Object setMenuUrl(Integer id,String hasUrl) throws Exception{
		return menuService.getMenuContent(id,hasUrl);
		
	}
}
