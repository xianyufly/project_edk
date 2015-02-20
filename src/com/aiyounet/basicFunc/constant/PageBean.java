package com.aiyounet.basicFunc.constant;

public class PageBean {
	private Integer page = 1;

	private Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public PageBean() {
		super();
	}

	public PageBean(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getStart() {
		return (this.page - 1) * this.rows;
	}
	/**
	 * list伪分页使用
	 * @Title: getStart 
	 * @param length
	 * @return
	 * @throws 
	 * @author Mirror 2015-1-15
	 */
	public Integer getStart(int length) {
		int end = this.page * this.rows - 1;
		if(length - 1 < end){
			return length  - length%this.rows;//返回最后一页开始页码
		}
		return (this.page - 1) * this.rows;
	}
	/**
	 * list伪分页使用
	 * @Title: getEnd 
	 * @param length list长度
	 * @return
	 * @throws 
	 * @author Mirror 2015-1-15
	 */
	public Integer getEnd(int length){
		int end = this.page * this.rows - 1;
		if(length - 1 < end){
			return length - 1;
		}
		return  this.page * this.rows - 1;
	}
	public static void main(String[] args) {
		PageBean page = new PageBean(1, 10);
		System.out.println(page.getStart(12));
		System.out.println(page.getEnd(12));
	}
}
