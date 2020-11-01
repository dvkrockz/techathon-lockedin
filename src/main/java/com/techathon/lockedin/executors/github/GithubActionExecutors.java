package com.techathon.lockedin.executors.github;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;
import com.techathon.lockedin.users.UserRepository;

public abstract class GithubActionExecutors<T>  {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubActionExecutors.class);
	
	 public   ActionResponse<T> actionType;
	 public UserRepository userRepo;
	public GithubActionExecutors(UserRepository userRepo) {
		this.userRepo = userRepo;
		// TODO Auto-generated constructor stub
	}

	public ActionResponse<?> performAction(HttpServletRequest object, String jsonObject) {
		 	ActionResponse<T> actionResponse = getNewActionType();
		 	
		    LOGGER.debug(actionResponse.toString());
	
	return actionResponse;
	}
	
	 public ActionResponse<T> getNewActionType() {
			this.actionType = new ActionResponse<T>();
			return actionType;
		}

	public   UserDetails checkUserExist(String user) {
		return userRepo.findByUserEmailId(user).orElse(null);
	}
	
	public UserDetails saveNewUser(UserDetails user) {
		return userRepo.save(user);
	}
	
	
	


}
