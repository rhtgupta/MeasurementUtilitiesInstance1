package com.impetus.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.impetus.service.controller", "com.impetus.service.service.impl" })
public class ServiceMain {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "measurement-utilities-server");
		SpringApplication.run(ServiceMain.class, args);
	}

}
