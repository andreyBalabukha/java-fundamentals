package com.example.CommandLineRunnerApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineRunnerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CommandLineRunnerApplication.class, args);
		System.out.println("Hello World");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World from CommandLineRunner");
	}

}
