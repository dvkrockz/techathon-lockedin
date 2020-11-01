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

	@Autowired
	private UserRepository userRepo;


	@Autowired
	private MailService mailService;
	
	@Scheduled(cron="0 0 1 */3 *")
	public void schedularJob() {
	    // Something
		
		// Get All users
		List<UserDetails> userList = userRepo.findTop50ByOrderByTotalReviewerPointsDesc();
		
		// Select Top 50 Reviwers 
		//Send Email
		String response = mailService.sendMail(null, null);
		
		//reset developers and reviwers point
	}
	
	
}
