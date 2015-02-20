package com.aiyounet.basicFunc.dao.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GroupUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group_user_info")
public class GroupUserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7823383523209945952L;
	private Integer id;
	private Integer userId;
	private Integer groupId;

	// Constructors

	/** default constructor */
	public GroupUserInfo() {
	}

	/** full constructor */
	public GroupUserInfo(Integer userId, Integer groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}