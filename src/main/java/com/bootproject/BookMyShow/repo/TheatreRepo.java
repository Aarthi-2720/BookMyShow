package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer> {

}
