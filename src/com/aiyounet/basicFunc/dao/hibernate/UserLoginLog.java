package com.aiyounet.basicFunc.dao.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_login_log")
public class UserLoginLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -51844033953310861L;
	private Integer id;
	private String userName;
	private String ipAddr;
	private String evenTime;
	private Integer type;
	private String mac;
	private String ipSearch;
	private String loginTag;
	private Integer userId;
	private Integer platId;
	// Constructors

	/** default constructor */
	public UserLoginLog() {
	}

	/** full constructor */
	public UserLoginLog(String userName, String ipAddr, String evenTime,
			Integer type,String mac,String ipSearch,String loginTag,Integer userId,Integer platId) {
		this.userName = userName;
		this.ipAddr = ipAddr;
		this.evenTime = evenTime;
		this.type = type;
		this.mac = mac;
		this.ipSearch = ipSearch;
		this.loginTag = loginTag;
		this.userId = userId;
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

	@Column(name = "ip_addr", length = 64)
	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Column(name = "even_time", length = 64)
	public String getEvenTime() {
		return this.evenTime;
	}

	public void setEvenTime(String evenTime) {
		this.evenTime = evenTime;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "mac", length = 100)
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	@Column(name = "ip_search", length = 64)
	public String getIpSearch() {
		return ipSearch;
	}

	public void setIpSearch(String ipSearch) {
		this.ipSearch = ipSearch;
	}
	@Column(name = "login_tag", length = 20)
	public String getLoginTag() {
		return loginTag;
	}

	public void setLoginTag(String loginTag) {
		this.loginTag = loginTag;
	}
	@Column(name = "user_id" )
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "plat_id" )
	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

}