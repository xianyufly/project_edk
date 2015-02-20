package com.aiyounet.basicFunc.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.aiyounet.basicFunc.dao.bean.Dictionary;
import com.aiyounet.basicFunc.dao.hibernate.bean.ParamerBean;
import com.aiyounet.basicFunc.util.StringUtil;
import com.aiyounet.frame.dao.db.BaseDaoImpl;

@Repository
public class DictionaryDao extends BaseDaoImpl<Dictionary>{
	private static final Logger log=LoggerFactory.getLogger(Dictionary.class);

	/**
	 * 获取一级标签
	 * @return
	 */
	public List<Map<String, Object>> getOneTag(){
		String sql="select id,d_name from t_dictionary where p_id=0";
		List<Map<String, Object>> list=this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	/**
	 * 获取二级标签
	 * @return
	 */
	public List<Map<String, Object>> getTwoTag(String p_id){
		String sql = "select id,d_name from t_dictionary where p_id=:pId";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pId",p_id);
		List<Map<String, Object>> list=this.getSession().createSQLQuery(sql).setProperties(map).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	/**
	 * 根据条件查询返回记录结果的条数
	 * @param str
	 * @return
	 */
	public int resultCount(String str){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer sql=new StringBuffer("select count(*) from t_dictionary t1 " +
				"left join t_dictionary t2 on t1.p_id=t2.id");
		if(!StringUtil.isNull(str)){
			sql.append("  where t1.d_name like :str");
			map.put("str", "%"+str+"%");
		}
		List list = this.getSession().createSQLQuery(sql.toString()).setProperties(map).list();
		return ((Number)list.get(0)).intValue();
	}
	
	/**
	 * 修改数据字典
	 * @param p_id
	 * @throws Exception 
	 */
	public boolean updateDictionary(String id,String p_id,String d_name){
		Map<String,Object> map = new HashMap<String, Object>();
		StringBuffer sql=new StringBuffer("update t_dictionary set ");
		if(p_id!=null && !"".equals(p_id)){
			sql.append("p_id=:pId");
			map.put("pId", p_id);
		}
		if(d_name!=null && !"".equals(d_name)){
			sql.append(",d_name=:dName ");
			map.put("dName", d_name);
		}
		if(id!=null && !"".equals(id)){
			sql.append(" where id=:Id");
			map.put("Id", id);
		}
		int i=this.getSession().createSQLQuery(sql.toString()).setProperties(map).executeUpdate();
		if(i!=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据关键字进行查询数据
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> findByLike(ParamerBean bean) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		Query query = null;
		StringBuffer sql=new StringBuffer("select t1.id id,t1.d_name d_name,t1.p_id p_id,t2.d_name p_name from t_dictionary t1 " +
				"left join t_dictionary t2 on t1.p_id=t2.id");
		if(!StringUtil.isNull(bean.getStr())){
			sql.append("  where t1.d_name like :str");
			map.put("str","%"+bean.getStr()+"%");
		}
		log.info("sql:"+sql.toString());
		query=this.getSession().createSQLQuery(sql.toString()).setProperties(map);
		if(bean.getPage().equals("1")){
			query.setFirstResult(0);
			query.setMaxResults(Integer.parseInt(bean.getRows()));
		}else{
			query.setFirstResult(bean.getStart());
			query.setMaxResults(Integer.parseInt(bean.getRows()));
		}
		List<Map<String, Object>> list=query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}
	
	/**
	 * 添加记录
	 * @param p_id
	 * @param pname
	 * @param d_name
	 * @return
	 */
	public boolean addDictionary(String pp_id,String pName,String p_id,String pdName,String dName){
		Map<String, Object> map=new HashMap<String, Object>();
		StringBuffer sql=new StringBuffer("insert into t_dictionary(d_name,p_id) ");
		Dictionary dic = new Dictionary();
		//选择添加一级标签
		if(pp_id.equals("0")){
			sql.append("values(:pName,0)");
			map.put("pName", pName);
		}else if(!pp_id.equals("0") && p_id.equals("0")){//选择添加二级标签
			sql.append("values(:pdName,:ppId)");
			map.put("pdName", pdName);
			map.put("ppId", pp_id);
		}else if(!pp_id.equals("0") && !p_id.equals("0")){//选择添加三级标签
			sql.append("values(:dName,:pId)");
			map.put("dName", dName);
			map.put("pId", p_id);
		}
		log.info("sql:"+sql.toString());
		int i = this.getSession().createSQLQuery(sql.toString()).setProperties(map).executeUpdate();
		if(i!=0){
			return true;
		}else{
			return false;
		}
	}
	public Integer getDictionaryIdByName(String name) throws Exception{
		Session se = super.getSession();
		return (Integer) se.createQuery("select id from Dictionary where name = :name").setParameter("name", name).uniqueResult();
	}
	public List<Map<String, Object>> getkeywordsList(Integer pId) throws Exception{
		Session se = super.getSession();		
		return se.createQuery("select id as id,name as text from Dictionary where pId = :pId").setParameter("pId", pId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	/*
	 * 邵哥哥写
	 */
	public List<Dictionary> getDictionaryId(String d_name){
		String sql="SELECT id,d_name as name,p_id as pId FROM t_dictionary WHERE d_name=?";
		List list = this.getSession().createSQLQuery(sql).setParameter(0, d_name).setResultTransformer(Transformers.aliasToBean(Dictionary.class)).list();
		return list;
		
	}
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(String id) throws Exception{
		String sql1="select p_id from t_dictionary where id=?";
		List<Map<String, Object>> list1=this.getSession().createSQLQuery(sql1).setParameter(0, id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		if(list1.get(0).get("p_id").equals(0)){
			String sql2="select id from t_dictionary where p_id=?";
			List<Map<String, Object>> list2=this.getSession().createSQLQuery(sql2).setParameter(0,id).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(list2.size()>0){
				return false;
			}else{
				delete(Integer.parseInt(id));
				return true;
			}
		}else{
			delete(Integer.parseInt(id));
			return true;
		}
	}
}
