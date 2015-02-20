package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GroupMenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "group_menu_info")
public class GroupMenuInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2132742250229828029L;
	// Fields

	private Integer id;
	private Menu menu;
	private UserGroup userGroup;

	// Constructors

	/** default constructor */
	public GroupMenuInfo() {
	}

	/** minimal constructor */
	public GroupMenuInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GroupMenuInfo(Integer id, Menu menu, UserGroup userGroup) {
		this.id = id;
		this.menu = menu;
		this.userGroup = userGroup;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}