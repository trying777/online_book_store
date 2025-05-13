package com.book.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.dto.UserDTO;
import com.book.store.entity.User;
import com.book.store.repository.UserRepository;
import com.book.store.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	http://localhost:8080/registerUser
	@PostMapping("/registerUser")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user){
		User userExists = userRepository.findByUserName(user.getUserName());
		if(userExists != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDTO("User already exist", null, null, null, null, null));
		}
		
		User createdUser = userService.registerUser(user);
		UserDTO userDTO = new UserDTO(
				createdUser.getUserName(), 
				createdUser.getUserFirstName(),
				createdUser.getUserLastName(), 
				createdUser.getUserPhoneNo(), 
				createdUser.getUserEmail(), 
				createdUser.getRole());
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
	}
	
//	http://localhost:8080/updateUserByUserName/{uname}
	@PutMapping("/updateUserByUserName/{uname}")
	public ResponseEntity<UserDTO> updateUserByUserName(@PathVariable("uname") String username, @RequestBody User user){
		User userExists = userRepository.findByUserName(username);
		if(userExists == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		UserDTO userDTO = userService.updateUserByUserName(username, user);
		return ResponseEntity.ok(userDTO);
	}
	
//	http://localhost:8080/deleteUserByUserName/{uname}
	@DeleteMapping("/deleteUserByUserName/{uname}")
	public ResponseEntity<String> deleteUserByUserName(@PathVariable("uname") String username) {
		User userExisting = userRepository.findByUserName(username);
		if(userExisting == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with username: "+username);
		}
		
		userRepository.delete(userExisting);
		return ResponseEntity.status(HttpStatus.OK).body("User with username: " +username+ " deleted.");
	}
	
}
