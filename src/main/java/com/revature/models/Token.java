
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
    "AuthenticationResult",
    "ChallengeParameters"
})
public class Token {

    @JsonProperty("AuthenticationResult")
    private AuthenticationResult authenticationResult;
    @JsonProperty("ChallengeParameters")
    private ChallengeParameters challengeParameters;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Token() {
    }

    /**
     * 
     * @param authenticationResult
     * @param challengeParameters
     */
    public Token(AuthenticationResult authenticationResult, ChallengeParameters challengeParameters) {
        super();
        this.authenticationResult = authenticationResult;
        this.challengeParameters = challengeParameters;
    }

    @JsonProperty("AuthenticationResult")
    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    @JsonProperty("AuthenticationResult")
    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }

    @JsonProperty("ChallengeParameters")
    public ChallengeParameters getChallengeParameters() {
        return challengeParameters;
    }

    @JsonProperty("ChallengeParameters")
    public void setChallengeParameters(ChallengeParameters challengeParameters) {
        this.challengeParameters = challengeParameters;
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
        return new ToStringBuilder(this).append("authenticationResult", authenticationResult).append("challengeParameters", challengeParameters).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(authenticationResult).append(challengeParameters).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Token) == false) {
            return false;
        }
        Token rhs = ((Token) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(authenticationResult, rhs.authenticationResult).append(challengeParameters, rhs.challengeParameters).isEquals();
    }

}
