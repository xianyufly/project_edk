package com.aiyounet.basicFunc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aiyounet.basicFunc.dao.bean.Dictionary;
import com.aiyounet.basicFunc.dao.hibernate.DictionaryDao;
import com.aiyounet.basicFunc.dao.hibernate.bean.ParamerBean;
import com.aiyounet.basicFunc.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService{
	@Resource
	private DictionaryDao dictionaryDao;
	
	public Map<String, Object> findList(ParamerBean dictionaryBean) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> disc=dictionaryDao.findByLike(dictionaryBean);
		int total=dictionaryDao.resultCount(dictionaryBean.getStr());
		map.put("total", total);
		map.put("rows", disc);
		return map;
	}
	/**
	 * 动态增加一条记录后获取所有一级标签
	 */
	public List<Map<String, Object>> getOneTag() throws Exception {
		List<Map<String, Object>> dics=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", "0");
		map.put("d_name", " --- 添加一级标签  ---");
		dics.add(map);
		dics.addAll(dictionaryDao.getOneTag());
		return dics;
	}
	
	/**
	 * 动态增加一条记录后获取所有二级标签
	 */
	public List<Map<String, Object>> getTwoTag(String p_id) throws Exception {
		List<Map<String, Object>> dics=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", "0");
		map.put("d_name", " --- 添加二级标签  ---");
		dics.add(map);
		dics.addAll(dictionaryDao.getTwoTag(p_id));
		return dics;
	}

	public Map<String, Object> deleteById(String id) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		boolean flag=dictionaryDao.deleteById(id);
		if(flag){
			map.put("success", 1);
		}else{
			map.put("success", 0);
		}
		return map;
	}

	public Map<String, Object> updateDictionary(String id,String p_id,String d_name) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		boolean flag = dictionaryDao.updateDictionary(id, p_id, d_name);
		if(flag){
			map.put("success", 1);
		}else{
			map.put("success", 0);
		}
		return map;
	}

	public Map<String, Object> addDictionary(String pp_id,String pName,String p_id,String pdName,String dName)
			throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		boolean flag = dictionaryDao.addDictionary(pp_id, pName, p_id, pdName, dName);
		if(flag){
			map.put("success", "1");
		}else{
			map.put("success", "0");
		}
		return map;
	}

	public List<Dictionary> getDictionaryId(String d_name) throws Exception {
		return dictionaryDao.getDictionaryId(d_name);
	}

	public List<Map<String, Object>> getkeywordsList() throws Exception {		
		Integer pId = dictionaryDao.getDictionaryIdByName("关键字");
		return dictionaryDao.getkeywordsList(pId);
	}

}
