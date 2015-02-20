package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7277030961058295371L;
	private Integer id;
	private String userName;
	private String nickName;// v
	private String password;// v
	private String drawPass;// v
	private String qq;// v
	private String email;// v
	private Double money;
	private Integer userType;// v
	private Double point;// v
	private String bankName;// v
	private String accoName;// v
	private String accoNumb;// v
	private Integer PId;// v
	private String lastLoginTime;// v
	private String lastLoginIp;// v
	private Integer onlineStatus;
	private Double rewardPoint;// v
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;
	private String question3;
	private String answer3;
	private Integer isProtect;
	private String token;// v
	private Integer isStop;
	private String registTime;
	private Integer model;
	private String bankMemo;
	private String registIp;
	private String descrip;// v
	private Double ensureMoney;// v
	private String errTime = "";// v当日首次密码错误时间
	private Integer errCount;// v用于计算密码错误次数
	private Double dividedPoint;// v
	private String moreInfo;// v
	private String isWhiteUser;
	private String errAccoTime = "";// v
	private Integer errAccoCount;// v
	private String isFirstAgent;
	private Integer isFreeze;
	private Double lowerPoint;
	private String lastLoginTag;
	private Double limitOdds;
	private String limitTime;
	private Integer qqLimit;
	private String  firstRechTime;
	private Double firstRechValue;
	private Integer platId;
	private String platName;
	private String address;

	// Constructors
	@Column(name="plat_name")
	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(String userName, String nickName, String password, String drawPass, String qq,
			String email, Double money, Integer userType, Double point, String bankName,
			String accoName, String accoNumb, Integer PId, String lastLoginTime,
			String lastLoginIp, Integer onlineStatus, Double rewardPoint, String question1,
			String answer1, String question2, String answer2, String question3, String answer3,
			Integer isProtect, String token, Integer isStop, String registTime, Integer model,
			String bankMemo, String registIp, String descrip, Double ensureMoney, String errTime,
			Integer errCount, Double dividedPoint, String moreInfo, String isWhiteUser,
			String errAccoTime, Integer errAccoCount, Integer isFreeze, Double lowerPoint,
			String lastLoginTag, Double limitOdds, String limitTime,Integer qqLimit,String firstRechTime,Double firstRechValue,Integer platId) {
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.drawPass = drawPass;
		this.qq = qq;
		this.email = email;
		this.money = money;
		this.userType = userType;
		this.point = point;
		this.bankName = bankName;
		this.accoName = accoName;
		this.accoNumb = accoNumb;
		this.PId = PId;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIp = lastLoginIp;
		this.onlineStatus = onlineStatus;
		this.rewardPoint = rewardPoint;
		this.question1 = question1;
		this.answer1 = answer1;
		this.question2 = question2;
		this.answer2 = answer2;
		this.question3 = question3;
		this.answer3 = answer3;
		this.isProtect = isProtect;
		this.token = token;
		this.isStop = isStop;
		this.registTime = registTime;
		this.model = model;
		this.bankMemo = bankMemo;
		this.registIp = registIp;
		this.descrip = descrip;
		this.ensureMoney = ensureMoney;
		this.errTime = errTime;
		this.errCount = errCount;
		this.dividedPoint = dividedPoint;
		this.moreInfo = moreInfo;
		this.isWhiteUser = isWhiteUser;
		this.errAccoTime = errAccoTime;
		this.errAccoCount = errAccoCount;
		this.isFreeze = isFreeze;
		this.lowerPoint = lowerPoint;
		this.lastLoginTag = lastLoginTag;
		this.limitOdds = limitOdds;
		this.limitTime = limitTime;
		this.qqLimit = qqLimit;
		this.firstRechTime = firstRechTime;
		this.firstRechValue = firstRechValue;
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

	@Column(name = "nick_name", length = 64)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "draw_pass", length = 64)
	public String getDrawPass() {
		return this.drawPass;
	}

	public void setDrawPass(String drawPass) {
		this.drawPass = drawPass;
	}

	@Column(name = "qq", length = 64)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "money", precision = 16)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "user_type")
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "point", precision = 8)
	public Double getPoint() {
		return this.point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	@Column(name = "bank_name", length = 64)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "acco_name", length = 64)
	public String getAccoName() {
		return this.accoName;
	}

	public void setAccoName(String accoName) {
		this.accoName = accoName;
	}

	@Column(name = "acco_numb", length = 64)
	public String getAccoNumb() {
		return this.accoNumb;
	}

	public void setAccoNumb(String accoNumb) {
		this.accoNumb = accoNumb;
	}

	@Column(name = "p_id")
	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	@Column(name = "last_login_time", length = 64)
	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip", length = 64)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "online_status")
	public Integer getOnlineStatus() {
		return this.onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	@Column(name = "reward_point", precision = 8)
	public Double getRewardPoint() {
		return this.rewardPoint;
	}

	public void setRewardPoint(Double rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	@Column(name = "question1", length = 10)
	public String getQuestion1() {
		return this.question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	@Column(name = "answer1", length = 256)
	public String getAnswer1() {
		return this.answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	@Column(name = "question2", length = 10)
	public String getQuestion2() {
		return this.question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Column(name = "answer2", length = 256)
	public String getAnswer2() {
		return this.answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@Column(name = "question3", length = 10)
	public String getQuestion3() {
		return this.question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	@Column(name = "answer3", length = 256)
	public String getAnswer3() {
		return this.answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	@Column(name = "is_protect")
	public Integer getIsProtect() {
		return this.isProtect;
	}

	public void setIsProtect(Integer isProtect) {
		this.isProtect = isProtect;
	}

	@Column(name = "token", length = 64)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "is_stop")
	public Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}

	@Column(name = "regist_time")
	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTimel) {
		this.registTime = registTimel;
	}

	@Column(name = "model")
	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	@Column(name = "bank_memo")
	public String getBankMemo() {
		return bankMemo;
	}

	public void setBankMemo(String bankMemo) {
		this.bankMemo = bankMemo;
	}

	@Column(name = "regist_ip")
	public String getRegistIp() {
		return registIp;
	}

	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}

	@Column(name = "descrip")
	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	@Column(name = "ensure_money")
	public Double getEnsureMoney() {
		return ensureMoney;
	}

	public void setEnsureMoney(Double ensureMoney) {
		this.ensureMoney = ensureMoney;
	}

	@Column(name = "err_time", length = 60)
	public String getErrTime() {
		return errTime;
	}

	public void setErrTime(String errTime) {
		this.errTime = errTime;
	}

	@Column(name = "err_count")
	public Integer getErrCount() {
		return errCount;
	}

	public void setErrCount(Integer errCount) {
		this.errCount = errCount;
	}

	@Column(name = "divided_point", precision = 5)
	public Double getDividedPoint() {
		return dividedPoint;
	}

	public void setDividedPoint(Double dividedPoint) {
		this.dividedPoint = dividedPoint;
	}

	@Column(name = "more_info", length = 1000)
	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Column(name = "is_white_user", length = 5)
	public String getIsWhiteUser() {
		return isWhiteUser;
	}

	public void setIsWhiteUser(String isWhiteUser) {
		this.isWhiteUser = isWhiteUser;
	}

	@Column(name = "err_acco_time", length = 60)
	public String getErrAccoTime() {
		return errAccoTime;
	}

	public void setErrAccoTime(String errAccoTime) {
		this.errAccoTime = errAccoTime;
	}

	@Column(name = "err_acco_count")
	public Integer getErrAccoCount() {
		return errAccoCount;
	}

	public void setErrAccoCount(Integer errAccoCount) {
		this.errAccoCount = errAccoCount;
	}

	@Transient
	public String getIsFirstAgent() {
		return isFirstAgent;
	}

	public void setIsFirstAgent(String isFirstAgent) {
		this.isFirstAgent = isFirstAgent;
	}

	@Column(name = "is_freeze")
	public Integer getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Column(name = "lower_point", precision = 8)
	public Double getLowerPoint() {
		return lowerPoint;
	}

	public void setLowerPoint(Double lowerPoint) {
		this.lowerPoint = lowerPoint;
	}

	@Column(name = "last_login_tag", length = 10)
	public String getLastLoginTag() {
		return lastLoginTag;
	}

	public void setLastLoginTag(String lastLoginTag) {
		this.lastLoginTag = lastLoginTag;
	}

	@Column(name = "limit_odds", precision = 5)
	public Double getLimitOdds() {
		return limitOdds;
	}

	public void setLimitOdds(Double limitOdds) {
		this.limitOdds = limitOdds;
	}

	@Column(name = "limit_time", length = 20)
	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}
	@Column(name = "qq_limit")
	public Integer getQqLimit() {
		return qqLimit;
	}

	public void setQqLimit(Integer qqLimit) {
		this.qqLimit = qqLimit;
	}
	@Column(name = "first_rech_time",length=20)
	public String getFirstRechTime() {
		return firstRechTime;
	}

	public void setFirstRechTime(String firstRechTime) {
		this.firstRechTime = firstRechTime;
	}
	@Column(name = "first_rech_value", precision = 11 )
	public Double getFirstRechValue() {
		return firstRechValue;
	}

	public void setFirstRechValue(Double firstRechValue) {
		this.firstRechValue = firstRechValue;
	}
	@Column(name="plat_id")
	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}
	@Column(name = "address",length=500)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}