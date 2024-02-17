package com.bootproject.BookMyShow.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bootproject.BookMyShow.Exception.BookingNotFound;
import com.bootproject.BookMyShow.Exception.InvalidEntry;
import com.bootproject.BookMyShow.Exception.UserNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.BookingDao;
import com.bootproject.BookMyShow.dao.UserDao;
import com.bootproject.BookMyShow.dto.UserDto;
import com.bootproject.BookMyShow.entity.Booking;
import com.bootproject.BookMyShow.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	UserDto dto = new UserDto();
	ModelMapper mapper = new ModelMapper();
	
	BookingDao bdao = new BookingDao();
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user) {
		
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		
		mapper.map(dao.saveUser(user), dto);
		
		structure.setMessage(user.getUName()+" User has Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		
		return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int uId){
		
		User exUser = dao.findUser(uId);
		
		if(exUser!=null) {
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			
			mapper.map(exUser, dto);
			
			structure.setMessage("User found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(String email, String password, User user, int uId){
		
		User exUser = dao.userLogin(email, password);
		
		if(exUser != null) {
			User upUser = dao.updateUser(exUser, uId);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			if(upUser != null) {
				mapper.map(upUser, dto);
				
				structure.setMessage("User updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
			
				return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
			}
			throw new UserNotFound("User not found with given Id");
		}
		throw new InvalidEntry("User Login failed");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int uId){
		
		User user = dao.findUser(uId);
		
		if(user != null) {
			if(dao.userLogin(user.getUEmail(), user.getUPassword()) != null) {
				User delUser = dao.deleteUser(uId);
				ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
				
				mapper.map(delUser, dto);
				
				structure.setMessage("User deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
			}
			throw new InvalidEntry("User Login failed");
		}
		throw new UserNotFound("User not found with given Id");
	}
	
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		
		List<User> userList = dao.findAllUser();
		List<UserDto> userdtoList = new ArrayList<UserDto>();
		
		if(! userList.isEmpty()) {
			for(User a : userList) {
				mapper.map(a, userdtoList);
				userdtoList.add(dto);
			}
			ResponseStructure<List<UserDto>> structure = new ResponseStructure<List<UserDto>>();
			structure.setMessage("All Users are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(userdtoList);
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound("Users are not found");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> assignBookingToUser(String email, String password){
		
		User user = dao.userLogin(email, password);
		
		if(user!=null) {
			List<Booking> booking = bdao.findAllBooking();
			if(!booking.isEmpty()) {
				user.setUBooking(booking);
				User upUser = dao.updateUser(user, user.getUId());
				mapper.map(upUser, dto);
				
				ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
				structure.setMessage("Booking assigned to User");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
			}
			throw new BookingNotFound("Booking List is empty");
		}
		throw new UserNotFound("User Login failed");
	}
	
	
}
	
