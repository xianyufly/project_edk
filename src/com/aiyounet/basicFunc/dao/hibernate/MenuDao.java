package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.constant.PageBean;
import com.aiyounet.basicFunc.dao.bean.Menu;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class MenuDao extends BaseDaoImpl<Menu> {

	@SuppressWarnings("unchecked")
	public List<Menu> getMenusForGroup(List<Integer> listMenuId) {
		Session session = super.getSession();
		if (listMenuId.size() > 0)
			return session.createQuery("from Menu where id in (:listMenuId) order by order_by")
					.setParameterList("listMenuId", listMenuId).list();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenus() {
		Session session = super.getSession();

		return session.createQuery("from Menu ").list();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMenus(PageBean page) {
		Session session = super.getSession();

		return session
				.createSQLQuery(
						"select m.id,m.text,m.url,u.text parent,m.has_url hasUrl from menu m left join menu u on m.p_id = u.id")
				.setFirstResult(page.getStart()).setMaxResults(page.getRows())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	public Integer getMenusTotal() {
		Session session = super.getSession();

		return session.createQuery("from Menu").list().size();
	}
	@SuppressWarnings("unchecked")
	public List<Object> getHasUrlForUser(Integer id) {
		Session session = super.getSession();
		return session
				.createSQLQuery(
						"select  u.has_url  hasUrl  from group_user_info g ,group_menu_info m,menu u where g.group_id=m.group_id and m.menu_id = u.id and g.user_id=? and u.p_id!=0")
				.setParameter(0, id).list();
	}
	
}
