package com.book.store.dto;

import com.book.store.entity.UserRole;

public class UserDTO {
	
	private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNo;
    private String userEmail;
    private UserRole role;
    
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String userName, String userFirstName, String userLastName, String userPhoneNo, String userEmail,
			UserRole role) {
		super();
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPhoneNo = userPhoneNo;
		this.userEmail = userEmail;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userPhoneNo=" + userPhoneNo + ", userEmail=" + userEmail + ", role=" + role + "]";
	}

}
