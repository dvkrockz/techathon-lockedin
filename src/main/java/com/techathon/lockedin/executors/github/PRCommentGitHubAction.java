package com.techathon.lockedin.executors.github;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

public class PRCommentGitHubAction<T> extends GithubActionExecutors<T> {

 
private UserRepository userRepo;
	public PRCommentGitHubAction(UserRepository userRepo) {
		super(userRepo);
		this.userRepo = userRepo;
		// TODO Auto-generated constructor stub
	}

	
	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
		ActionResponse<T> actionResponse = getNewActionType();
		
	 	Gson gs = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
	 		    .create();
	 	PrOpenedModel classObject =  gs.fromJson(jsonObject, PrOpenedModel.class);
	 	 
		// Get UserfromDb from jsonObject login
	 	UserDetails userFromDb = checkUserExist(classObject.getPullRequest().getUser().getLogin());
		// Get PR url from Json and fetch The PROpenModelfromDB
	 	PrOpenedModel prOpenModelFromDb = null;
	 	if(userFromDb != null) {
	 	for(PrOpenedModel prDb: userFromDb.getPrOpenModelList()) {
	 	if(prDb.getPullRequest().getUrl().equalsIgnoreCase(classObject.getPullRequest().getUrl())) {
	 	//check if comments of review exist
	 		if(classObject.getReview() != null) {
	 			boolean isReviwerMatched = false;
	 			for(RequestedReviewers rev: prDb.getPullRequest().getRequestedReviewers()) {
	 				if(rev.getLogin().equalsIgnoreCase(classObject.getReview().getUser().getLogin())) {
	 					isReviwerMatched = true;
	 					GitHubComments reviewComments =  new GitHubComments();
	 					reviewComments.setNodeId(classObject.getReview().getNodeId());
	 					reviewComments.setBody(classObject.getReview().getBody());
	 					reviewComments.setCommitId(classObject.getReview().getCommitId());
	 					reviewComments.setPullRequestUrl(classObject.getReview().getPullRequestUrl());
	 					reviewComments.setHtmlUrl(classObject.getReview().getHtmlUrl());
	 					reviewComments.setAuthorAssociation(classObject.getReview().getAuthorAssociation());
	 					reviewComments.setCreatedAt(classObject.getReview().getSubmittedAt());
	 					reviewComments.setId(classObject.getReview().getId());
	 					rev.getComments().add(reviewComments);
	 					
	 				}else {
	 					//Add New Review and Review Comments
	 					
	 				}
	 			}
	 			if(isReviwerMatched) {
//	 				userRepo.saveAndFlush(userFromDb)
	 			UserDetails userSaved = 	saveNewUser(userFromDb);
	 			}
	 			
	 		}else if(classObject.getComment() != null) {
	 			boolean isCommentsAdded = false;
	 			for(RequestedReviewers rev: prDb.getPullRequest().getRequestedReviewers()) {
	 				if(rev.getLogin().equalsIgnoreCase(classObject.getComment().getUser().getLogin())) {
	 					isCommentsAdded = true;
	 					rev.getComments().add(classObject.getComment());
	 					
	 				}else {
	 					//Add New Review and Review Comments
	 					
	 				}
	 			}
	 			if(isCommentsAdded) {
	 			UserDetails userSaved = saveNewUser(userFromDb);
	 			}
	 			
	 		}
	 		
	 		
	 		
	 	}
	 	}
	 	}
		
		
		// Add Reviews to Pojo
			//in case of review object form a comment pojo and add
		
		return actionResponse;
	}
	 

}
