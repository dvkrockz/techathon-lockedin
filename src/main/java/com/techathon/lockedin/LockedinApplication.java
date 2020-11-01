package com.techathon.lockedin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

@EnableAutoConfiguration 
@SpringBootApplication
@EnableScheduling
//@ComponentScan( "com.techathon.lockedin.githubhook")
public class LockedinApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockedinApplication.class, args);
	}

	
	
	
}
