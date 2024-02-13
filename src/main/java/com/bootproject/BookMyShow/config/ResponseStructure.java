package com.bootproject.BookMyShow.config;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private String message;
	private int status;
	private T data;

}
