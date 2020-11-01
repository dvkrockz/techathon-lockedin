package com.techathon.lockedin.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

import net.minidev.json.JSONObject;

@RestController("dashboard")
public class DashBoardController {

	@Autowired
	private UserRepository userRepo;

	
	  @GetMapping("Authenticate")
	    public JSONObject getAuthenticateData(String userName){

	 

	          JSONObject obj=new JSONObject();  
	        if(userName == "mohit") {
	            
	            UserDetails detail1 = new UserDetails();
	            detail1.setGitHubUserName("mohit-gep");
	            detail1.setId((long) 1);
	            detail1.setIsAdmin(true);
	            detail1.setTotalReviewerPoints(200);

	 

	            UserDetails detail2 = new UserDetails();
	            detail2.setGitHubUserName("mohit-gep");
	            detail2.setId((long) 2);
	            detail2.setIsAdmin(false);
	            detail2.setTotalReviewerPoints(100);

	 

	             UserDetails detail3 = new UserDetails();
	            detail3.setGitHubUserName("mohit-gep");
	            detail3.setId((long) 3);
	            detail3.setIsAdmin(false);
	            detail3.setTotalReviewerPoints(150);

	 

	            UserDetails detail4 = new UserDetails();
	            detail4.setGitHubUserName("mohit-gep");
	            detail4.setId((long) 4);
	            detail4.setIsAdmin(false);
	            detail4.setTotalReviewerPoints(20);
	            
	            List<UserDetails> details = null;
	            details.add(detail1);
	            details.add(detail2);
	            details.add(detail3);
	            details.add(detail4);
	               
	          obj.put("isAdmin","true");    
	          obj.put("data",details);
	        }
	        else {
	             UserDetails detail1 = new UserDetails();
	                detail1.setGitHubUserName("mohit-gep");
	                detail1.setId((long) 1);
	                detail1.setIsAdmin(false);
	                detail1.setTotalReviewerPoints(200);

	 

	                
	                List<UserDetails> details = null;
	                details.add(detail1);
	                
	                  
	              obj.put("isAdmin","false");    
	              obj.put("data",details);
	        }
	        return obj;
	    }
	    
	    
	    
	    
	    @GetMapping("Email")
	    public boolean getsendEmailData(){
	        
	        return true;
	    }
	    
	    @GetMapping("showtop")
	    public List<UserDetails> getTopFifty(){
	    	return userRepo.findTop50ByOrderByTotalReviewerPointsDesc();
	    }
	
}
