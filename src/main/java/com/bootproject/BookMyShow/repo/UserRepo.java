package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
