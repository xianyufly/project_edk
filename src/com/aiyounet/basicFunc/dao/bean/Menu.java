package com.aiyounet.basicFunc.dao.bean;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "menu")
public class Menu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6002771792424876267L;
	private Integer id;
	private String text;
	private String url;
	private Integer PId;
	private String hasUrl;
	private Integer isShort;
//	private Set<GroupMenuInfo> groupMenuInfos = new HashSet<GroupMenuInfo>(0);
	
	// Constructors

	

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String text, String url, Integer PId, String hasUrl) {
		this.text = text;
		this.url = url;
		this.PId = PId;
		this.hasUrl = hasUrl;
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

	@Column(name = "text", length = 64)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "url", length = 256)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "p_id")
	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}
	@Column(name = "has_url")
	public String getHasUrl() {
		return hasUrl;
	}

	public void setHasUrl(String hasUrl) {
		this.hasUrl = hasUrl;
	}
	
	@Column(name="is_short")
	public Integer getIsShort() {
		return isShort;
	}

	public void setIsShort(Integer isShort) {
		this.isShort = isShort;
	}

/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<GroupMenuInfo> getGroupMenuInfos() {
		return this.groupMenuInfos;
	}

	public void setGroupMenuInfos(Set<GroupMenuInfo> groupMenuInfos) {
		this.groupMenuInfos = groupMenuInfos;
	}*/

}