package com.revature.models;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "Managers")
public class Managers {
	

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nonnull 
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "address")
	private Address trainingAddress;
	
	public Managers() {
	}
	
	public Managers(int id, String email, Address trainingAddress) {
		super();
		this.id = id;
		this.email = email;
		this.trainingAddress = trainingAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getTrainingAddress() {
		return trainingAddress;
	}

	public void setTrainingAddress(Address trainingAddress) {
		this.trainingAddress = trainingAddress;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((trainingAddress == null) ? 0 : trainingAddress.hashCode());
		return result;
	}

	

	@Override
	public String toString() {
		return "Managers [id=" + id + ", email=" + email + ", trainingAddress=" + trainingAddress + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Managers other = (Managers) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (trainingAddress == null) {
			if (other.trainingAddress != null)
				return false;
		} else if (!trainingAddress.equals(other.trainingAddress))
			return false;
		return true;
	}
}