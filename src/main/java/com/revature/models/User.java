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
@Table(name="users")
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
	@JoinColumn(name="roleId")
	private Role role;	
	
	@NotNull
	@ManyToMany(
			fetch = FetchType.EAGER,
			cascade = {
					CascadeType.MERGE
        })
	@JoinTable(
			name ="usersCohorts",
			joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "cohortId") }
			)
	private Set<Cohort> cohorts = new HashSet<>();
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
