package com.aiyounet.basicFunc.dao.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_dictionary")
public class Dictionary {
	private Integer id;
	private String name;
	private Integer pId;
	public Dictionary(Integer id, String name, Integer pId) {
		super();
		this.id = id;
		this.name = name;
		this.pId = pId;
	}
	public Dictionary() {
		super();
	}
	@Id
	@GeneratedValue
	@Column(name="id",unique=true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="d_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="p_id")
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
}
