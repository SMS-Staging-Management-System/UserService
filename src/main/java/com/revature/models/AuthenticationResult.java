
package com.revature.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AccessToken",
    "ExpiresIn",
    "IdToken",
    "RefreshToken",
    "TokenType"
})
public class AuthenticationResult {

    @JsonProperty("AccessToken")
    private String accessToken;
    @JsonProperty("ExpiresIn")
    private Integer expiresIn;
    @JsonProperty("IdToken")
    private String idToken;
    @JsonProperty("RefreshToken")
    private String refreshToken;
    @JsonProperty("TokenType")
    private String tokenType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AuthenticationResult() {
    }

    /**
     * 
     * @param tokenType
     * @param accessToken
     * @param expiresIn
     * @param refreshToken
     * @param idToken
     */
    public AuthenticationResult(String accessToken, Integer expiresIn, String idToken, String refreshToken, String tokenType) {
        super();
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    @JsonProperty("AccessToken")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("AccessToken")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("ExpiresIn")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("ExpiresIn")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonProperty("IdToken")
    public String getIdToken() {
        return idToken;
    }

    @JsonProperty("IdToken")
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @JsonProperty("RefreshToken")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonProperty("RefreshToken")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonProperty("TokenType")
    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("TokenType")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("accessToken", accessToken).append("expiresIn", expiresIn).append("idToken", idToken).append("refreshToken", refreshToken).append("tokenType", tokenType).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tokenType).append(accessToken).append(additionalProperties).append(expiresIn).append(refreshToken).append(idToken).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AuthenticationResult) == false) {
            return false;
        }
        AuthenticationResult rhs = ((AuthenticationResult) other);
        return new EqualsBuilder().append(tokenType, rhs.tokenType).append(accessToken, rhs.accessToken).append(additionalProperties, rhs.additionalProperties).append(expiresIn, rhs.expiresIn).append(refreshToken, rhs.refreshToken).append(idToken, rhs.idToken).isEquals();
    }

}
