package com.techathon.lockedin.executors.github;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

public class PRReviewRequestedAction <T> extends GithubActionExecutors<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PRReviewRequestedAction.class);
private UserRepository userRep;
	public PRReviewRequestedAction(UserRepository userRepo) {
		super(userRepo);
this.userRep = userRepo;
}
	public static final String regex = "[x]"; 
	
	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
		LOGGER.info("In Review Requested");
		Gson gs = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
	 		    .create();
	 	PrOpenedModel classObject = gs.fromJson(jsonObject, PrOpenedModel.class);
		 
	 	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOGGER.error("Thread Interrupted");
		}
	 	UserDetails userFromDb = checkUserExist(classObject.getPullRequest().getUser().getLogin());
	 	if(userFromDb.getPrOpenModelList() != null && !userFromDb.getPrOpenModelList().isEmpty()) {
	 	for(PrOpenedModel pr: userFromDb.getPrOpenModelList()) {
	 	if(pr.getPullRequest()!= null && classObject.getPullRequest()!= null
	 			&& pr.getPullRequest().getUrl().equalsIgnoreCase(classObject.getPullRequest().getUrl()))	{
	 		if(pr.getPullRequest().getRequestedReviewers() == null ) {
	 			
	 			pr.getPullRequest().setRequestedReviewers(classObject.getPullRequest().getRequestedReviewers());
	 			
	 		}else {
	 			List<RequestedReviewers> newPrList = new ArrayList<>();
	 			for(RequestedReviewers rev: pr.getPullRequest().getRequestedReviewers()) {
	 				
	 				boolean didMatched =  false;
	 				for(RequestedReviewers clasRev: classObject.getPullRequest().getRequestedReviewers()) {
	 					if(clasRev.getLogin().equalsIgnoreCase(rev.getLogin())){
	 						didMatched =  true;
	 						break;
	 					}
	 			}
	 				if(!didMatched) {
	 					newPrList.add(rev);
	 				}
	 		}
	 			pr.getPullRequest().getRequestedReviewers().addAll(newPrList);
	 	}
	 	
	 	}
	 	
	 	
	 	}
	 	saveNewUser(userFromDb);
	 	
	 	
		
		
	 	}
	 	return actionType;
	}

}
