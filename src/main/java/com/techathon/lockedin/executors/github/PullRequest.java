package com.techathon.lockedin.executors.github;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"url",
"id",
"node_id",
"html_url",
"diff_url",
"patch_url",
"issue_url",
"number",
"state",
"locked",
"title",
"user",
"body",
"created_at",
"updated_at",
"closed_at",
"merged_at",
"merge_commit_sha",
"assignee",
"assignees",
"requested_reviewers",
"requested_teams",
"labels",
"author_association",
"active_lock_reason",
"merged",
"mergeable",
"rebaseable",
"mergeable_state",
"merged_by",
"comments",
"review_comments",
"maintainer_can_modify",
"commits",
"additions",
"deletions",
"changed_files"
})
@Table(name="pull_request")
@Entity
public class PullRequest {

@JsonProperty("url")
private String url;

@JsonProperty("id")
@Id
@Column(name = "id")
private Integer id;
@JsonProperty("node_id")
private String nodeId;
@JsonProperty("html_url")
private String htmlUrl;
@JsonProperty("diff_url")
private String diffUrl;
@JsonProperty("patch_url")
private String patchUrl;
@JsonProperty("issue_url")
private String issueUrl;
@JsonProperty("number")
private Integer number;
@JsonProperty("state")
private String state;
@JsonProperty("locked")
private Boolean locked;
@JsonProperty("title")
private String title;
@JsonProperty("user")
@OneToOne
private User user;
@JsonProperty("body")
private String body;
@JsonProperty("created_at")
private String createdAt;
@JsonProperty("updated_at")
private String updatedAt;
@JsonProperty("closed_at")
private String closedAt;
@JsonProperty("merged_at")
private String mergedAt;
@JsonProperty("merge_commit_sha")
private String mergeCommitSha;
@JsonProperty("assignee")
private String assignee;
 
@JsonProperty("requested_reviewers")
@OneToMany
private List<RequestedReviewers> requestedReviewers = null;
 
@JsonProperty("author_association")
private String authorAssociation;
@JsonProperty("active_lock_reason")
private String activeLockReason;
@JsonProperty("merged")
private Boolean merged;
@JsonProperty("mergeable")
private Boolean mergeable;
@JsonProperty("rebaseable")
private String rebaseable;
@JsonProperty("mergeable_state")
private String mergeableState;
@JsonProperty("merged_by")
private String mergedBy;
@JsonProperty("comments")
private Integer comments;
@JsonProperty("review_comments")
private Integer reviewComments;
@JsonProperty("maintainer_can_modify")
private Boolean maintainerCanModify;
@JsonProperty("commits")
private Integer commits;
@JsonProperty("additions")
private Integer additions;
@JsonProperty("deletions")
private Integer deletions;
@JsonProperty("changed_files")
private Integer changedFiles;
 
@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("node_id")
public String getNodeId() {
return nodeId;
}

@JsonProperty("node_id")
public void setNodeId(String nodeId) {
this.nodeId = nodeId;
}

@JsonProperty("html_url")
public String getHtmlUrl() {
return htmlUrl;
}

@JsonProperty("html_url")
public void setHtmlUrl(String htmlUrl) {
this.htmlUrl = htmlUrl;
}

@JsonProperty("diff_url")
public String getDiffUrl() {
return diffUrl;
}

@JsonProperty("diff_url")
public void setDiffUrl(String diffUrl) {
this.diffUrl = diffUrl;
}

@JsonProperty("patch_url")
public String getPatchUrl() {
return patchUrl;
}

@JsonProperty("patch_url")
public void setPatchUrl(String patchUrl) {
this.patchUrl = patchUrl;
}

@JsonProperty("issue_url")
public String getIssueUrl() {
return issueUrl;
}

@JsonProperty("issue_url")
public void setIssueUrl(String issueUrl) {
this.issueUrl = issueUrl;
}

@JsonProperty("number")
public Integer getNumber() {
return number;
}

@JsonProperty("number")
public void setNumber(Integer number) {
this.number = number;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

@JsonProperty("locked")
public Boolean getLocked() {
return locked;
}

@JsonProperty("locked")
public void setLocked(Boolean locked) {
this.locked = locked;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("user")
public User getUser() {
return user;
}

@JsonProperty("user")
public void setUser(User user) {
this.user = user;
}

@JsonProperty("body")
public String getBody() {
return body;
}

@JsonProperty("body")
public void setBody(String body) {
this.body = body;
}

@JsonProperty("created_at")
public String getCreatedAt() {
return createdAt;
}

@JsonProperty("created_at")
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

@JsonProperty("updated_at")
public String getUpdatedAt() {
return updatedAt;
}

@JsonProperty("updated_at")
public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}
 
public String getClosedAt() {
	return closedAt;
}

public void setClosedAt(String closedAt) {
	this.closedAt = closedAt;
}

public String getMergedAt() {
	return mergedAt;
}

public void setMergedAt(String mergedAt) {
	this.mergedAt = mergedAt;
}

public String getMergeCommitSha() {
	return mergeCommitSha;
}

public void setMergeCommitSha(String mergeCommitSha) {
	this.mergeCommitSha = mergeCommitSha;
}

public String getAssignee() {
	return assignee;
}

public void setAssignee(String assignee) {
	this.assignee = assignee;
}

 
public List<RequestedReviewers> RequestedReviewers() {
	return requestedReviewers;
}

public void setRequestedReviewers(List<RequestedReviewers> requestedReviewers) {
	this.requestedReviewers = requestedReviewers;
}
 
@JsonProperty("author_association")
public String getAuthorAssociation() {
return authorAssociation;
}

@JsonProperty("author_association")
public void setAuthorAssociation(String authorAssociation) {
this.authorAssociation = authorAssociation;
}

@JsonProperty("active_lock_reason")
public Object getActiveLockReason() {
return activeLockReason;
}


public void setActiveLockReason(String activeLockReason) {
	this.activeLockReason = activeLockReason;
}

public void setMergeable(Boolean mergeable) {
	this.mergeable = mergeable;
}

public void setRebaseable(String rebaseable) {
	this.rebaseable = rebaseable;
}

public void setMergedBy(String mergedBy) {
	this.mergedBy = mergedBy;
}

@JsonProperty("merged")
public Boolean getMerged() {
return merged;
}

@JsonProperty("merged")
public void setMerged(Boolean merged) {
this.merged = merged;
}

@JsonProperty("mergeable")
public Object getMergeable() {
return mergeable;
}

 
@JsonProperty("rebaseable")
public Object getRebaseable() {
return rebaseable;
}

 
@JsonProperty("mergeable_state")
public String getMergeableState() {
return mergeableState;
}

@JsonProperty("mergeable_state")
public void setMergeableState(String mergeableState) {
this.mergeableState = mergeableState;
}

@JsonProperty("merged_by")
public Object getMergedBy() {
return mergedBy;
}

 
@JsonProperty("comments")
public Integer getComments() {
return comments;
}

@JsonProperty("comments")
public void setComments(Integer comments) {
this.comments = comments;
}

@JsonProperty("review_comments")
public Integer getReviewComments() {
return reviewComments;
}

@JsonProperty("review_comments")
public void setReviewComments(Integer reviewComments) {
this.reviewComments = reviewComments;
}

@JsonProperty("maintainer_can_modify")
public Boolean getMaintainerCanModify() {
return maintainerCanModify;
}

@JsonProperty("maintainer_can_modify")
public void setMaintainerCanModify(Boolean maintainerCanModify) {
this.maintainerCanModify = maintainerCanModify;
}

@JsonProperty("commits")
public Integer getCommits() {
return commits;
}

@JsonProperty("commits")
public void setCommits(Integer commits) {
this.commits = commits;
}

@JsonProperty("additions")
public Integer getAdditions() {
return additions;
}

@JsonProperty("additions")
public void setAdditions(Integer additions) {
this.additions = additions;
}

@JsonProperty("deletions")
public Integer getDeletions() {
return deletions;
}

@JsonProperty("deletions")
public void setDeletions(Integer deletions) {
this.deletions = deletions;
}

@JsonProperty("changed_files")
public Integer getChangedFiles() {
return changedFiles;
}

@JsonProperty("changed_files")
public void setChangedFiles(Integer changedFiles) {
this.changedFiles = changedFiles;
}

public List<RequestedReviewers> getRequestedReviewers() {
	return requestedReviewers;
}


 
}