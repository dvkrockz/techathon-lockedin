package com.techathon.lockedin.executors.github;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

public class PRSubmitGitHubAction<T> extends GithubActionExecutors<T> {

	 
private UserRepository userRep;
	public PRSubmitGitHubAction(UserRepository userRepo) {
		super(userRepo);
this.userRep = userRepo;
}
	public static final String regex = "[x]"; 
	
	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
		
	
	 	ActionResponse<T> actionResponse = getNewActionType();
	 	Gson gs = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
	 		    .create();
	 	PrOpenedModel classObject = gs.fromJson(jsonObject, PrOpenedModel.class);
	 	Integer pointsToBeDeducted = 0;
	 	//Get User
	 	 	UserDetails authorFromDb = checkUserExist(classObject.getPullRequest().getUser().getLogin());
	 			//Get PR
	 	 	PrOpenedModel prOpenModelFromDb = null;
	 	 	if(authorFromDb != null) {
	 		 	for(PrOpenedModel prDb: authorFromDb.getPrOpenModelList()) {
	 		 	if(prDb.getPullRequest().getUrl().equalsIgnoreCase(classObject.getPullRequest().getUrl())) {
	 		 	// get Reviewer List 
						// for each Reviewer
	 		 		for(RequestedReviewers rev: prDb.getPullRequest().getRequestedReviewers()) {
	 		 		//If comment exist
	 		 			if(rev.getComments() != null && !rev.getComments().isEmpty()) {
	 		 			// for each comments 
	 		 				Integer points = 0;
	 		 				for(GitHubComments comment: rev.getComments() ) {
	 		 				// compare with hardcoded comments in For Loop
	 		 					 points = points + compareComments(comment);
	 		 				}
	 		 			//Calculate total deduct point from Developer -- save transeintly
	 		 				pointsToBeDeducted = pointsToBeDeducted+points;
	 		 			// get reviewer userObject
	 		 				UserDetails reviewUser = checkUserExist(rev.getLogin());
	 		 				Integer totDev = reviewUser.getTotalReviewerPoints() != null ? reviewUser.getTotalReviewerPoints() : 0;
	 		 				Integer tot = 	reviewUser.getTotalReviewerPoints() + points;
	 		 				reviewUser.setTotalReviewerPoints(tot);
							//update reviewer points
	 		 				saveNewUser(reviewUser);

	 		 			}else {
	 		 			//else
	 						// Get reviewer userObject
	 						//Deduct point from reviewer
	 						//Update Reviewer
	 						//Add point to user   -- save transeintly
	 		 				UserDetails reviewUserNoT = checkUserExist(rev.getLogin());
	 		 				Integer totDev = reviewUserNoT.getTotalReviewerPoints() != null ? reviewUserNoT.getTotalReviewerPoints() : 0;
	 		 				Integer tot = 	reviewUserNoT.getTotalReviewerPoints() - 5;
	 		 				pointsToBeDeducted = pointsToBeDeducted + 5;
	 		 				reviewUserNoT.setTotalReviewerPoints(tot);
	 		 				saveNewUser(reviewUserNoT);
	 		 			}	 		 		
	 		 		}
	 		 		}
								

			//End for each reviewer
	//get total addaed or deducted author points
	 		 	Integer totDevpoints = authorFromDb.getTotalDeveloperPoints() != null ?  authorFromDb.getTotalDeveloperPoints() : 0;
	 		 	Integer totAdded = totDevpoints - pointsToBeDeducted;
	 		 	authorFromDb.setTotalDeveloperPoints(totAdded);
	 		 	saveNewUser(authorFromDb);
	//update userTable
	 		 	}

	 	
	}
	
	 	 	return actionResponse;
	}
	
	 




	private Integer compareComments(GitHubComments comment) {
		Pattern pattern = Pattern.compile(regex);
		Matcher countOverlappingEmailsMatcher = pattern.matcher(comment.getBody());
			int count = 0;
			while (countOverlappingEmailsMatcher.find()) {
				//NLP Service   //Return which category does the comment fall into weigth // Accuracy thresold 80< 
				
			    count++;
			}
		return count;
	}
}