package com.linklife.domain.model;

public class ChangePWDModel {

	private String Password;
	private String NewPassword;
	private String ConfirmPassword;


	public String getPassword() {

		return Password;
	}


	public void setPassword( String password ) {

		Password = password;
	}


	public String getNewPassword() {

		return NewPassword;
	}


	public void setNewPassword( String newPassword ) {

		NewPassword = newPassword;
	}


	public String getConfirmPassword() {

		return ConfirmPassword;
	}


	public void setConfirmPassword( String confirmPassword ) {

		ConfirmPassword = confirmPassword;
	}

}
