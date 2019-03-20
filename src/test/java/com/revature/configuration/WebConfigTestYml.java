package com.revature.configuration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;




public class WebConfigTestYml {
	
	@JsonProperty
	private List<String> origins;
	
	@JsonProperty
	private List<String> headers;
	
	@JsonProperty
	private List<String> methods;
	
	
	private boolean credentials = true;

	public List<String> getOrigins() {
		return origins;
	}

	public void setOrigins(List<String> origins) {
		this.origins = origins;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	public boolean isCredentials() {
		return credentials;
	}

	public void setCredentials(boolean credentials) {
		this.credentials = credentials;
	}

	
	public WebConfigTestYml() {
		super();
	}
	
	public WebConfigTestYml(List<String> origins, List<String> headers, List<String> methods, boolean credentials) {
		super();
		this.origins = origins;
		this.headers = headers;
		this.methods = methods;
		this.credentials = credentials;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (credentials ? 1231 : 1237);
		result = prime * result + ((headers == null) ? 0 : headers.hashCode());
		result = prime * result + ((methods == null) ? 0 : methods.hashCode());
		result = prime * result + ((origins == null) ? 0 : origins.hashCode());
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
		WebConfigTestYml other = (WebConfigTestYml) obj;
		if (credentials != other.credentials)
			return false;
		if (headers == null) {
			if (other.headers != null)
				return false;
		} else if (!headers.equals(other.headers))
			return false;
		if (methods == null) {
			if (other.methods != null)
				return false;
		} else if (!methods.equals(other.methods))
			return false;
		if (origins == null) {
			if (other.origins != null)
				return false;
		} else if (!origins.equals(other.origins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebConfigTestYml [origins=" + origins + ", headers=" + headers + ", methods=" + methods
				+ ", credentials=" + credentials + "]";
	}
	
	
}
