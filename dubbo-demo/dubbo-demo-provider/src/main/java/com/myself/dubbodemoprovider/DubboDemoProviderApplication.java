package com.myself.dubbodemoprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class DubboDemoProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboDemoProviderApplication.class, args);
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
