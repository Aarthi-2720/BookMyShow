package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Seats;

public interface SeatsRepo extends JpaRepository<Seats, Integer>{

}
