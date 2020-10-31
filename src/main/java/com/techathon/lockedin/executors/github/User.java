package com.techathon.lockedin.executors.github;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"login",
"id",
"node_id",
"avatar_url",
"gravatar_id",
"url",
"html_url",
 "type",
"site_admin"
})
@Table(name="github_user")
@Entity
public class User {

@JsonProperty("login")
private String login;
@Id
@Column(name = "id")
@JsonProperty("id")
private Integer id;
@JsonProperty("node_id")
private String nodeId;
@JsonProperty("avatar_url")
private String avatarUrl;
@JsonProperty("gravatar_id")
private String gravatarId;
@JsonProperty("url")
private String url;
@JsonProperty("html_url")
private String htmlUrl;
 
@JsonProperty("type")
private String type;
@JsonProperty("site_admin")
private Boolean siteAdmin;
 
@JsonProperty("login")
public String getLogin() {
return login;
}

@JsonProperty("login")
public void setLogin(String login) {
this.login = login;
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

@JsonProperty("avatar_url")
public String getAvatarUrl() {
return avatarUrl;
}

@JsonProperty("avatar_url")
public void setAvatarUrl(String avatarUrl) {
this.avatarUrl = avatarUrl;
}

@JsonProperty("gravatar_id")
public String getGravatarId() {
return gravatarId;
}

@JsonProperty("gravatar_id")
public void setGravatarId(String gravatarId) {
this.gravatarId = gravatarId;
}

@JsonProperty("url")
public String getUrl() {
return url;
}

@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("html_url")
public String getHtmlUrl() {
return htmlUrl;
}

@JsonProperty("html_url")
public void setHtmlUrl(String htmlUrl) {
this.htmlUrl = htmlUrl;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("site_admin")
public Boolean getSiteAdmin() {
return siteAdmin;
}

@JsonProperty("site_admin")
public void setSiteAdmin(Boolean siteAdmin) {
this.siteAdmin = siteAdmin;
}
 

}
