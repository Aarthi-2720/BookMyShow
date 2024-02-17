package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
