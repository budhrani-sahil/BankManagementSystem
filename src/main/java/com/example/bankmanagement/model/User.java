package com.example.bankmanagement.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;


@Entity
public class User extends RepresentationModel<User>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
		
	private String password;
	private String role;
	
		
	public User(int userId, String userName, String password, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}


	public User() {
		super();
	}


	public User( int userId,String userName, String password) {
		super();
	
		this.userName = userName;
		this.password = password;
		this.userId=userId;
		
	}
	
	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	
	@Override
	public int hashCode() {
		
		return this.userId;
	}


	@Override
	public boolean equals(Object obj) {
		
		
		if(obj==null || !(obj instanceof User) )
			return false;
		return this.userId==((User)obj).getUserId();
	}


	
	
	
	
	
	
}
