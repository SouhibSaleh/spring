package com.example.MyProject.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class userLogin {
	private String UserName;
	private String PassWord;
	private final loginSession logSes;
	@Autowired
	public userLogin(loginSession logSes) {
		super();
		this.logSes = logSes;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public loginSession getLogSes() {
		return logSes;
	}
	@Override
	public String toString() {
		return "userLogin [UserName=" + UserName + ", PassWord=" + PassWord + ", logSes=" + logSes + "]";
	}
	
	
	

}
