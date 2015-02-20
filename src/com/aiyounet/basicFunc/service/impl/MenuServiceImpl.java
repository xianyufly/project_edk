package com.aiyounet.basicFunc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.GroupMenuInfo;
import com.aiyounet.basicFunc.dao.bean.Menu;
import com.aiyounet.basicFunc.dao.bean.UserGroup;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.dao.bean.dto.UserGroupDto;
import com.aiyounet.basicFunc.dao.hibernate.GroupMenuInfoDao;
import com.aiyounet.basicFunc.dao.hibernate.GroupUserInfoDao;
import com.aiyounet.basicFunc.dao.hibernate.MenuDao;
import com.aiyounet.basicFunc.dao.hibernate.UserGroupDao;
import com.aiyounet.basicFunc.service.MenuService;
import com.aiyounet.basicFunc.util.ConsUtil;

@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDao menuDao;
	@Resource
	private GroupUserInfoDao groupUserInfoDao;
	@Resource
	private GroupMenuInfoDao groupMenuInfoDao;
	@Resource
	private UserGroupDao userGroupDao;

	@SuppressWarnings("unchecked")
	public Object initMenu(UserInfo user) throws Exception {
		List<Menu> listMenu = null;
		listMenu = menuDao.getMenus();
		List<Map<String, Object>> listMenuDto = new ArrayList<Map<String, Object>>();
		List<Menu> listMenu2 = new ArrayList<Menu>();
		for (Menu menu : listMenu) {
			if (menu.getPId() == 0) {
				Map<String, Object> menuDto = new HashMap<String, Object>();
				menuDto.put("id", menu.getId());
				menuDto.put("text", menu.getText());
				menuDto.put("state", "open");
				menuDto.put("children", new ArrayList<Map<String, Object>>());
				listMenuDto.add(menuDto);
			} else {
				listMenu2.add(menu);
			}
		}
		for (int i = 0; i < listMenuDto.size(); i++) {
			for (Menu menu : listMenu2) {
				if (menu.getPId().equals(listMenuDto.get(i).get("id"))) {
					Map<String, Object> menuDto = new HashMap<String, Object>();
					menuDto.put("id", menu.getId());
					menuDto.put("text", menu.getText());
					menuDto.put("attributes", new HashMap<String, Object>());
					((Map<String, Object>) menuDto.get("attributes")).put(
							"url", menu.getUrl());
					((List<Map<String, Object>>) listMenuDto.get(i).get(
							"children")).add(menuDto);
				}
			}
		}

		return listMenuDto;
	}

	public Object initMenuOfGroup() {
		List<Map<String, Object>> groupDto = new ArrayList<Map<String, Object>>();
		List<UserGroup> groups = userGroupDao.getGroups();
		for (UserGroup group : groups) {
			Map<String, Object> menuDto = new HashMap<String, Object>();
			menuDto.put("id", group.getId());
			menuDto.put("text", group.getGroupName());
			groupDto.add(menuDto);
		}
		return groupDto;
	}

	public Object getGroupOfUser(String id) {
		List<Integer> list = groupUserInfoDao.getGroupIds(id);
		return list;
	}

	public Object getGroups() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserGroup> userGroups = userGroupDao.getGroups();
		List<UserGroupDto> newuserGroups = new ArrayList<UserGroupDto>();
		for (UserGroup group : userGroups) {
			UserGroupDto newGroup = new UserGroupDto();
			newGroup.setId(group.getId());
			newGroup.setGroupName(group.getGroupName());
			newuserGroups.add(newGroup);
		}
		map.put("total", userGroups.size());
		map.put("rows", newuserGroups);
		return map;
	}

	public Object getMenuOfGroup(String id) {
		List<Integer> list = groupMenuInfoDao.getMenuIds(id);
		return list;
	}

	public Object setMenus(String id, String MenuIds) throws Exception {
		String[] Menus = MenuIds.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		groupMenuInfoDao.deleteAll(id);
		for (String MenuId : Menus) {
			GroupMenuInfo groupMenu = groupMenuInfoDao.getGroupUser(id, MenuId);
			if (groupMenu == null) {
				GroupMenuInfo newObject = new GroupMenuInfo();
				newObject.setMenu(menuDao.get(Integer.parseInt(MenuId)));
				newObject.setUserGroup(userGroupDao.get(Integer.parseInt(id)));
				groupMenuInfoDao.save(newObject);
			}

		}
		map.put(ConsUtil.SUCCESS, "0");
		return map;
	}

	public Object getMenuContent(PageBean page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> menus = menuDao.getMenus(page);
		map.put("rows", menus);
		map.put("total", menuDao.getMenusTotal());
		return map;
	}
	public Object getMenuContent(Integer id, String hasUrl) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Menu menu = menuDao.get(id);
		menu.setHasUrl(hasUrl);
		menuDao.update(menu);
		map.put(ConsUtil.SUCCESS, "0");
		return map;
	}


}
