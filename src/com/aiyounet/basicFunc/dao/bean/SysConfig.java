package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_config")
public class SysConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4675759515476013481L;
	private Integer id;
	private Integer canReg;
	private String userNameFilter;
	private String passFilter;
	private String curVersion;
	private String sycnVersion;
	private String startFile;
	private String startUrl;
	private String customerServiceAddr;
	private Integer isOpenPoint;
	private Integer verifyDays;
	private Integer fenMode;
	private Double oneIssueMaxReward;
	private Double dangerValue1;
	private Double dangerValue2;
	private Double integralRate;
	private Integer platId;
	private Double dangerValue3;
	private Integer isBet;
	private Integer isOpen;
	private Integer isLimit;
	private Double limitOdds;
	private Integer limitType;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	// Constructors
	@Column(name="plat_id")
	public Integer getPlatId() {
		return platId;
	}


	public void setPlatId(Integer platId) {
		this.platId = platId;
	}


	/** default constructor */
	public SysConfig() {
	}


	public SysConfig(Integer canReg, String userNameFilter, String passFilter, String curVersion,
		String sycnVersion, String startFile, String startUrl, String customerServiceAddr,
		Integer isOpenPoint, Integer verifyDays, Integer fenMode, Double oneIssueMaxReward,
		Double dangerValue1, Double dangerValue2, Double integralRate,Double dangerValue3,Integer isBet,Integer isLimit,Integer isOpen,Double limitOdds) {
	super();
	this.canReg = canReg;
	this.userNameFilter = userNameFilter;
	this.passFilter = passFilter;
	this.curVersion = curVersion;
	this.sycnVersion = sycnVersion;
	this.startFile = startFile;
	this.startUrl = startUrl;
	this.customerServiceAddr = customerServiceAddr;
	this.isOpenPoint = isOpenPoint;
	this.verifyDays = verifyDays;
	this.fenMode = fenMode;
	this.oneIssueMaxReward = oneIssueMaxReward;
	this.dangerValue1 = dangerValue1;
	this.dangerValue2 = dangerValue2;
	this.integralRate = integralRate;
	this.isBet = isBet;
	this.isLimit = isLimit;
	this.isOpen = isOpen;
	this.limitOdds = limitOdds;
	this.dangerValue3 = dangerValue3;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "can_reg")
	public Integer getCanReg() {
		return this.canReg;
	}

	public void setCanReg(Integer canReg) {
		this.canReg = canReg;
	}
	@Column(name = "fen_mode")
	public Integer getFenMode() {
		return fenMode;
	}

	public void setFenMode(Integer fenMode) {
		this.fenMode = fenMode;
	}

	@Column(name = "user_name_filter", length = 64)
	public String getUserNameFilter() {
		return this.userNameFilter;
	}

	public void setUserNameFilter(String userNameFilter) {
		this.userNameFilter = userNameFilter;
	}

	@Column(name = "pass_filter", length = 64)
	public String getPassFilter() {
		return this.passFilter;
	}

	public void setPassFilter(String passFilter) {
		this.passFilter = passFilter;
	}

	@Column(name = "cur_version", length = 64)
	public String getCurVersion() {
		return this.curVersion;
	}

	public void setCurVersion(String curVersion) {
		this.curVersion = curVersion;
	}

	@Column(name = "sycn_version", length = 64)
	public String getSycnVersion() {
		return this.sycnVersion;
	}

	public void setSycnVersion(String sycnVersion) {
		this.sycnVersion = sycnVersion;
	}
	@Column(name = "start_file", length = 64)
	public String getStartFile() {
		return startFile;
	}

	public void setStartFile(String startFile) {
		this.startFile = startFile;
	}
	@Column(name = "start_url")
	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}
	@Column(name="customerServiceAddr")
	public String getCustomerServiceAddr() {
		return customerServiceAddr;
	}

	public void setCustomerServiceAddr(String customerServiceAddr) {
		this.customerServiceAddr = customerServiceAddr;
	}
	@Column(name="is_open_point")
	public Integer getIsOpenPoint() {
		return isOpenPoint;
	}

	public void setIsOpenPoint(Integer isOpenPoint) {
		this.isOpenPoint = isOpenPoint;
	}
	@Column(name="verify_days")
	public Integer getVerifyDays() {
		return verifyDays;
	}

	public void setVerifyDays(Integer verifyDays) {
		this.verifyDays = verifyDays;
	}
	@Column(name="one_issue_max_reward")
	public Double getOneIssueMaxReward() {
		return oneIssueMaxReward;
	}
	public void setOneIssueMaxReward(Double oneIssueMaxReward) {
		this.oneIssueMaxReward = oneIssueMaxReward;
	}

	@Column(name="danger_value1", precision = 11)
	public Double getDangerValue1() {
		return dangerValue1;
	}
	public void setDangerValue1(Double dangerValue1) {
		this.dangerValue1 = dangerValue1;
	}

	@Column(name="danger_value2" , precision = 11)
	public Double getDangerValue2() {
		return dangerValue2;
	}

	public void setDangerValue2(Double dangerValue2) {
		this.dangerValue2 = dangerValue2;
	}
	
	@Column(name="integral_rate" , precision = 11)
	public Double getIntegralRate() {
		return integralRate;
	}


	public void setIntegralRate(Double integralRate) {
		this.integralRate = integralRate;
	}
	@Column(name="danger_value3" , precision = 11)
	public Double getDangerValue3() {
		return dangerValue3;
	}


	public void setDangerValue3(Double dangerValue3) {
		this.dangerValue3 = dangerValue3;
	}


	@Column(name="is_bet")
	public Integer getIsBet() {
		return isBet;
	}


	public void setIsBet(Integer isBet) {
		this.isBet = isBet;
	}


	@Column(name="is_open")
	public Integer getIsOpen() {
		return isOpen;
	}


	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	@Column(name="is_limit")
	public Integer getIsLimit() {
		return isLimit;
	}


	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}

	@Column(name="limit_odds" , precision = 11)
	public Double getLimitOdds() {
		return limitOdds;
	}


	public void setLimitOdds(Double limitOdds) {
		this.limitOdds = limitOdds;
	}

	@Column(name="limit_type" )
	public Integer getLimitType() {
		return limitType;
	}


	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}


	@Override
	public String toString() {
		return "SysConfig [id=" + id + ", canReg=" + canReg + ", userNameFilter=" + userNameFilter
				+ ", passFilter=" + passFilter + ", curVersion=" + curVersion + ", sycnVersion="
				+ sycnVersion + ", startFile=" + startFile + ", startUrl=" + startUrl
				+ ", customerServiceAddr=" + customerServiceAddr + ", isOpenPoint=" + isOpenPoint
				+ ", verifyDays=" + verifyDays + ", fenMode=" + fenMode + ", oneIssueMaxReward="
				+ oneIssueMaxReward + "]";
	}

}