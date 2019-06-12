package com.revature.models.dto;

 import java.util.List;

 public class EmailListNoPage {

 	private List<String> emailList;

	public EmailListNoPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailListNoPage(List<String> emailList) {
		super();
		this.emailList = emailList;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailList == null) ? 0 : emailList.hashCode());
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
		EmailListNoPage other = (EmailListNoPage) obj;
		if (emailList == null) {
			if (other.emailList != null)
				return false;
		} else if (!emailList.equals(other.emailList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailListNoPage [emailList=" + emailList + "]";
	}
	
	
 	
}
