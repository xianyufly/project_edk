package com.aiyounet.basicFunc.dao.hibernate.bean;

public class ParamerBean {
	private String str;
	private String rows="20";
	private String page="1";
	private String platId;
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	public String getPlatId() {
		return platId;
	}
	public void setPlatId(String platId) {
		this.platId = platId;
	}
	

	/**
	 * 获取分页起始记录
	 * @return
	 */
	public int getStart(){
		return (Integer.parseInt(page)-1)*Integer.parseInt(rows)+1;
	}
}
