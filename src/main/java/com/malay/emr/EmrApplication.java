package com.malay.emr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.malay.emr"})
public class EmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmrApplication.class, args);
	}

}
