package com.techathon.lockedin.executors.github;

import java.util.HashMap;
import java.util.Map;

public class GitHubExecutorFactory {

	private Map<String, GithubActionExecutors<?>> excelActionMap;
	
	private static GitHubExecutorFactory exeFactory;
	
	GitHubExecutorFactory(){
		excelActionMap = new HashMap<String, GithubActionExecutors<?>>();	
	}
	
	public void addAction(String name, GithubActionExecutors<?> executor) {
		if (!excelActionMap.containsKey(name)) {
			excelActionMap.put(name, executor);
		}
	}
	
	public GithubActionExecutors<?> getExecutor(String type) {
		GithubActionExecutors<?> executor = excelActionMap.get(type);
		return executor;
	}
	
	public static synchronized GitHubExecutorFactory getInstance() {
		if (exeFactory == null) {
			exeFactory = new GitHubExecutorFactory();
		}
		return exeFactory;
	}
	
	

}
