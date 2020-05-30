package com.crestasom.CustomAnnotationUsingJDBC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CustomAnnotationUsingJdbcApplication {
	@Autowired
	MyService service;

	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotationUsingJdbcApplication.class, args);
	}

	@RequestMapping("insert")
	public List<Student> insert() {
		service.insertStudentsExcp();
		return service.getAll();
	}

}
