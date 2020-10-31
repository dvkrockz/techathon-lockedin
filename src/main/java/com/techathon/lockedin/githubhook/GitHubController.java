package com.techathon.lockedin.githubhook;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.executors.github.GitHubActionType;
import com.techathon.lockedin.executors.github.GitHubExecutorFactory;
import com.techathon.lockedin.executors.github.GithubActionExecutors;
import com.techathon.lockedin.executors.github.NewPRGithubAction;
import com.techathon.lockedin.executors.github.PRApproveGitHubAction;
import com.techathon.lockedin.executors.github.PRCommentGitHubAction;
import com.techathon.lockedin.executors.github.PRDismissedGitHubAction;
import com.techathon.lockedin.executors.github.PRSubmitGitHubAction;

@RestController
@RequestMapping(value = "githubwebhook")
public class GitHubController {
	private final static Logger logger = LoggerFactory.getLogger(GitHubController.class);
	
	private GitHubExecutorFactory gitHubExecutor;
	
	@PostConstruct
	public void init() {
		gitHubExecutor = GitHubExecutorFactory.getInstance();
    
		gitHubExecutor.addAction(GitHubActionType.NEWPRREQUEST.name(),new NewPRGithubAction<Object>());
		gitHubExecutor.addAction(GitHubActionType.PRAPPROVEREQUEST.name(),new PRApproveGitHubAction<Object>());
		gitHubExecutor.addAction(GitHubActionType.PRCOMMENTREQUEST.name(),new PRCommentGitHubAction<Object>());
		gitHubExecutor.addAction(GitHubActionType.PRDISMISSEDREQUEST.name(),new PRDismissedGitHubAction<Object>());
		gitHubExecutor.addAction(GitHubActionType.PRSUBMITREQUEST.name(),new PRSubmitGitHubAction<Object>());
	}
	
	@GetMapping("getOk")
	public String getData(){
		return "ok";
		}
	
	@PostMapping(value="postgithubdata")
	public String gitHubWebHook(@RequestBody String jsonObject,HttpServletRequest request, HttpServletResponse response) throws ParseException {
		logger.info("Recived GitHubWeb for , %d", jsonObject);
		JSONParser parser = new JSONParser(jsonObject); 
		String dt= (String) parser.parseObject().get("action");
		
		GithubActionExecutors<?> githubaction = null;
		ActionResponse<?> actionResponse  = null;
		try {
		githubaction = gitHubExecutor.getExecutor(GitHubActionType.getAction(parser.parseObject().get("action").toString()).toString());
		actionResponse = githubaction.performAction(user, request);
		}
		catch (Exception e) {
			logger.error("Error in Performing Github Action");
		}
		
		
		return dt;
	}
	
}
