package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "addresses")
public class Address {

	@Id
	@Column(name = "address_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	private String alias;
	private String street;
	private String zip;
	private String city;
	private String state;
	private String country;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int addressId, String alias, String street, String zip, String city, String state, String country) {
		super();
		this.addressId = addressId;
		this.alias = alias;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", alias=" + alias + ", street=" + street + ", zip=" + zip
				+ ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

}