package com.bootproject.BookMyShow.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

public class Config implements WebMvcConfigurer{
	
	@Bean
	public OpenAPI swaggerDocOpenApi() {
		
		Server deserver = new Server();
		deserver.setUrl("localhost:8080");
		deserver.setDescription("Development Server");
		
		Server testserver = new Server();
		testserver.setUrl("localhost:8081");
		testserver.setDescription("TestServer");
		
		
		Contact con = new Contact();
		con.setEmail("aarthia205@gmail.com");
		con.setName("Aarthi");
		con.setUrl("../https://github.com");
		
		License li = new License();
		li.setName("License");
		li.setUrl("license providers");
		
		Info info = new Info();
		info.setContact(con);
		info.setLicense(li);
		info.setDescription("BookMyShow");
		info.setTermsOfService("https://in.search.yahoo.com/search?fr=mcafee&type=E211IN714G0&p=terms+and+conditions");
		info.setTitle("Book My Show");
		info.setVersion("2.0");
		
		OpenAPI op = new OpenAPI();
		op.info(info);
		op.servers(Arrays.asList(deserver, testserver));
		return op;
		
	}

}
