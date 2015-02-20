package com.aiyounet.basicFunc.dao.hibernate;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.util.StringUtil;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class UserLoginLogDao extends BaseDaoImpl<UserLoginLog> {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findAll(PageBean page, String userName, String groupType,
			String ipAddr, String mac,String platIdStr) {
		Session session = super.getSession();
		String sql = "SELECT u.id , u.user_name AS userName,u.ip_addr AS ipAddr,u.even_time AS evenTime,u.mac AS mac,l.id as macId,l.limit_value AS limitMac,l.limit_area AS limitAreaMac,l.user_name AS limitUserNameMac,l2.id as ipId," +
				"l2.limit_value AS limitIp, l2.limit_area AS limitAreaIp,l2.user_name AS limitUserNameIp, u.login_tag as loginTag"
				+ " FROM user_login_log u LEFT JOIN user_limit l ON u.mac = l.limit_value LEFT JOIN user_limit l2 ON l2.limit_value = u.ip_search where 1 = 1 and u.plat_id in ("+platIdStr+")";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!StringUtil.isNull(userName)) {
			sql = sql + " and u.user_name = :userName ";
			paramMap.put("userName", userName);
		}
		if (!StringUtil.isNull(ipAddr)) {
			sql = sql + " and u.ip_addr = :ipAddr ";
			paramMap.put("ipAddr", ipAddr);
		}
		if (!StringUtil.isNull(mac)) {
			sql = sql + " and u.mac = :mac ";
			paramMap.put("mac", mac);
		}
		if (!StringUtil.isNull(groupType)) {
			sql = sql + " group by u.mac, u.ip_addr ";
		}
		sql = sql + " ORDER BY u.id DESC";
		Query query = session.createSQLQuery(sql);
		query.setProperties(paramMap);
		if (page != null) {
			query.setFirstResult(page.getStart()).setMaxResults(page.getRows());
		}
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

	}

	@SuppressWarnings("unchecked")
	public Object getLastLoginTime(String userName) {
		Session session = super.getSession();
		List<Object> list = session
				.createSQLQuery(
						"select even_time from user_login_log where user_name = ? order by even_time desc")
				.setParameter(0, userName).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return " ";
		}

	}

	public List<Map<String, Object>> getUserInfoByLimitValue(String limitValue, String beginTime) {
		Session session = super.getSession();
		List<Map<String, Object>> list = session
				.createSQLQuery(
						"select u.id as userId,l.user_name as userName from user_login_log l left join user_info u on u.user_name = l.user_name where (l.mac = ? or l.ip_addr = ? ) and l.even_time >= ?  group by l.user_name ")
				.setParameter(0, limitValue).setParameter(1, limitValue).setParameter(2, beginTime)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	public Integer findAllCount(Object object, String userName, String groupType, String ipAddr,
			String mac,String platIdStr) {
		Session session = super.getSession();
		String sql = "select count(*) "
				+ " FROM user_login_log u LEFT JOIN user_limit l ON u.mac = l.limit_value LEFT JOIN user_limit l2 ON  l2.limit_value = u.ip_search where 1 = 1 and u.plat_id in ("+platIdStr+")";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (!StringUtil.isNull(userName)) {
			sql = sql + " and u.user_name = :userName ";
			paramMap.put("userName", userName);
		}
		if (!StringUtil.isNull(ipAddr)) {
			sql = sql + " and u.ip_addr = :ipAddr ";
			paramMap.put("ipAddr", ipAddr);
		}
		if (!StringUtil.isNull(mac)) {
			sql = sql + " and u.mac = :mac ";
			paramMap.put("mac", mac);
		}
		if (!StringUtil.isNull(groupType)) {
			sql = sql + " group by u.mac, u.ip_addr ";
		}
		Query query = session.createSQLQuery(sql);
		query.setProperties(paramMap);

		return  ((BigInteger) query.list().get(0)).intValue();
	}

}
