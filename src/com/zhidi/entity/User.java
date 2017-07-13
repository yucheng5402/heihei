package com.zhidi.entity;

import java.util.Map;

public class User {
	private String name;
	private String password;
	private Map<String, String> hobby;

	public User() {
		super();
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getHobby() {
		return hobby;
	}

	public void setHobby(Map<String, String> hobby) {
		this.hobby = hobby;
	}
	
}
