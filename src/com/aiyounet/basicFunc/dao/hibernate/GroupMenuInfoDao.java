package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.GroupMenuInfo;
import com.aiyounet.frame.dao.db.BaseDaoImpl;
@Repository
public class GroupMenuInfoDao extends BaseDaoImpl<GroupMenuInfo>{
	@SuppressWarnings("unchecked")
	public List<Integer> getMenuIds(List<Integer> list) {
		Session session = super.getSession();
		if(list.size()>0)
		return	session.createSQLQuery(
				"select menu_id from group_menu_info where group_id in (:list) ")
				.setParameterList("list", list).list();
		return null;
  }

	@SuppressWarnings("unchecked")
	public List<Integer> getMenuIds(String id) {
		Session session = super.getSession();
		return	session.createSQLQuery(
				"select menu_id from group_menu_info where group_id = "+id)
				.list();
	}
	public void deleteAll(String id) {
		Session session = super.getSession();
		session.createQuery("delete GroupMenuInfo where group_id = ?").setParameter(0, Integer.parseInt(id)).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public GroupMenuInfo getGroupUser(String id, String menusId) {
		Session session = super.getSession();
		List<GroupMenuInfo> list = session
				.createQuery(
						"from GroupMenuInfo o where o.userGroup.id= :id  and o.menu.id =:menusId ")
				.setParameter("id", Integer.parseInt(id)).setParameter("menusId",Integer.parseInt(menusId)).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	public Integer getGroupUserBymenu(int menuId,String groups) {
		Session session = super.getSession();
		Object r=session
				.createSQLQuery("select count(*) from group_menu_info where menu_id=? and group_id in (?)").setParameter(0, menuId).setParameter(1,groups).uniqueResult();
		return Integer.parseInt(r.toString());
		
	}
}
