package com.book.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.UserDTO;
import com.book.store.entity.User;
import com.book.store.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(@Valid User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDTO updateUserByUserName(String username, User user) {
		User userExisting = userRepository.findByUserName(username);
		
		if(userExisting == null) {
			return null;
		}
		
		userExisting.setUserName(user.getUserName());
		userExisting.setUserFirstName(user.getUserFirstName());
		userExisting.setUserLastName(user.getUserLastName());
		userExisting.setUserPhoneNo(user.getUserPhoneNo());
		userExisting.setUserEmail(user.getUserEmail());
		userExisting.setUserPassword(user.getUserPassword());
		
		User savedUser = userRepository.save(userExisting);
		
		UserDTO userDTO = new UserDTO(
				savedUser.getUserName(), 
				savedUser.getUserFirstName(), 
				savedUser.getUserLastName(), 
				savedUser.getUserPhoneNo(), 
				savedUser.getUserEmail(), 
				userExisting.getRole());
		return userDTO;
	}

	

}
