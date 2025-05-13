package com.book.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name", length = 12, nullable = false, unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "username must be of 6 to 12 length with no special characters")
	private String userName;
	
	@Column(name = "user_firstname", length = 40, nullable = false)
	@Pattern(regexp = "^[A-Z][a-z]{2,39}$",
             message = "Start with capital")
	private String userFirstName;
	
	@Column(name = "user_lastname", length = 40, nullable = false)
	@Pattern(regexp = "^[A-Z][a-z]{2,39}$",
    message = "Start with capital")
	private String userLastName;
	
	@Column(name = "user_phoneno",length = 10, nullable = false, unique = true)
	@Pattern(regexp = "^[6-9][0-9]{9}$",message = "Phone number must start with 6-9 and be exactly 10 digits")
	private String userPhoneNo;
	
	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
	@Column(name = "user_email",length = 60, nullable = false, unique = true)
	private String userEmail;
	
	@NotBlank(message = "Password is required")
	@Column(name = "password",length = 12, nullable = false)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
    	     message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit. Password must not be null.")
	private String userPassword;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;

	public User() {
		super();
	}

	public User(Integer userId, String userName, String userFirstName, String userLastName,
			 String userPhoneNo, String userEmail, String userPassword, UserRole role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPhoneNo = userPhoneNo;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", userPhoneNo=" + userPhoneNo + ", userEmail=" + userEmail
				+ ", role=" + role + "]";
	}
	
}
