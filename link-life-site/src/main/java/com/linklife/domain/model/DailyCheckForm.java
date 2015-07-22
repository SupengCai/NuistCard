package com.linklife.domain.model;

public class DailyCheckForm {

	private String account;

	private String name;

	private String password;

	private Integer state;

	private boolean rememberme;


	public String getAccount() {

		return account;
	}


	public void setAccount( String account ) {

		this.account = account;
	}


	public String getName() {

		return name;
	}


	public void setName( String name ) {

		this.name = name;
	}


	public String getPassword() {

		return password;
	}


	public void setPassword( String password ) {

		this.password = password;
	}


	public Integer getState() {

		return state;
	}


	public void setState( Integer state ) {

		this.state = state;
	}


	public boolean isRememberme() {

		return rememberme;
	}


	public void setRememberme( boolean rememberme ) {

		this.rememberme = rememberme;
	}

}