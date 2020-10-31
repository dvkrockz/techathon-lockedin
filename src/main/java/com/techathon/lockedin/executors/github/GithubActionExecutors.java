package com.techathon.lockedin.executors.github;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.models.UserDetails;

public abstract class GithubActionExecutors<T>  {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubActionExecutors.class);
	
	 public   ActionResponse<T> actionType;
	 
	public GithubActionExecutors() {
		// TODO Auto-generated constructor stub
	}

	public ActionResponse<T> performAction(UserDetails user,HttpServletRequest request){
		ActionResponse<T> actionResponse = getNewActionType();
		
		LOGGER.debug(actionResponse.toString());
	
	return actionResponse;
	}
	
	 public ActionResponse<T> getNewActionType() {
			this.actionType = new ActionResponse<T>();
			return actionType;
		}

	public abstract String checkUserExist();
	public abstract boolean saveNewUser();
	
	


}
