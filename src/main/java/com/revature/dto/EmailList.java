package com.revature.dto;

import java.util.Arrays;

public class EmailList {
	private String[] emails;

	public EmailList(String[] emails) {
		super();
		this.emails = emails;
	}

	public EmailList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(emails);
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
		EmailList other = (EmailList) obj;
		if (!Arrays.equals(emails, other.emails))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailList [emails=" + Arrays.toString(emails) + "]";
	}

	
	
}
