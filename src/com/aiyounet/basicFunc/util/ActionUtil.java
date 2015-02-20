package com.aiyounet.basicFunc.util;

import javax.servlet.http.HttpServletRequest;

public class ActionUtil {
	public static String detilUrl(String url, HttpServletRequest request) {
		String contor = url.split("/")[1];
		String method = url.split("/")[2];
		String action = null;
		if (contor.equals("collect".toLowerCase())){
			if (method.indexOf("insertCollectConfig") != -1){
				action = "增加采集规则，平台ID：paltId:" + request.getParameter("paltId");
			}else if (method.indexOf("deleteRecord") != -1){
				action = "删除采集规则，规则（collectConfig）ID:" + request.getParameter("id");
			}else if (method.indexOf("updateCollectConfig") != -1){
				action = "修改采集规则，规则（collectConfig）ID:" + request.getParameter("id");			
			}else
				action = null;	
		}else if (contor.equals("dictionary".toLowerCase())){
			if (method.indexOf("addDictionary") != -1){
				action = "增加标签 上级pp_id=" + request.getParameter("pp_id") + "p_id=" + request.getParameter("p_id") + "标签名："+request.getParameter("dName");
			}else if (method.indexOf("updateDictionary")!= -1){
				action = "修改标签Id：" + request.getParameter("id") + "上级p_id" + request.getParameter("p_id") + "标签名："+request.getParameter("dName");
			}else if (method.indexOf("addPlat")!= -1){
				action = "批量增加平台关联，标签id：" + request.getParameter("tagId") + "平台名称：" + request.getParameter("platName");
			}else if (method.indexOf("deleteDictionaryById")!= -1){
				action = "删除标签，id=" + request.getParameter("id");
			}else
				action = null;
		}else if (contor.equals("platmanage".toLowerCase())){
			if(method.indexOf("delete".toLowerCase())!= -1){
				action = "删除平台，id=" + request.getParameter("id");
			}else 
				action = null;
		} else if (contor.equals("userManager".toLowerCase())) {
			if(method.indexOf("addVip") != -1){
				action = "添加会员,用户名为"+request.getParameter("userName");
			}else if(method.indexOf("updatePass") != -1){
				action = "修改会员密码,用户ID为:"+request.getParameter("id")
						+",密码为"+request.getParameter("newPass");
			}else if(method.indexOf("isStop") != -1){
				action = "用户停用管理,用户ID为"+request.getParameter("id")
						+",状态修改为"+request.getParameter("isStop");
			}else{
				action = null;
			}
		}else if (contor.equals("TodayCount".toLowerCase())) {
			action = null;
		}else if (contor.equals("TradeRecode".toLowerCase())) {
			action = null;
		}else{
				action = null;		
		}
		return action;
	}
}
