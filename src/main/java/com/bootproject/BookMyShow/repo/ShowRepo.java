package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Show;

public interface ShowRepo extends JpaRepository<Show, Integer>{

}
