package com.techathon.lockedin.executors.github;

public enum GitHubActionType {

	NEWPRREQUEST("opened"),
	EDITPRREQUEST("edited"),
	PRMERGEDANDCLOSED("closed");

	
	/**
	 *
	 * 
	 * Name of the action
	 */
	private String action;

	/**
	 * constructor
	 * 
	 * @param action
	 *            name of the action
	 */
	private GitHubActionType(String action) {
		this.action = action;
	}
	
	public static GitHubActionType getAction(String action) {
		if ("opened".equalsIgnoreCase(action)) {
			return GitHubActionType.NEWPRREQUEST;
			
		} else if ("edited".equalsIgnoreCase(action)) {
			return GitHubActionType.EDITPRREQUEST;
		} else if ("closed".equalsIgnoreCase(action)) {
			return GitHubActionType.PRMERGEDANDCLOSED;
		}else {
			return null;
		}
	}
}
