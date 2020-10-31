package com.techathon.lockedin.executors.github;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"action",
"number",
"pull_request"
})
@Table(name = "pr_opened_model")
@Entity
public class PrOpenedModel {

@JsonProperty("action")
private String action;
@JsonProperty("number")
private Integer number;

@Id
@Column(name = "id")
private Integer id;

@JsonProperty("pull_request")
@ManyToOne
@JoinColumn(name="pull_request",referencedColumnName="nodeId",insertable=false,updatable=false)
private PullRequest pullRequest;

 
@JsonProperty("action")
public String getAction() {
return action;
}

@JsonProperty("action")
public void setAction(String action) {
this.action = action;
}

@JsonProperty("number")
public Integer getNumber() {
return number;
}

@JsonProperty("number")
public void setNumber(Integer number) {
this.number = number;
}

@JsonProperty("pull_request")
public PullRequest getPullRequest() {
return pullRequest;
}

@JsonProperty("pull_request")
public void setPullRequest(PullRequest pullRequest) {
this.pullRequest = pullRequest;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

 

}