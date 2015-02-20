package com.aiyounet.basicFunc.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.SysConfig;
import com.aiyounet.frame.dao.db.BaseDaoImpl;
@Repository
public class SysConfigDao extends BaseDaoImpl<SysConfig> {
	
	public List<SysConfig> FindAllByPlatId(String platIdStr){
		String hql = "from SysConfig where platId in ("+platIdStr+")";
		return this.getSession().createQuery(hql).list();
	}
}
