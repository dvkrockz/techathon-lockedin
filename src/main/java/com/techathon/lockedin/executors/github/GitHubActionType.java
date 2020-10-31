package com.techathon.lockedin.executors.github;

public enum GitHubActionType {

	NEWPRREQUEST("opened"),
	PRCOMMENTREQUEST("edited"),
	PRDISMISSEDREQUEST("PR_DISMISSED"),
	PRSUBMITREQUEST("PR_SUBIMTTED"),
	PRAPPROVEREQUEST("PR_APPROVED"),
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
			return GitHubActionType.PRCOMMENTREQUEST;
		} else if ("submitted".equalsIgnoreCase(action)) {
			return GitHubActionType.PRCOMMENTREQUEST;
		} else if ("created".equalsIgnoreCase(action)) {
			return GitHubActionType.PRCOMMENTREQUEST;
		}else if ("PR_DISMISSED".equalsIgnoreCase(action)) {
			return GitHubActionType.PRDISMISSEDREQUEST;
		}else if ("PR_SUBIMTTED".equalsIgnoreCase(action)) {
			return GitHubActionType.PRSUBMITREQUEST;
		}else if ("PR_APPROVED".equalsIgnoreCase(action)) {
			return GitHubActionType.PRAPPROVEREQUEST;
		}else if ("closed".equalsIgnoreCase(action)) {
			return GitHubActionType.PRMERGEDANDCLOSED;
		}else {
			return null;
		}
	}
}
