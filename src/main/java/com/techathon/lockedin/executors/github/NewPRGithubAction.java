package com.techathon.lockedin.executors.github;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

public class NewPRGithubAction<T> extends GithubActionExecutors<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewPRGithubAction.class);
	
	
	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
	 	ActionResponse<T> actionResponse = getNewActionType();
	 	Gson gs = new Gson();
	 	PrOpenedModel classObject = gs.fromJson(jsonObject, PrOpenedModel.class);
	 	UserDetails user = fromModeltoUser(classObject);
	 	UserDetails userDetailsFromDb = checkUserExist(user.getGitHubUserName());
	 	if(null != userDetailsFromDb) {
	 		 LOGGER.info("User Exist with userName %s " , userDetailsFromDb.getGitHubUserName().toString());
	 		 // If User Existed Check if the PR Exist
	 		 
	 		 //if PR Exist 
	 		 
	 		 	//Check the comments, title, body , or change in reviewers and save accordingly
	 		 
	 		 //PR DoesNot Exist
	 		 //Save PR in the List
	 		
	 	}else {
	 		List<PrOpenedModel> prOpenModel = new ArrayList<>();
	 		prOpenModel.add(classObject);
	 		user.setPrOpenModelList(prOpenModel);
	 		userDetailsFromDb = saveNewUser(user);
	 		//Check if there are Any reviewers
	 		//If exist Check for there User Details and Save
	 		if( classObject.getPullRequest().getRequestedReviewers().size() > 0) {
	 		for(RequestedReviewers rev: classObject.getPullRequest().getRequestedReviewers()) {
	 			UserDetails reviewerFromDb = checkUserExist(rev.getLogin());
	 			if(null == reviewerFromDb) {
	 				UserDetails reviewer = fromReviewertoUser(rev);
	 				saveNewUser(reviewer);
	 			}
	 		  }
	 		} 

	 		
	 	}
	    LOGGER.debug(actionResponse.toString());
	    actionResponse.setPrPullNumber(classObject.getPullRequest().getHtmlUrl());
return actionResponse;
}
	
	
	
	
 




	public NewPRGithubAction(UserRepository userRepo) {
		super(userRepo);
		// TODO Auto-generated constructor stub
	}
 
	public UserDetails fromModeltoUser(PrOpenedModel openModel) {
		UserDetails user = new UserDetails();
		user.setGitHubUrl(openModel.getPullRequest().getUser().getHtmlUrl());
		return user; 	
	}

	
	public UserDetails fromReviewertoUser(RequestedReviewers openModel) {
		UserDetails user = new UserDetails();
		user.setGitHubUserName(openModel.getLogin());
		return user;
		
		
	}
	 
}
