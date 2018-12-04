package com.revature.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String email;

	@NotNull
	private String username;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "usersCohorts", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "cohortId") })
	private Set<Cohort> cohorts = new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Cohort> getCohorts() {
		return cohorts;
	}

	public void setCohorts(Set<Cohort> cohorts) {
		this.cohorts = cohorts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cohorts == null) ? 0 : cohorts.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (cohorts == null) {
			if (other.cohorts != null)
				return false;
		} else if (!cohorts.equals(other.cohorts))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", role=" + role + ", cohorts=" + cohorts + "]";
	}
	
	
	

}
