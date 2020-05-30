package com.crestasom.CustomAnnotationUsingJDBC;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crestasom.CustomAnnotationUsingJDBC.annotation.MyTransactional;
import com.crestasom.CustomAnnotationUsingJDBC.service.MyService;

@SpringBootApplication
@RestController
public class CustomAnnotationUsingJdbcApplication implements CommandLineRunner {
	@Autowired
	MyService service;
	private static final Logger LOG = LoggerFactory.getLogger(CustomAnnotationUsingJdbcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotationUsingJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@RequestMapping("insert")
	public List<Student> insert() {
		service.insertStudents();
		return service.getAll();
	}

}
