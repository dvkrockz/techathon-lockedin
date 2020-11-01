package com.techathon.lockedin.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techathon.lockedin.email.EmailRequestDto;
import com.techathon.lockedin.email.MailService;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

@RestController()
@RequestMapping("dashboard")
public class DashBoardController {

	@Autowired
	private UserRepository userRepo;


	@Autowired
	private MailService mailService;
	
	@Autowired
	private RestTemplate restTemp;
//	
//	@Scheduled(cron="0 0 0 1 */3 *")
	@Scheduled(cron="0 0/1 * * * ?")
	public void schedularJob() throws ParseException {
	    // Something

		// Get All users
		List<UserDetails> userList = userRepo.findTop50ByOrderByTotalReviewerPointsDesc();
		
		// Select Top 50 Reviwers 
		//Send Email
		for(UserDetails user:userList) {
			String jsonResponse = restTemp.getForObject("https://api.github.com/users/"+user.getGitHubUserName(), String.class);
			JSONParser parser = new JSONParser(jsonResponse); 
			String name= (String) parser.parseObject().get("name");
			String emailId= (String) parser.parseObject().get("email");
			System.out.println(jsonResponse);
			EmailRequestDto dto = new EmailRequestDto();
			dto.setFrom("kudos@gep.com");
			dto.setTo(emailId != null? emailId:"mohit.bansal@gep.com");
			dto.setSubject("Kudos For Top Reviewer");
			dto.setName(name);
			String response = mailService.sendMail(dto, null);
		}
		
		
		//reset developers and reviwers point
	}
	
	  @GetMapping("Authenticate")
	    public List<UserDetails> getAuthenticateData(@RequestParam("userName")String userName){
		  UserDetails userFromDb  =  userRepo.findByGitHubUserName(userName).get();
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
	    	return userRepo.findTop50ByOrderByTotalReviewerPointsDesc();
	    }
	    
	    @GetMapping("getuserdata")
	    public List<UserDetails> getAllUserData(){
	    	return userRepo.findAll();
	    }
	
}
