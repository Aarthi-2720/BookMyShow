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
import com.bootproject.BookMyShow.dto.AdminDto;
import com.bootproject.BookMyShow.entity.Admin;
import com.bootproject.BookMyShow.services.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin){
		return service.saveAdmin(admin);
	}
	
//	@GetMapping("login")
//	public ResponseEntity<ResponseStructure<AdminDto>> adminLogin(@RequestParam String email, @RequestParam String password){
//		return service.AdminLogin(email, password);
//	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@RequestBody Admin admin, @RequestParam int aId){
		return service.findAdmin(aId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int aId){
		return service.deleteAdmin(aId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAmdin(@RequestParam String email, @RequestParam String password, @RequestBody Admin admin, @RequestParam int aId){
		return service.updateAdmin(email, password, admin, aId);
	}
	
	@GetMapping("adminlist")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmin(){
		return service.findAllAdmin();
	}
	
	@PutMapping("assignUser")
	public ResponseEntity<ResponseStructure<AdminDto>> assignUserToAdmin(@RequestParam String email, @RequestParam String password){
		return service.assignUserToAdmin(email, password);
	}
	
	@PutMapping("assignTheatre")
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatreToAdmin(@RequestParam String email, @RequestParam String password){
		return service.assignTheatreToAdmin(email, password);
	}
	

}
