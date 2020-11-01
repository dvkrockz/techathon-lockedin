package com.techathon.lockedin.executors.github;

import java.util.ArrayList;
import java.util.Date;
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

public class NewPRGithubAction<T> extends GithubActionExecutors<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewPRGithubAction.class);
private UserRepository userRepo;
	public NewPRGithubAction(UserRepository userRepo) {
		super(userRepo);
		this.userRepo = userRepo;
		// TODO Auto-generated constructor stub
	}
	
	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
	 	ActionResponse<T> actionResponse = getNewActionType();
	 	Gson gs = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
	 		    .create();
	 	PrOpenedModel classObject = gs.fromJson(jsonObject, PrOpenedModel.class);
	 	 
	 	UserDetails user = fromModeltoUser(classObject);
	 	UserDetails userDetailsFromDb = checkUserExist(user.getGitHubUserName());
	 	
	 	
	 	if(null != userDetailsFromDb && !userDetailsFromDb.getPrOpenModelList().isEmpty()) {
	 		 LOGGER.info("User Exist with userName %s " , userDetailsFromDb.getGitHubUserName().toString());
	 		 // If User Existed Check if the PR Exist
	 		 PrOpenedModel existingPr =  null;
	 		 List<PrOpenedModel> newPrs = new ArrayList<PrOpenedModel>();
	 		 boolean newPrTestAdd = false;
	 		 for(PrOpenedModel prs : userDetailsFromDb.getPrOpenModelList()) {
	 			if(!prs.getPullRequest().getUrl().equalsIgnoreCase(classObject.getPullRequest().getUrl())) {
	 			  // Save that PR	
	 				//PR DoesNot Exist
	 		 		 //Save PR in the List
//	 				userDetailsFromDb.getPrOpenModelList().add(prs);
	 				newPrTestAdd= true;
	 				newPrs.add(prs);
//	 				userRepo.save(userDetailsFromDb);
	 			}else {
	 				 //if PR Exist 
 
		 		 	//Check the comments, title, body , or change in reviewers and save accordingly
	 				
	 				//Comparing Body
	 				if(prs.getPullRequest().getBody().equalsIgnoreCase(classObject.getPullRequest().getBody())) {
	 					prs.getPullRequest().setBody(classObject.getPullRequest().getBody());
	 					userRepo.save(userDetailsFromDb);
	 				}
	 		
	 				
	 			}
	 		 }
	 		 if(newPrTestAdd) {
	 			userDetailsFromDb.getPrOpenModelList().addAll(newPrs);
		 		userRepo.save(userDetailsFromDb);
	 		 }
	 		
	 	}else if(null != userDetailsFromDb && userDetailsFromDb.getPrOpenModelList().isEmpty()){
	 		
	 		 LOGGER.info("In NewPRGithHub Actio ElseIf ");
//	 		List<PrOpenedModel> prOpenModel = new ArrayList<>();
//	 		prOpenModel.add(classObject);
//	 		userDetailsFromDb.setPrOpenModelList(prOpenModel);
	 	 
	 		userDetailsFromDb = fromDBToUser(userDetailsFromDb, user);
	 		userRepo.save(userDetailsFromDb);
	 		if( classObject.getPullRequest().getRequestedReviewers().size() > 0) {
		 		for(RequestedReviewers rev: classObject.getPullRequest().getRequestedReviewers()) {
		 			UserDetails reviewerFromDb = checkUserExist(rev.getLogin());
		 			if(null == reviewerFromDb) {
		 				UserDetails reviewer = fromReviewertoUser(rev);
		 				saveNewUser(reviewer);
		 			}
		 		  }
		 		} 
	 	}else {
	 		 LOGGER.info("In NewPRGithHub Actio ELSE ");
	 		List<PrOpenedModel> prOpenModel = new ArrayList<>();
	 		prOpenModel.add(classObject);
	 		user.setPrOpenModelList(prOpenModel);
	 		user.setTotalDeveloperPoints(1000);
	 		user.setTotalReviewerPoints(0);
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
	
	
	
	public UserDetails fromDBToUser(UserDetails fromDb, UserDetails fromJson) {
        
        if(fromDb.getGitHubUrl() == null && fromJson.getGitHubUrl() != null )
        {
            fromDb.setGitHubUrl(fromJson.getGitHubUrl());
        }
        if(fromDb.getIsAdmin() == null && fromJson.getIsAdmin() != null )
        {
            fromDb.setIsAdmin(fromJson.getIsAdmin());
        }
        if(fromDb.getGitHubUserName() == null && fromJson.getGitHubUserName() != null )
        {
            fromDb.setGitHubUserName(fromJson.getGitHubUserName());
        }
        if(fromDb.getId() == null && fromJson.getId() != null )
        {
            fromDb.setId(fromJson.getId());
        }
        if(fromDb.getLeaderBoardRank() == null && fromJson.getLeaderBoardRank() != null )
        {
            fromDb.setLeaderBoardRank(fromJson.getLeaderBoardRank());
        }
        if(fromDb.getTotalDeveloperPoints() == null && fromJson.getTotalDeveloperPoints() != null )
        {
        	 
            fromDb.setTotalDeveloperPoints(fromJson.getTotalDeveloperPoints());
        }else {
        	
        	fromDb.setTotalDeveloperPoints(1000);
        }
        if(fromDb.getTotalReviewerPoints() == null && fromJson.getTotalReviewerPoints() != null )
        {
            fromDb.setTotalReviewerPoints(fromJson.getTotalReviewerPoints());
        }else {
        	fromDb.setTotalReviewerPoints(0);
        }
        if(fromDb.getUserCreatedOn() == null && fromJson.getUserCreatedOn() != null )
        {
            fromDb.setUserCreatedOn(fromJson.getUserCreatedOn());
        }
       if(fromDb.getUserEmailId() == null && fromJson.getUserEmailId() != null )
        {
            fromDb.setUserEmailId(fromJson.getUserEmailId());
        }
      
        return fromDb;
    }
 




 
	public UserDetails fromModeltoUser(PrOpenedModel openModel) {
		UserDetails user = new UserDetails();
		user.setGitHubUrl(openModel.getPullRequest().getUser().getHtmlUrl());
		user.setGitHubUserName(openModel.getPullRequest().getUser().getLogin());
user.setIsAdmin(false);
        
        // for the first time user
        user.setTotalDeveloperPoints(1000);
        user.setUserCreatedOn(new Date());
        
        // for the first time user
        user.setTotalReviewerPoints(0);
		return user; 	
	}

	
	public UserDetails fromReviewertoUser(RequestedReviewers openModel) {
		UserDetails user = new UserDetails();
		user.setGitHubUserName(openModel.getLogin());
        user.setIsAdmin(false);
        user.setUserCreatedOn(new Date());
        user.setTotalDeveloperPoints(1000);
 		user.setTotalReviewerPoints(0);
		return user;
		
		
	}
	

	 
}
