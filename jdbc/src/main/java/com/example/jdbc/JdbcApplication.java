package com.example.jdbc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication implements ApplicationRunner {

	private final UserService userService;

	public JdbcApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	public void run() {
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userService.findPopularUsers().forEach(System.out::println);
	}

}
