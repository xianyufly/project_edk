package com.aiyounet.basicFunc.dao.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_group")
public class UserGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8194880910349183401L;
	private Integer id;
	private String groupName;
/*	private Set<GroupMenuInfo> groupMenuInfos = new HashSet<GroupMenuInfo>(0);*/

	// Constructors

	/** default constructor */
	public UserGroup() {
	}

	/** full constructor */
	public UserGroup(String groupName, Set<GroupMenuInfo> groupMenuInfos) {
		this.groupName = groupName;
//		this.groupMenuInfos = groupMenuInfos;
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

	@Column(name = "group_name", length = 64)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userGroup")
	public Set<GroupMenuInfo> getGroupMenuInfos() {
		return this.groupMenuInfos;
	}

	public void setGroupMenuInfos(Set<GroupMenuInfo> groupMenuInfos) {
		this.groupMenuInfos = groupMenuInfos;
	}*/

}