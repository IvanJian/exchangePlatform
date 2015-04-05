package cn.edu.jmu.jyf.model;

import java.sql.Timestamp;

public class Token {
	private Integer userId;
	private String token;
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
	
	public Token(Integer userId,String token,Timestamp tokenDeadline){
		this.userId=userId;
		this.token=token;
		this.tokenDeadline=tokenDeadline;
	}
	
	public Token(){
		this.userId=null;
		this.token=null;
		this.tokenDeadline=null;
	}
	
	public boolean varify() {
		return false;
	}
	
}
