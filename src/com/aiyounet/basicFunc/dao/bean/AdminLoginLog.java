package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin_login_log")
public class AdminLoginLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3842736276507996383L;
	private Integer id;
	private String userName;
	private String loginTime;
	private String loginIp;


	
	// Constructors

	/** default constructor */
	public AdminLoginLog() {
	}

	/** full constructor */
	public AdminLoginLog(String userName, String loginTime, String loginIp) {
		this.userName = userName;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
 
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

	@Column(name = "login_time", length = 64)
	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "login_ip", length = 64)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

 
}