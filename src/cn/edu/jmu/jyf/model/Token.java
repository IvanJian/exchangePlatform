package cn.edu.jmu.jyf.model;

import java.sql.Timestamp;

public class Token {
	private Integer userId;
	private String token;
	private String name;
	private Timestamp tokenDeadline;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTokenDeadline() {
		return tokenDeadline;
	}

	public void setTokenDeadline(Timestamp tokenDeadline) {
		this.tokenDeadline = tokenDeadline;
	}

	public Token(Integer userId, String token, String name,
			Timestamp tokenDeadline) {
		this.userId = userId;
		this.token = token;
		this.tokenDeadline = tokenDeadline;
		this.name = name;
	}

	public Token() {
		this.name = null;
		this.userId = null;
		this.token = null;
		this.tokenDeadline = null;
	}

	public boolean varify() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
