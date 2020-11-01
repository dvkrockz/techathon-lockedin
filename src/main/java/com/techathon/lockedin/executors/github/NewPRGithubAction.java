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
		Gson gs = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		PrOpenedModel classObject = gs.fromJson(jsonObject, PrOpenedModel.class);
		UserDetails user = fromModeltoUser(classObject);
		UserDetails userDetailsFromDb = checkUserExist(user.getGitHubUserName());

		if (userDetailsFromDb == null) {
			// Condition when Author is totally new

			LOGGER.info("In NewPRGithHub Action  Condition when Author is totally new");
			List<PrOpenedModel> prOpenModel = new ArrayList<>();
			prOpenModel.add(classObject);
			user.setPrOpenModelList(prOpenModel);
			userDetailsFromDb = saveNewUser(user);

			// Check if there are Any reviewers
			// If exist Check for there User Details and Save
			if (classObject.getPullRequest().getRequestedReviewers().size() > 0) {
				for (RequestedReviewers rev : classObject.getPullRequest().getRequestedReviewers()) {
					UserDetails reviewerFromDb = checkUserExist(rev.getLogin());
					if (null == reviewerFromDb) {
						UserDetails reviewer = fromReviewertoUser(rev);
						saveNewUser(reviewer);
					}
				}
			}

		} else if (userDetailsFromDb != null) {
			LOGGER.info("In NewPRGithHub Action Condition When Author was just a Reviewer	");
			// Condition When Author was just a Reviewer
			if (userDetailsFromDb.getGitHubUrl() == null && userDetailsFromDb.getPrOpenModelList().isEmpty()) {
				List<PrOpenedModel> prOpenModel = new ArrayList<>();
				prOpenModel.add(classObject);
				// Check if PR Exist then ADD
				userDetailsFromDb.setPrOpenModelList(prOpenModel);
				userDetailsFromDb.setGitHubUrl(classObject.getPullRequest().getUser().getHtmlUrl());
				userDetailsFromDb = saveNewUser(userDetailsFromDb);
				// Check if Reviwer Exit and Add
				if (classObject.getPullRequest().getRequestedReviewers().size() > 0) {
					for (RequestedReviewers rev : classObject.getPullRequest().getRequestedReviewers()) {
						UserDetails reviewerFromDb = checkUserExist(rev.getLogin());
						if (null == reviewerFromDb) {
							UserDetails reviewer = fromReviewertoUser(rev);
							saveNewUser(reviewer);
						}
					}
				}

			} else {
//	 			Condition when Author has already raise a PR Earlier
				LOGGER.info("In NewPRGithHub Action Condition when Author has already raise a PR Earlier");
				userDetailsFromDb.getPrOpenModelList().add(classObject);
				userDetailsFromDb = saveNewUser(userDetailsFromDb);
				if (classObject.getPullRequest().getRequestedReviewers().size() > 0) {
					for (RequestedReviewers rev : classObject.getPullRequest().getRequestedReviewers()) {
						UserDetails reviewerFromDb = checkUserExist(rev.getLogin());
						if (null == reviewerFromDb) {
							UserDetails reviewer = fromReviewertoUser(rev);
							saveNewUser(reviewer);
						}
					}
				}

			}

		}

		LOGGER.debug(actionResponse.toString());
		actionResponse.setPrPullNumber(classObject.getPullRequest().getHtmlUrl());
		return actionResponse;
	}

	public UserDetails fromDBToUser(UserDetails fromDb, UserDetails fromJson) {

		if (fromDb.getGitHubUrl() == null && fromJson.getGitHubUrl() != null) {
			fromDb.setGitHubUrl(fromJson.getGitHubUrl());
		}
		if (fromDb.getIsAdmin() == null && fromJson.getIsAdmin() != null) {
			fromDb.setIsAdmin(fromJson.getIsAdmin());
		}
		if (fromDb.getGitHubUserName() == null && fromJson.getGitHubUserName() != null) {
			fromDb.setGitHubUserName(fromJson.getGitHubUserName());
		}
		if (fromDb.getId() == null && fromJson.getId() != null) {
			fromDb.setId(fromJson.getId());
		}
		if (fromDb.getLeaderBoardRank() == null && fromJson.getLeaderBoardRank() != null) {
			fromDb.setLeaderBoardRank(fromJson.getLeaderBoardRank());
		}
		if (fromDb.getTotalDeveloperPoints() == null && fromJson.getTotalDeveloperPoints() != null) {

			fromDb.setTotalDeveloperPoints(fromJson.getTotalDeveloperPoints());
		} else {

			fromDb.setTotalDeveloperPoints(1000);
		}
		if (fromDb.getTotalReviewerPoints() == null && fromJson.getTotalReviewerPoints() != null) {
			fromDb.setTotalReviewerPoints(fromJson.getTotalReviewerPoints());
		} else {
			fromDb.setTotalReviewerPoints(0);
		}
		if (fromDb.getUserCreatedOn() == null && fromJson.getUserCreatedOn() != null) {
			fromDb.setUserCreatedOn(fromJson.getUserCreatedOn());
		}
		if (fromDb.getUserEmailId() == null && fromJson.getUserEmailId() != null) {
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
