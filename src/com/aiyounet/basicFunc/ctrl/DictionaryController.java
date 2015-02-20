package com.aiyounet.basicFunc.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aiyounet.basicFunc.dao.bean.Dictionary;
import com.aiyounet.basicFunc.dao.hibernate.bean.ParamerBean;
import com.aiyounet.basicFunc.service.DictionaryService;

@Controller
public class DictionaryController {
	@Resource
	private DictionaryService dictionaryService;
	
	@RequestMapping
	@ResponseBody
	public List<Map<String, Object>> getOneTagList() throws Exception{
		return dictionaryService.getOneTag();
	}
	
	@RequestMapping
	@ResponseBody
	public List<Map<String, Object>> getOneTagListEdit() throws Exception{
		List<Map<String, Object>> dics = new ArrayList<Map<String,Object>>();
		dics.addAll(dictionaryService.getOneTag());
		return dics;
	}
	
	@RequestMapping
	@ResponseBody
	public List<Map<String, Object>> getTwoTagList(String p_id) throws Exception{
		return dictionaryService.getTwoTag(p_id);
	}

	@RequestMapping
	@ResponseBody
	public Map<String, Object> getDictionaryList(ParamerBean dictionaryBean) throws Exception{
		return dictionaryService.findList(dictionaryBean);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> deleteDictionaryById(String id) throws Exception{
		return dictionaryService.deleteById(id);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> updateDictionary(String id,String p_id,String d_name) throws Exception{
		return dictionaryService.updateDictionary(id,p_id,d_name);
	}
	
	@RequestMapping
	@ResponseBody
	public Map<String, Object> addDictionary(String pp_id,String pName,String p_id,String pdName,String dName) throws Exception{
		return dictionaryService.addDictionary(pp_id, pName, p_id, pdName, dName);
	}
	@RequestMapping
	@ResponseBody
	public List<Map<String, Object>> getkeywordsList() throws Exception{
		return dictionaryService.getkeywordsList();
	}
	
	@RequestMapping
	@ResponseBody
	public List<Dictionary> getDictionaryId(String d_name) throws Exception{
		List<Dictionary> list = dictionaryService.getDictionaryId(d_name);
		return list;
		
	}
 
}
