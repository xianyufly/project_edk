package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OperLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "oper_log")
public class OperLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1752750796006733011L;
	// Fields

	private Integer id;
	private String userName;
	private String actionContent;
	private String operTime;
	private String ip;
	private Integer platId;

	// Constructors

	/** default constructor */
	public OperLog() {
	}

	/** full constructor */
	public OperLog(String userName, String actionContent, String operTime,String ip,Integer platId) {
		this.userName = userName;
		this.actionContent = actionContent;
		this.operTime = operTime;
		this.ip=ip;
		this.platId = platId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_name", length = 64)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "action_content", length = 1024)
	public String getActionContent() {
		return this.actionContent;
	}

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}

	@Column(name = "oper_time", length = 64)
	public String getOperTime() {
		return this.operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name="plat_id")
	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}
	
}