package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.GroupUserInfo;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class GroupUserInfoDao extends BaseDaoImpl<GroupUserInfo> {

	@SuppressWarnings("unchecked")
	public List<Integer> getGroupIds(Integer id) {
		Session session = super.getSession();
		return session
				.createSQLQuery(
						"select group_id from group_user_info where user_id = ?")
				.setParameter(0, id).list();
	}


	@SuppressWarnings("unchecked")
	public List<Integer> getAllUserIds() {
		Session session = super.getSession();
		return session.createSQLQuery(
				"select user_id from  group_user_info group by user_id").list();
	}

	@SuppressWarnings("unchecked")
	public GroupUserInfo getGroupUser(String id, String groupId) {
		Session session = super.getSession();
		List<GroupUserInfo> list = session
				.createQuery(
						"from GroupUserInfo where userId=?  and groupId = ?")
				.setParameter(0, Integer.parseInt(id)).setParameter(1,Integer.parseInt(groupId)).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	public void deleteAll(String id) {
		Session session = super.getSession();
		session.createQuery("delete GroupUserInfo where userId = ?").setParameter(0, Integer.parseInt(id)).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getGroupIds(String id) {
		Session session = super.getSession();
		return session.createSQLQuery("select group_id from group_user_info where user_id = ?").setParameter(0, Integer.parseInt(id)).list();
	}
	
}
