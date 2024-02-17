package com.bootproject.BookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bootproject.BookMyShow.entity.User;
import com.bootproject.BookMyShow.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User findUser(int uId)
	{
		Optional<User> opuser = repo.findById(uId);
		if(opuser.isPresent())
		{
			return opuser.get();
		}
		return null;
	}
	
	public User deleteUser(int uId)
	{
		User user =findUser(uId);
		repo.delete(user);
		
		return user;
	}
	
	public User updateUser(User user , int  uId)
	{
		User exuser= findUser(uId);
		
		if(exuser!=null)
		{
			user.setUId(uId);
			return repo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser()
	{
		List<User> user = repo.findAll();
		return user;
	}
	
	public User userLogin(String email, String password) {
		return repo.userLogin(email, password);
	}
}
