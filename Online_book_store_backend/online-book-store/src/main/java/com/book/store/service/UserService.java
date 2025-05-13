package com.book.store.service;

import com.book.store.dto.UserDTO;
import com.book.store.entity.User;

import jakarta.validation.Valid;

public interface UserService {

	public User registerUser(@Valid User user);

	public UserDTO updateUserByUserName(String username, User user);

}
