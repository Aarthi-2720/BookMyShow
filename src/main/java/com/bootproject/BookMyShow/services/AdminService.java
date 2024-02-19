package com.bootproject.BookMyShow.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bootproject.BookMyShow.Exception.AdminNotFound;
import com.bootproject.BookMyShow.Exception.InvalidEntry;
import com.bootproject.BookMyShow.Exception.TheatreNotFound;
import com.bootproject.BookMyShow.Exception.UserNotFound;
import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.AdminDao;
import com.bootproject.BookMyShow.dao.TheatreDao;
import com.bootproject.BookMyShow.dao.UserDao;
import com.bootproject.BookMyShow.dto.AdminDto;
import com.bootproject.BookMyShow.entity.Admin;
import com.bootproject.BookMyShow.entity.Theatre;
import com.bootproject.BookMyShow.entity.User;

@Service
public class AdminService {
	
	@Autowired
	AdminDao dao;
	
	AdminDto dto = new AdminDto();
	ModelMapper mapper = new ModelMapper();
	
	@Autowired
	UserDao udao;
	
	@Autowired
	TheatreDao tdao;
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
		
		ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
		
		mapper.map(dao.saveAdmin(admin), dto);
		
		structure.setMessage(admin.getAName()+"Admin has Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}

//	public ResponseEntity<ResponseStructure<AdminDto>> AdminLogin(String email, String password){
//		
//		List<Admin> aList = dao.findAllAdmin();
//		
//		if(aList.isEmpty()) {
//			for(Admin admin : aList) {
//				if(admin.getAEmail().equals(email) && admin.getAPassword().equals(password)) {
//				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
//				
//				mapper.map(admin, dto);
//				
//				structure.setMessage("Login Successfull");
//				structure.setStatus(HttpStatus.ACCEPTED.value());
//				structure.setData(dto);
//				
//				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.ACCEPTED);
//				}
//				return null;
//			}
//			return null;
//		}
//		return null;
//	}
//	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int aId){
		
		Admin admin = dao.findAdmin(aId);
		
		if(admin!=null) {
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			
			mapper.map(admin, dto);
			
			structure.setMessage("Admin found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found with given id") ;
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(String adminEmail, String adminPassword, Admin admin, int aId){
		
		Admin exadmin = dao.adminLogin(adminEmail, adminPassword);
		
		if(exadmin != null) {
				Admin upadmin = dao.updateAdmin(admin, aId);
				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
				if(admin!=null) {
				mapper.map(upadmin, dto);
				
				structure.setMessage("Admin updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
			}
			throw new AdminNotFound("Admin not found with given id") ;
		}
		throw new InvalidEntry("Admin Login failed");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int aId){
		
		Admin admin = dao.findAdmin(aId);
		
		if(admin!=null) {
			if(dao.adminLogin(admin.getAEmail(), admin.getAPassword()) !=null) {
				Admin delAdmin = dao.deleteAdmin(aId);
				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
				
				mapper.map(delAdmin, dto);
				
				structure.setMessage("Admin deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
			}
			throw new InvalidEntry("Admin Login failed");
		}
		throw new AdminNotFound("Admin not found with given id") ;
	}
	
	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmin(){
		
		List<Admin> adminList = dao.findAllAdmin();
		List<AdminDto> admindtoList = new ArrayList<AdminDto>();
		
		if(! adminList.isEmpty()) {
			for(Admin a : adminList) {
				mapper.map(a, admindtoList);
				admindtoList.add(dto);
			}
			ResponseStructure<List<AdminDto>> structure = new ResponseStructure<List<AdminDto>>();
			structure.setMessage("All Admins are Listed");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(admindtoList);
			return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admins are not found");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> assignUserToAdmin( String email, String password){
		
		Admin admin = dao.adminLogin(email, password);
		
		if(admin!=null) {
			List<User> user = udao.findAllUser();
			if(!user.isEmpty()) {
				admin.setAUser(user);
				Admin upAdmin = dao.updateAdmin(admin, admin.getAId());
				mapper.map(upAdmin, dto);
				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
					
				structure.setMessage("User assign to the Admin");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
					
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
			}
			throw new UserNotFound("User List is empty");
		}
		throw new InvalidEntry("Admin Login failed");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> deleteUserFromAdmin(String email, String password, int uId){
		
		Admin admin = dao.adminLogin(email, password);
		if(admin !=null) {
			List<User> userlist = admin.getAUser();
			if(!userlist.isEmpty()) {
				for(User u : userlist ) {
					int id = u.getUId();
					if(id==uId) {
						userlist.remove(uId);
						udao.deleteUser(uId);
						admin.setAUser(userlist);
						Admin upadmin = dao.updateAdmin(admin, admin.getAId());
						mapper.map(upadmin, dto);
						ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
						structure.setMessage("User successfully removed from Admin");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(dto);
						
						return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
					}
				}
				throw new UserNotFound("User not found with given id");
			}
			throw new UserNotFound("User list is empty");
		}
		throw new InvalidEntry("Admin Login failed");	
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatreToAdmin(String email, String password){
		
		Admin admin = dao.adminLogin(email, password);
		
		if(admin!=null) {
			List<Theatre> theatre = tdao.findAllTheatre();
			if(!theatre.isEmpty()) {
				admin.setATheatre(theatre);
				Admin upadmin = dao.updateAdmin(admin, admin.getAId());
				mapper.map(upadmin, dto);
				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
				structure.setMessage("Theatre assigned to admin");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre list is empty");
		}
		throw new InvalidEntry("Admin Login failed");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> deleteTheatreFromAdmin(String email, String password, int tId){
		
		Admin admin = dao.adminLogin(email, password);
		if(admin !=null) {
			List<Theatre> theatrelist = admin.getATheatre();
			if(!theatrelist.isEmpty()) {
				for(Theatre t : theatrelist ) {
					int id = t.getTId();
					if(id==tId) {
						theatrelist.remove(tId);
						tdao.deleteTheatre(tId);
						admin.setATheatre(theatrelist);
						Admin upadmin = dao.updateAdmin(admin, admin.getAId());
						mapper.map(upadmin, dto);
						ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
						structure.setMessage("Theatre successfully removed from Admin");
						structure.setStatus(HttpStatus.OK.value());
						structure.setData(dto);
						
						return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
					}
				}
				throw new TheatreNotFound("Theatre does not found with given id");
			}
			throw new TheatreNotFound("Theatre list is empty");
		}
		throw new InvalidEntry("Admin Login failed");	
	}	
	
}
