package com.techathon.lockedin.githubhook;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.techathon.lockedin.executors.ActionResponse;
import com.techathon.lockedin.executors.github.GitHubActionType;
import com.techathon.lockedin.executors.github.GitHubExecutorFactory;
import com.techathon.lockedin.executors.github.GithubActionExecutors;
import com.techathon.lockedin.executors.github.NewPRGithubAction;
import com.techathon.lockedin.executors.github.PRCommentGitHubAction;
import com.techathon.lockedin.executors.github.PRSubmitGitHubAction;
import com.techathon.lockedin.executors.github.PrOpenedModel;
import com.techathon.lockedin.users.UserRepository;

@RestController
@RequestMapping(value = "githubwebhook")
public class GitHubController {
	private final static Logger logger = LoggerFactory.getLogger(GitHubController.class);
	
	private GitHubExecutorFactory gitHubExecutor;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostConstruct
	public void init() {
		gitHubExecutor = GitHubExecutorFactory.getInstance();
		gitHubExecutor.addAction(GitHubActionType.NEWPRREQUEST.name(),new NewPRGithubAction<PrOpenedModel>(userRepo));
		gitHubExecutor.addAction(GitHubActionType.EDITPRREQUEST.name(),new PRCommentGitHubAction<Object>(userRepo));
		gitHubExecutor.addAction(GitHubActionType.PRMERGEDANDCLOSED.name(),new PRSubmitGitHubAction<Object>(userRepo));
		}
	
	@GetMapping("getOk")
	public String getData(){
		return "ok";
		}
	
	@PostMapping(value="postgithubdata")
	public void gitHubWebHook(@RequestBody String jsonObject,HttpServletRequest request, HttpServletResponse response) throws ParseException {
		logger.info("Recived GitHubWeb for , %d", jsonObject);
		JSONParser parser = new JSONParser(jsonObject); 
		String dt= (String) parser.parseObject().get("action");
		logger.info(dt);
		GithubActionExecutors<?> githubaction = null;
		ActionResponse<?> actionResponse  = null;
		if(null != dt && !dt.isEmpty()) {
			try {
			githubaction = gitHubExecutor.getExecutor(GitHubActionType.getAction(dt).toString());
			actionResponse = githubaction.performAction(request ,jsonObject);
			}
			catch (Exception e) {
				logger.error("Error in Performing Github Action",e);
			}
		} 
  
	}
	
	
}
