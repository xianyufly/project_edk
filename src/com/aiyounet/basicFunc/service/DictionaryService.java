package com.aiyounet.basicFunc.service;

import java.util.List;
import java.util.Map;

import com.aiyounet.basicFunc.dao.bean.Dictionary;
import com.aiyounet.basicFunc.dao.hibernate.bean.ParamerBean;


public interface DictionaryService {
	public Map<String, Object> findList(ParamerBean dictionaryBean) throws Exception;
	
	public List<Map<String, Object>> getOneTag() throws Exception;
	
	public List<Map<String, Object>> getTwoTag(String p_id) throws Exception;
	
	public Map<String, Object> deleteById(String id) throws Exception;
	
	public Map<String, Object> updateDictionary(String id,String p_id,String d_name) throws Exception;
	
	public Map<String, Object> addDictionary(String pp_id,String pName,String p_id,String pdName,String dName) throws Exception;

	public List<Dictionary> getDictionaryId(String d_name) throws Exception;
	public List<Map<String, Object>> getkeywordsList() throws Exception;
}
