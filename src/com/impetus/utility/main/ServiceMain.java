package com.impetus.utility.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.impetus.utility.controller",
		"com.impetus.utility.serviceImpl" })
public class ServiceMain {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "measurement-utilities-server");
		SpringApplication.run(ServiceMain.class, args);
	}

}
