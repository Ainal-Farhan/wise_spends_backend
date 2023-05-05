package com.ainal.apps.wise_spends.security.auth;

public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private String username;
	private String password;
	private String gender;
	private Boolean flagUsernameIsEmail;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Boolean getFlagUsernameIsEmail() {
		return flagUsernameIsEmail;
	}
	public void setFlagUsernameIsEmail(Boolean flagUsernameIsEmail) {
		this.flagUsernameIsEmail = flagUsernameIsEmail;
	}
}
