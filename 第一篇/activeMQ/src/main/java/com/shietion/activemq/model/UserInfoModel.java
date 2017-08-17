package com.shietion.activemq.model;

import java.io.Serializable;

public class UserInfoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5690251061796561938L;

	private int id ;
	
	private String name ;
	
	private String sex ;
	
	private String phone ;
	
	private String email ;
	
	
	

	public UserInfoModel(int id, String name, String sex, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserInfoModel [id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", email=" + email
				+ "]";
	}
}
