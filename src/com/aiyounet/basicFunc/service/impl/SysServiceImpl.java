package com.aiyounet.basicFunc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aiyounet.basicFunc.dao.bean.Menu;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.dao.hibernate.GroupMenuInfoDao;
import com.aiyounet.basicFunc.dao.hibernate.GroupUserInfoDao;
import com.aiyounet.basicFunc.dao.hibernate.MenuDao;
import com.aiyounet.basicFunc.dao.hibernate.UserInfoDao;
import com.aiyounet.basicFunc.service.SysService;
import com.aiyounet.basicFunc.util.ConsUtil;
import com.aiyounet.frame.util.SecurityUtil;

@Service
public class SysServiceImpl implements SysService {

 
	@Resource
	private GroupUserInfoDao groupUserInfoDao;
	@Resource
	private GroupMenuInfoDao groupMenuInfoDao;
	@Resource
	private MenuDao menuDao;
	@Resource
	private UserInfoDao userInfoDao;
	@SuppressWarnings("unchecked")
	public Map<String, Object> initIndex(UserInfo user) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 加载信息列表
		if(user==null||user.getUserType()!=2){
			map.put(ConsUtil.SUCCESS, 1);
			return map;
		}
		List<Integer> list = null;
		boolean isOpen = false;
		List<Menu> listMenu = null;
		list = groupUserInfoDao.getGroupIds(user.getId());
		List<Integer> listMenuId = groupMenuInfoDao.getMenuIds(list);
		listMenu = menuDao.getMenusForGroup(listMenuId);

		List<Map<String, Object>> listMenuDto = new ArrayList<Map<String, Object>>();
		List<Menu> listMenu2 = new ArrayList<Menu>();
		List<Map<String,Object>> listButton = new ArrayList<Map<String,Object>>();
		for (Menu menu : listMenu) {
			if (menu.getPId() == 0) {
				Map<String, Object> menuDto = new HashMap<String, Object>();
				menuDto.put("id", menu.getId());
				menuDto.put("text", menu.getText());
				if (isOpen)
					menuDto.put("state", "open");
				else
					menuDto.put("state", "close");
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
					Map<String,Object> menuButton = new HashMap<String,Object>();
					if(menu.getIsShort().intValue() == 1){
						menuButton.put("id", menu.getId());
						menuButton .put("text", menu.getText());
						menuButton.put("url", menu.getUrl());
						listButton.add(menuButton);
					}
				}

//				if("今日统计".equals(menu.getText()) || "今日统计查询".equals(menu.getText()) || "投注记录".equals(menu.getText()) || "资金变动流水".equals(menu.getText()) || "会员管理".equals(menu.getText()) || "玩家登录日志".equals(menu.getText()) || "充值审核".equals(menu.getText()) 
//					|| "充值审核查询".equals(menu.getText()) || "手工充值".equals(menu.getText()) || "取款审核".equals(menu.getText()) || "取款审核查询".equals(menu.getText())){
				
				
			}
		}
		
		map.put(ConsUtil.SUCCESS, 0);
		map.put("tree", listMenuDto);
		map.put("shortKey", listButton);
		map.put(ConsUtil.USER, user);
		map.put("groupIds", list);
		return map;
	}

	public Map<String, Object> updatePass(UserInfo user, String oldPass,
			String newPass) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		user = userInfoDao.get(user.getId());
		if(user.getUserType() != 2){//只允许管理员通过此接口改密码
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "无操作权限");
			return map;
		}
		oldPass = SecurityUtil.encode32(oldPass);
		if(!oldPass.equals(user.getPassword())){
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "密码错误");
			return map;
		}
		newPass =  SecurityUtil.encode32(newPass);
		UserInfo updateUser = new UserInfo();
		updateUser.setPassword(newPass);
		userInfoDao.updateUserInfo(updateUser, user.getId(), null);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

}
