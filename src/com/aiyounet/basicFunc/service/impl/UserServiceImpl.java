package com.aiyounet.basicFunc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.AdminLoginLog;
import com.aiyounet.basicFunc.dao.bean.GroupUserInfo;
import com.aiyounet.basicFunc.dao.bean.SysConfig;
import com.aiyounet.basicFunc.dao.bean.UserGroup;
import com.aiyounet.basicFunc.dao.bean.UserInfo;
import com.aiyounet.basicFunc.dao.hibernate.AdminLoginLogDao;
import com.aiyounet.basicFunc.dao.hibernate.GroupUserInfoDao;
import com.aiyounet.basicFunc.dao.hibernate.SysConfigDao;
import com.aiyounet.basicFunc.dao.hibernate.UserGroupDao;
import com.aiyounet.basicFunc.dao.hibernate.UserInfoDao;
import com.aiyounet.basicFunc.service.UserService;
import com.aiyounet.basicFunc.util.AuthCodeUtil;
import com.aiyounet.basicFunc.util.ConsUtil;
import com.aiyounet.basicFunc.util.DateUtil;
import com.aiyounet.basicFunc.util.HttpClient;
import com.aiyounet.basicFunc.util.StringUtil;
import com.aiyounet.frame.util.JsonUtil;
import com.aiyounet.frame.util.SecurityUtil;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	HttpSession hSession;
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private GroupUserInfoDao groupUserInfoDao;
	@Resource
	private UserGroupDao userGroupDao;
	@Resource
	private AdminLoginLogDao adminLoginLogDao;
	@Resource
	private SysConfigDao sysConfigDao;
 
	public Object getUsers() {
		List<UserInfo> users = userInfoDao.getUsersOfManager();
		Integer total = userInfoDao.getUsersOfManagerCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", users);
		return map;
	}

	public Object setGroups(String id, String groupIds) throws Exception {
		String[] groups = groupIds.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		groupUserInfoDao.deleteAll(id);
		for (String groupId : groups) {
			GroupUserInfo groupUser = groupUserInfoDao.getGroupUser(id, groupId);
			if (groupUser == null) {
				GroupUserInfo newObject = new GroupUserInfo();
				newObject.setUserId(Integer.parseInt(id));
				newObject.setGroupId(Integer.parseInt(groupId));
				groupUserInfoDao.save(newObject);
			}
		}
		map.put(ConsUtil.SUCCESS, "0");
		return map;
	}

	public Object addGroup(String groupName) throws Exception {
		UserGroup group = new UserGroup();
		group.setGroupName(groupName);
		userGroupDao.save(group);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ConsUtil.SUCCESS, "0");
		return map;
	}

	public Map<String, Object> createAuthcode() {
		// 生成验证码对象
		AuthCodeUtil rc = new AuthCodeUtil();
		Map<String, Object> map = rc.createRandImage();
		// 验证码保存到session
		String authcode = (String) map.get(ConsUtil.AUTHCODE);
		hSession.setAttribute(ConsUtil.AUTHCODE, authcode);
		return map;
	}

	public Object login(String userName, String passWord, String code, String ip) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String authcode = (String) hSession.getAttribute(ConsUtil.AUTHCODE);
		if (!code.toLowerCase().equals(authcode.toLowerCase())) {
			// if(false){
			hSession.removeAttribute(ConsUtil.AUTHCODE);
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "验证码错误");
			return map;
		}

		UserInfo user = userInfoDao.getUserInfo(userName);// platId=null的时候，查询管理员
		if (user == null) {
			map.put(ConsUtil.SUCCESS, 2);
			map.put(ConsUtil.MSG, "账号错误");
			return map;
		}
		if (!SecurityUtil.encode32(passWord).equals(user.getPassword())) {
			map.put(ConsUtil.SUCCESS, 3);
			map.put(ConsUtil.MSG, "密码错误");
			return map;
		}
		if (user.getUserType() != 2) {
			map.put(ConsUtil.SUCCESS, 4);
			map.put(ConsUtil.MSG, "对不起,您的权限不够。");
			return map;
		}
		hSession.setAttribute(ConsUtil.USER, user);
		AdminLoginLog adminLog = new AdminLoginLog();
		adminLog.setLoginIp(ip);
		String time = DateUtil.getCurDatetime(null);
		adminLog.setLoginTime(time);
		adminLog.setUserName(userName);
		adminLoginLogDao.save(adminLog);
		UserInfo updateUser = new UserInfo();
		updateUser.setLastLoginTime(time);
		updateUser.setLastLoginIp(ip);
		// 2013-08-23 userInfoDao.setUserLoginIP(ip, time, user.getId());
		userInfoDao.updateUserInfo(updateUser, user.getId(), null);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

	public Object testUserName(String userName, String nickName, Integer platId) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo user = userInfoDao.getUserInfo(userName);
		if (user == null) {
			UserInfo nickUser = userInfoDao.getUserOfNick(nickName, platId);
			if (nickUser == null) {
				map.put(ConsUtil.SUCCESS, 0);
			} else {
				map.put(ConsUtil.SUCCESS, 2);
			}
		} else {

			map.put(ConsUtil.SUCCESS, 1);
		}
		return map;
	}


	@SuppressWarnings("unchecked")
	private String getCityForIp(String ip) {
		// http://www.youdao.com/smartresult-xml/search.s?type=ip&q=115.156.238.114
		String city = null;
		HttpClient client = new HttpClient("http://int.dpool.sina.com.cn/iplookup/iplookup.php",
				false, false);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("format", "js");
		map.put("ip", ip);
		String sb;
		try {
			sb = client.getString("", map);
			Map<String, String> json = JsonUtil.toObject(sb.split("=")[1], Map.class);
			city = json.get("province").toString() + "-" + json.get("city").toString();
			return city;
		} catch (Exception e) {
			log.info("IP地址：" + ip + "--------------------------无法解析");
			return "未知";
		}
	}

 
	public Object addVip(UserInfo user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SysConfig sys = sysConfigDao.findAll().get(0);
		if (sys.getCanReg() != ConsUtil.CANREG) {
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "当前系统不允许注册");
			return map;
		}
		String[] names = sys.getUserNameFilter().split(",");
		for (String name : names) {
			if (user.getUserName().indexOf(name) > -1) {
				map.put(ConsUtil.SUCCESS, 1);
				map.put(ConsUtil.MSG, "用户名不能包含" + sys.getUserNameFilter());
				return map;
			}
		}
		String[] nicks = sys.getPassFilter().split(",");
		for (String name : nicks) {
			if (user.getNickName().indexOf(name) > -1) {
				map.put(ConsUtil.SUCCESS, 1);
				map.put(ConsUtil.MSG, "昵称不能包含" + sys.getPassFilter());
				return map;
			}
		}
		UserInfo us = userInfoDao.getUserInfo(user.getUserName());
		if (us != null) {
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "该账户已经存在");
			return map;
		}
		UserInfo nick = userInfoDao.getUserOfNick(user.getNickName(), user.getPlatId());
		if (nick != null) {
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "该昵称已经存在!");
			return map;
		}
		user.setPassword(SecurityUtil.encode32(user.getPassword()));
		if (!StringUtil.isNull(user.getDrawPass())) {
			user.setDrawPass(SecurityUtil.encode32(user.getDrawPass()));
		}
		user.setMoney(0.00);
		user.setRegistTime(DateUtil.getCurDatetime(null));
		user.setToken("");
		user.setModel(1);
		user.setIsStop(0);
		user.setPoint(0.00);
		user.setOnlineStatus(0);
		Integer userId = (Integer) userInfoDao.save(user);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}
 
 
	public Object updatePass(Integer id, String newPass) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		userInfoDao.setLoginPw(SecurityUtil.encode32(newPass), id);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

	public Object isStop(Integer id, Integer isStop) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		userInfoDao.setStop(id, isStop);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

 

	public Object addManager( String userName, String passWord) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo user = new UserInfo();
		UserInfo users = userInfoDao.getUserInfo(userName);// 检查平台用户是否已经存在
		if (users != null) {
			map.put(ConsUtil.SUCCESS, 1);
			map.put(ConsUtil.MSG, "该用户已经存在");
			return map;
		}
		user.setPId(0);
		user.setUserName(userName);
		user.setPassword(SecurityUtil.encode32(passWord));
		user.setUserType(2);
		user.setMoney(0.00);
		user.setRegistTime(DateUtil.getCurDatetime(null));
		user.setToken("");
		user.setModel(1);
		user.setIsStop(0);
		user.setPoint(0.00);
		user.setOnlineStatus(0);
		user.setIsProtect(0);
		user.setRewardPoint(0.0);
		userInfoDao.save(user);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

	public Object deletedManager(Integer id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		userInfoDao.delete(id);
		map.put(ConsUtil.SUCCESS, 0);
		return map;
	}

	public Object regist(String code, UserInfo user, String ip) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object vipmanager(PageBean page, String name, String value, String ispro, String sucCon,
			String successValue, String bankInfo, UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


 
 
 
 
}
