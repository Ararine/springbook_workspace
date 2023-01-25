package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot01DemoApplication {

	//환경설정 역할이므로 지우면 안된다.
	public static void main(String[] args) {
		SpringApplication.run(Springboot01DemoApplication.class, args);
	}

}
