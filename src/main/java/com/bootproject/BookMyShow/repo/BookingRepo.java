package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
