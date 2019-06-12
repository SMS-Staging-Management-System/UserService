package com.revature.models.dto;


 public class EmailSearch {

 	private String emailFragement;
 	private int page;
	public EmailSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailSearch(String emailFragement, int page) {
		super();
		this.emailFragement = emailFragement;
		this.page = page;
	}
	public String getEmailFragement() {
		return emailFragement;
	}
	public void setEmailFragement(String emailFragement) {
		this.emailFragement = emailFragement;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailFragement == null) ? 0 : emailFragement.hashCode());
		result = prime * result + page;
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
		EmailSearch other = (EmailSearch) obj;
		if (emailFragement == null) {
			if (other.emailFragement != null)
				return false;
		} else if (!emailFragement.equals(other.emailFragement))
			return false;
		if (page != other.page)
			return false;
		return true;
	}
 }
