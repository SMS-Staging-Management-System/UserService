package com.revature.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.models.Cohort;
import com.revature.models.User;

public class UserListInputDto {
	List<UserInputDto> userList = new ArrayList<>();

	public List<User> toUsersList(Cohort cohort) {
		List<User> users = new ArrayList<>();
		Set<Cohort> cohorts = new HashSet<>();
		cohorts.add(cohort);

		for (UserInputDto uiDto : this.userList) {
			users.add(new User(uiDto, cohorts));
		}
		return users;
	}

	public UserListInputDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserListInputDto(List<UserInputDto> userList) {
		super();
		this.userList = userList;
	}

	public List<UserInputDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInputDto> userList) {
		this.userList = userList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		UserListInputDto other = (UserListInputDto) obj;
		if (userList == null) {
			if (other.userList != null)
				return false;
		} else if (!userList.equals(other.userList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserListInputDto [userList=" + userList + "]";
	}

}
