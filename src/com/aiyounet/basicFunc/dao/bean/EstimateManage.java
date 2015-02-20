package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

/**
 * TEstimate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_estimate")
public class EstimateManage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private Integer userId;
	private String userName;
	private Integer platId;
	private String platName;
	private String estimate;
	private Integer serve;
	private Integer experience;
	private Integer safety;
	private Integer isDelete;
	private String addTime;

	// Constructors

	/** default constructor */
	public EstimateManage() {
	}

	/** full constructor */
	public EstimateManage(Integer userId, String userName, Integer platId,
			String platName, String estimate, Integer serve,
			Integer experience, Integer safety, Integer isDelete) {
		this.userId = userId;
		this.userName = userName;
		this.platId = platId;
		this.platName = platName;
		this.estimate = estimate;
		this.serve = serve;
		this.experience = experience;
		this.safety = safety;
		this.isDelete = isDelete;
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

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "plat_id", nullable = false)
	public Integer getPlatId() {
		return this.platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

	@Column(name = "estimate", nullable = false, length = 1024)
	public String getEstimate() {
		return this.estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	@Column(name = "serve", nullable = false)
	public Integer getServe() {
		return this.serve;
	}
	@Column(name = "userName", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "platName", nullable = false)
	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public void setServe(Integer serve) {
		this.serve = serve;
	}

	@Column(name = "experience", nullable = false)
	public Integer getExperience() {
		return this.experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Column(name = "safety", nullable = false)
	public Integer getSafety() {
		return this.safety;
	}
	@Column(name = "addTime", nullable = false)
	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setSafety(Integer safety) {
		this.safety = safety;
	}

	@Column(name = "is_delete", nullable = false)
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}