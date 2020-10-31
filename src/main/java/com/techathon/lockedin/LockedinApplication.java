package com.techathon.lockedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration 
@SpringBootApplication
//@ComponentScan( "com.techathon.lockedin.githubhook")
public class LockedinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockedinApplication.class, args);
	}

}
