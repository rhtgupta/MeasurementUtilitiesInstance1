package poc.microservices.instance.one.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "poc.microservices.instance.one.controller" })
public class InstanceOneServer {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "measurement-utilities-server");
		SpringApplication.run(InstanceOneServer.class, args);
	}

}
