package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.UserGroup;
import com.aiyounet.frame.dao.db.BaseDaoImpl;
@Repository
public class UserGroupDao  extends BaseDaoImpl<UserGroup>{

	@SuppressWarnings("unchecked")
	public List<UserGroup> getGroups() {
		Session	session = super.getSession();
		return session.createQuery("from UserGroup").list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getGroupIds() {
		Session	session = super.getSession();
		return session.createSQLQuery("select id from user_group").list();
	}

}
