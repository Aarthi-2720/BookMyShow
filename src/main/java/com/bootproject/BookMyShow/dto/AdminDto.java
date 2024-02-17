package com.bootproject.BookMyShow.dto;

import java.util.List;

import com.bootproject.BookMyShow.entity.Theatre;
import com.bootproject.BookMyShow.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {

	private int aId;
	private String aName;
	private long aContact;
	private String aEmail;
	
	private List<User> user;
	private List<Theatre> theatre;
}
