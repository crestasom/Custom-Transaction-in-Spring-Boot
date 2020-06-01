package com.crestasom.CustomAnnotationUsingJDBC;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CustomAnnotationUsingJdbcApplication {
	@Autowired
	MyService service;
	private static final Logger LOG = LoggerFactory.getLogger(CustomAnnotationUsingJdbcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotationUsingJdbcApplication.class, args);
	}

	@RequestMapping("insert")
	public void insert() {
		// TODO Auto-generated method stub
		//invoking first annotated method
		service.insertStudents();
		service.getAll().forEach(s -> {
			LOG.info(s.toString());
		});
		//invoking second annotated method that has sql exception.
		service.insertStudentsExcp();
		//no changes will be reflected in db. will return same value as above
		service.getAll().forEach(s -> {
			LOG.info(s.toString());
		});
	}
}
