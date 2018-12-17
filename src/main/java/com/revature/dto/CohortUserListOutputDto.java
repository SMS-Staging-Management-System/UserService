package com.revature.dto;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Cohort;
import com.revature.models.User;

//DTOOutput
// CohortEmailDTO:
// String Messages:
// Successful
// Failed {number} Users
// Cohort could not be created
// Cohort cohort:
// List<Users> acceptedUsers:
// LIst<Users> rejectedUsers:

public class CohortUserListOutputDto {
	String messages;
	Cohort cohort;
	List<User> acceptedUsers = new ArrayList<>();
	List<User> rejectedUsers = new ArrayList<>();;

	public CohortUserListOutputDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CohortUserListOutputDto(String messages, Cohort cohort, List<User> acceptedUsers, List<User> rejectedUsers) {
		super();
		this.messages = messages;
		this.cohort = cohort;
		this.acceptedUsers = acceptedUsers;
		this.rejectedUsers = rejectedUsers;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Cohort getCohort() {
		return cohort;
	}

	public void setCohort(Cohort cohort) {
		this.cohort = cohort;
	}

	public List<User> getAcceptedUsers() {
		return acceptedUsers;
	}

	public void setAcceptedUsers(List<User> acceptedUsers) {
		this.acceptedUsers = acceptedUsers;
	}

	public List<User> getRejectedUsers() {
		return rejectedUsers;
	}

	public void setRejectedUsers(List<User> rejectedUsers) {
		this.rejectedUsers = rejectedUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptedUsers == null) ? 0 : acceptedUsers.hashCode());
		result = prime * result + ((cohort == null) ? 0 : cohort.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((rejectedUsers == null) ? 0 : rejectedUsers.hashCode());
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
		CohortUserListOutputDto other = (CohortUserListOutputDto) obj;
		if (acceptedUsers == null) {
			if (other.acceptedUsers != null)
				return false;
		} else if (!acceptedUsers.equals(other.acceptedUsers))
			return false;
		if (cohort == null) {
			if (other.cohort != null)
				return false;
		} else if (!cohort.equals(other.cohort))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (rejectedUsers == null) {
			if (other.rejectedUsers != null)
				return false;
		} else if (!rejectedUsers.equals(other.rejectedUsers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CohortUserListOutput [messages=" + messages + ", cohort=" + cohort + ", acceptedUsers=" + acceptedUsers
				+ ", rejectedUsers=" + rejectedUsers + "]";
	}

}
