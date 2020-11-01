package com.techathon.lockedin.executors.github;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"node_id",
"user",
"body",
"commit_id",
"submitted_at",
"state",
"html_url",
"pull_request_url",
"author_association",
"_links"
})
public class GitHubReviewComments implements Serializable
{

@JsonProperty("id")
private Integer id;
@JsonProperty("node_id")
private String nodeId;
@JsonProperty("user")
private User user;
@JsonProperty("body")
private String body;
@JsonProperty("commit_id")
private String commitId;
@JsonProperty("submitted_at")
private String submittedAt;
@JsonProperty("state")
private String state;
@JsonProperty("html_url")
private String htmlUrl;
@JsonProperty("pull_request_url")
private String pullRequestUrl;
@JsonProperty("author_association")
private String authorAssociation;


private final static long serialVersionUID = 2729822541390151518L;

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

@JsonProperty("commit_id")
public String getCommitId() {
return commitId;
}

@JsonProperty("commit_id")
public void setCommitId(String commitId) {
this.commitId = commitId;
}

@JsonProperty("submitted_at")
public String getSubmittedAt() {
return submittedAt;
}

@JsonProperty("submitted_at")
public void setSubmittedAt(String submittedAt) {
this.submittedAt = submittedAt;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

@JsonProperty("html_url")
public String getHtmlUrl() {
return htmlUrl;
}

@JsonProperty("html_url")
public void setHtmlUrl(String htmlUrl) {
this.htmlUrl = htmlUrl;
}

@JsonProperty("pull_request_url")
public String getPullRequestUrl() {
return pullRequestUrl;
}

@JsonProperty("pull_request_url")
public void setPullRequestUrl(String pullRequestUrl) {
this.pullRequestUrl = pullRequestUrl;
}

@JsonProperty("author_association")
public String getAuthorAssociation() {
return authorAssociation;
}

@JsonProperty("author_association")
public void setAuthorAssociation(String authorAssociation) {
this.authorAssociation = authorAssociation;
}

 
}
