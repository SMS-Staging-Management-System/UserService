package com.revature.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="cohorts")
public class Cohort {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cohortId;
	
	@NotNull
	private String cohortName;
	
	@NotNull
	private String cohortDescription;

	@JsonIgnore
	@ManyToMany(
			fetch = FetchType.LAZY,
        	cascade = {
        			CascadeType.MERGE
        }, mappedBy = "cohorts")
	private Set<User> users = new HashSet<>();

	public Cohort() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cohort(int cohortId, @NotNull String cohortName, @NotNull String cohortDescription, Set<User> users) {
		super();
		this.cohortId = cohortId;
		this.cohortName = cohortName;
		this.cohortDescription = cohortDescription;
		this.users = users;
	}

	public int getCohortId() {
		return cohortId;
	}

	public void setCohortId(int cohortId) {
		this.cohortId = cohortId;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cohortDescription == null) ? 0 : cohortDescription.hashCode());
		result = prime * result + cohortId;
		result = prime * result + ((cohortName == null) ? 0 : cohortName.hashCode());
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
		Cohort other = (Cohort) obj;
		if (cohortDescription == null) {
			if (other.cohortDescription != null)
				return false;
		} else if (!cohortDescription.equals(other.cohortDescription))
			return false;
		if (cohortId != other.cohortId)
			return false;
		if (cohortName == null) {
			if (other.cohortName != null)
				return false;
		} else if (!cohortName.equals(other.cohortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cohort [cohortId=" + cohortId + ", cohortName=" + cohortName + ", cohortDescription="
				+ cohortDescription + "]";
	}

	
	
	
}
