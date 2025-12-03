package com.hexaware.crisel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.hexaware"})------>shoeb  is this folder scanning not happening i.e anootion use top scan i remove it work
@SpringBootApplication
public class CriselApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriselApplication.class, args);
	}

}

