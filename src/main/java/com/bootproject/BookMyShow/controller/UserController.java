package com.bootproject.BookMyShow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dto.UserDto;
import com.bootproject.BookMyShow.entity.User;
import com.bootproject.BookMyShow.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam int uId){
		return service.findUser(uId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam int uId){
		return service.deleteUser(uId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestParam String email, @ RequestParam String password, @RequestBody User user, @RequestParam int uId){
		return service.updateUser(email, password, user, uId);
	}
	
	@GetMapping("userlist")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		return service.findAllUser();
	}
	
	@PutMapping("assignBooking")
	public ResponseEntity<ResponseStructure<UserDto>> assignBookingToUser(@RequestParam String email, @RequestParam String password){
		return service.assignBookingToUser(email, password);
	}
	
	@DeleteMapping("removebooking")
	public ResponseEntity<ResponseStructure<UserDto>> deleteBookingFromUser(@RequestParam String email, @RequestParam String password, int bId){
		return service.deleteBookingFromUser(email, password, bId);
	}
}
