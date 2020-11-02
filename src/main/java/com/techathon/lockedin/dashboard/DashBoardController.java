package com.techathon.lockedin.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techathon.lockedin.email.EmailRequestDto;
import com.techathon.lockedin.email.MailService;
import com.techathon.lockedin.githubhook.GitHubController;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

@RestController()
@RequestMapping("dashboard")
public class DashBoardController {
	private final static Logger logger = LoggerFactory.getLogger(DashBoardController.class);

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemp;


	@Autowired
	private MailService mailService;
	
//	
	 
//	@Scheduled(cron="0 0 0 1 */3 *")
	@Scheduled(cron="0 0/1 * * * ?")
	public void schedularJob() throws ParseException, IOException {
	    // Something
 
		// Get All users
		List<UserDetails> userList = userRepo.findTop2ByOrderByTotalReviewerPointsDesc();
		
		// Select Top 50 Reviwers 
		//Send Email
		for(UserDetails user:userList) {
			String jsonResponse = restTemp.getForObject("https://api.github.com/users/"+user.getGitHubUserName(), String.class);
			JSONParser parser = new JSONParser(jsonResponse); 
			EmailRequestDto dto = new EmailRequestDto();
			String name= (String) parser.parseObject().get("name");
//			String emailId= (String) parser.parseObject().get("userEmailId");
//			EmailRequestDto dto = new EmailRequestDto();
			dto.setFrom("mohit.bansal@gep.com");
			dto.setTo(user.getUserEmailId());
			logger.info("EMAIL TO " + user.getUserEmailId());
			dto.setSubject("Kudos For Top Reviewer");
			dto.setName(name);
			logger.info("Name EMAIL TO " + name);
			String response = mailService.sendMail(dto, null);
//			System.out.println(response);
		}
		
		
		//reset developers and reviwers point
	}
	
	  @GetMapping("Authenticate")
	    public List<UserDetails> getAuthenticateData(@RequestParam(defaultValue = "userName", required = true)String userName){
		  UserDetails userFromDb  =  userRepo.findBygitHubUserName(userName).orElse(null);
		  List<UserDetails> lis = new ArrayList<UserDetails>();
		  if(userFromDb != null && !userFromDb.getIsAdmin()) {
			  lis.add(userFromDb);
			  return lis;
		  }else if(userFromDb != null && userFromDb.getIsAdmin()) {
			  return userRepo.findAll();
		  }else  {
			  return null;
		  }
		  
	       
	    }
	    
	    
	    
	    
	    @GetMapping("Email")
	    public boolean getsendEmailData(){
	        
	        return true;
	    }
	    
	    @GetMapping("showtop")
	    public List<UserDetails> getTopFifty(){
	    	return userRepo.findTop2ByOrderByTotalReviewerPointsDesc();
	    }
	    
	    @GetMapping("getuserdata")
	    public List<UserDetails> getAllUserData(){
	    	return userRepo.findAll();
	    }
	
}
