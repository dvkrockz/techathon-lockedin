package com.techathon.lockedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration 
@SpringBootApplication
@EnableScheduling
//@ComponentScan( "com.techathon.lockedin.githubhook")
public class LockedinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockedinApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	
}
