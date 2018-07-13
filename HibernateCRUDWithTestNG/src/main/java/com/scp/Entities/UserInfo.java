package com.scp.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class UserInfo {
	@Id
	private int userId;
	private String userName;
	private String password;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "\nUserInfo [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}
