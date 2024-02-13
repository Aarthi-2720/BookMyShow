package com.bootproject.BookMyShow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootproject.BookMyShow.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
