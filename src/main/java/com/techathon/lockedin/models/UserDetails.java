package com.techathon.lockedin.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.techathon.lockedin.executors.github.PrOpenedModel;

@Table(name="user_details")
@Entity
public class UserDetails implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
	
	private String gitHubUserName;
	
	private String gitHubUrl;
	
	private String userEmailId;
	
	private Integer leaderBoardRank;
	
	private Integer totalReviewerPoints;
	
	private Integer totalDeveloperPoints;
	
	private String managerId;
	
	private String managerEmailId;
	
	private Boolean isAdmin;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<PrOpenedModel> prOpenModelList;
		
	@CreatedDate
	private Date userCreatedOn;

	public List<PrOpenedModel> getPrOpenModelList() {
		return prOpenModelList;
	}

	public void setPrOpenModelList(List<PrOpenedModel> prOpenModelList) {
		this.prOpenModelList = prOpenModelList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGitHubUserName() {
		return gitHubUserName;
	}

	public void setGitHubUserName(String gitHubUserName) {
		this.gitHubUserName = gitHubUserName;
	}

	public String getGitHubUrl() {
		return gitHubUrl;
	}

	public void setGitHubUrl(String gitHubUrl) {
		this.gitHubUrl = gitHubUrl;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public Integer getLeaderBoardRank() {
		return leaderBoardRank;
	}

	public void setLeaderBoardRank(Integer leaderBoardRank) {
		this.leaderBoardRank = leaderBoardRank;
	}

	public Integer getTotalReviewerPoints() {
		return totalReviewerPoints;
	}

	public void setTotalReviewerPoints(Integer totalReviewerPoints) {
		this.totalReviewerPoints = totalReviewerPoints;
	}

	public Integer getTotalDeveloperPoints() {
		return totalDeveloperPoints;
	}

	public void setTotalDeveloperPoints(Integer totalDeveloperPoints) {
		this.totalDeveloperPoints = totalDeveloperPoints;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerEmailId() {
		return managerEmailId;
	}

	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getUserCreatedOn() {
		return userCreatedOn;
	}

	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}
	
	
	
	
}
