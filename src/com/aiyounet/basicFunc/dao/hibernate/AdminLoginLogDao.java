package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.AdminLoginLog;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class AdminLoginLogDao extends BaseDaoImpl<AdminLoginLog> {

	@SuppressWarnings("unchecked")
	public List<AdminLoginLog> findAll(PageBean page, String userName) {
		Session session = super.getSession();
		if (userName == null)
			return session.createQuery("from AdminLoginLog order by id desc")
					.setFirstResult(page.getStart())
					.setMaxResults(page.getRows()).list();
		else
			return session.createQuery("from AdminLoginLog where userName=? order by id desc")
					.setParameter(0, userName).setFirstResult(page.getStart())
					.setMaxResults(page.getRows()).list();
	}

}
