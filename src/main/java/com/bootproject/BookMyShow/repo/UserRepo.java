package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootproject.BookMyShow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where uName=?1 and uPassword=?2")
	public User userLogin(String email, String password); 

}
