package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

}
