package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.Admin;
import com.bootproject.BookMyShow.repo.AdminRepo;

@Repository
public class AdminDao {
	
	@Autowired
	AdminRepo repo;
	
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}
	
	public Admin findAdmin(int aId)
	{
		Optional<Admin> opAdmin = repo.findById(aId);
		if(opAdmin.isPresent())
		{
			return opAdmin.get();
		}
		return null;
	}
	
	public Admin deleteAdmin(int aId)
	{
		Admin admin =findAdmin(aId);
		repo.delete(admin);
		
		return admin;
	}
	
	public Admin updateAdmin(Admin admin , int  aId)
	{
		Admin exadmin= findAdmin(aId);
		
		if(exadmin!=null)
		{
			admin.setAId(aId);
			return repo.save(admin);
		}
		return null;
	}
	
	public List<Admin> findAllAdmin()
	{
		List<Admin> admin = repo.findAll();
		return admin;
	}
}
