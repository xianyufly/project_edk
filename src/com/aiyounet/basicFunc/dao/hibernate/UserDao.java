package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.UserManage;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class UserDao extends BaseDaoImpl<UserManage>{
	public List<Map<String, Object>> getUserNameList() throws Exception{
		Session se = super.getSession();
		List<Map<String, Object>> list = se.createQuery("select id as id, userName as text from UserManage where isDelete = 0").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();			
		return list;
	}
}
