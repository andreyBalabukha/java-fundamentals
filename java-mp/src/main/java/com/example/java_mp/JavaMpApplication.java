package com.example.java_mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.java_mp")
@EnableJpaRepositories(basePackages = "com.example.java_mp.repository")
public class JavaMpApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMpApplication.class, args);
	}

}
