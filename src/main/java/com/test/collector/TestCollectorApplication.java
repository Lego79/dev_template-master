package com.test.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestCollectorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TestCollectorApplication.class, args);
	}
	
}
