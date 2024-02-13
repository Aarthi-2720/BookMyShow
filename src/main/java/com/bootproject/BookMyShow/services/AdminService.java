package com.bootproject.BookMyShow.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bootproject.BookMyShow.config.ResponseStructure;
import com.bootproject.BookMyShow.dao.AdminDao;
import com.bootproject.BookMyShow.dto.AdminDto;
import com.bootproject.BookMyShow.entity.Admin;

@Service
public class AdminService {
	
	@Autowired
	AdminDao dao;
	
	AdminDto dto = new AdminDto();
	ModelMapper mapper = new ModelMapper();
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
		
		ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
		
		mapper.map(dao.saveAdmin(admin), dto);
		
		structure.setMessage(admin.getAName()+"Admin has Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}

}
