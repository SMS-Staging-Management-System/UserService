package com.revature.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.models.Cohort;
import com.revature.models.User;

public class CohortUserListInputDto {
	private String cohortName;
	private String cohortDescription;

	List<UserInputDto> userList;

	public CohortUserListInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CohortUserListInputDto(String cohortName, String cohortDescription, List<UserInputDto> userList) {
		super();
		this.cohortName = cohortName;
		this.cohortDescription = cohortDescription;
		this.userList = userList;
	}

	public String getCohortName() {
		return cohortName;
	}

	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}

	public String getCohortDescription() {
		return cohortDescription;
	}

	public void setCohortDescription(String cohortDescription) {
		this.cohortDescription = cohortDescription;
	}

	
	public List<UserInputDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInputDto> userList) {
		this.userList = userList;
	}

	public List<User> toUsersList(Cohort cohort) {
		List<User> users = new ArrayList<>();
		Set<Cohort> cohorts = new HashSet<>();
		cohorts.add(cohort);
		
		for (UserInputDto uiDto : this.userList) {
			users.add(new User(uiDto,cohorts));
		}
		return users;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cohortDescription == null) ? 0 : cohortDescription.hashCode());
		result = prime * result + ((cohortName == null) ? 0 : cohortName.hashCode());
		result = prime * result + ((userList == null) ? 0 : userList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CohortUserListInputDto other = (CohortUserListInputDto) obj;
		if (cohortDescription == null) {
			if (other.cohortDescription != null)
				return false;
		} else if (!cohortDescription.equals(other.cohortDescription))
			return false;
		if (cohortName == null) {
			if (other.cohortName != null)
				return false;
		} else if (!cohortName.equals(other.cohortName))
			return false;
		if (userList == null) {
			if (other.userList != null)
				return false;
		} else if (!userList.equals(other.userList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CohortUserList [cohortName=" + cohortName + ", cohortDescription=" + cohortDescription + ", userList="
				+ userList + "]";
	}

}
