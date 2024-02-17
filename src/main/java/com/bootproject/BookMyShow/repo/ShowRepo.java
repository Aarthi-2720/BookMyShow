package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Shows;

public interface ShowRepo extends JpaRepository<Shows, Integer>{

}
