/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Salesforce DTO for SObject ThirdPartyAccountLink
 */
@XStreamAlias("ThirdPartyAccountLink")
public class ThirdPartyAccountLink extends AbstractSObjectBase {

    // ThirdPartyAccountLinkKey
    private String ThirdPartyAccountLinkKey;

    @JsonProperty("ThirdPartyAccountLinkKey")
    public String getThirdPartyAccountLinkKey() {
        return this.ThirdPartyAccountLinkKey;
    }

    @JsonProperty("ThirdPartyAccountLinkKey")
    public void setThirdPartyAccountLinkKey(String ThirdPartyAccountLinkKey) {
        this.ThirdPartyAccountLinkKey = ThirdPartyAccountLinkKey;
    }

    // UserId
    private String UserId;

    @JsonProperty("UserId")
    public String getUserId() {
        return this.UserId;
    }

    @JsonProperty("UserId")
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    // SsoProviderId
    private String SsoProviderId;

    @JsonProperty("SsoProviderId")
    public String getSsoProviderId() {
        return this.SsoProviderId;
    }

    @JsonProperty("SsoProviderId")
    public void setSsoProviderId(String SsoProviderId) {
        this.SsoProviderId = SsoProviderId;
    }

    // Handle
    private String Handle;

    @JsonProperty("Handle")
    public String getHandle() {
        return this.Handle;
    }

    @JsonProperty("Handle")
    public void setHandle(String Handle) {
        this.Handle = Handle;
    }

    // RemoteIdentifier
    private String RemoteIdentifier;

    @JsonProperty("RemoteIdentifier")
    public String getRemoteIdentifier() {
        return this.RemoteIdentifier;
    }

    @JsonProperty("RemoteIdentifier")
    public void setRemoteIdentifier(String RemoteIdentifier) {
        this.RemoteIdentifier = RemoteIdentifier;
    }

    // Provider
    private String Provider;

    @JsonProperty("Provider")
    public String getProvider() {
        return this.Provider;
    }

    @JsonProperty("Provider")
    public void setProvider(String Provider) {
        this.Provider = Provider;
    }

    // SsoProviderName
    private String SsoProviderName;

    @JsonProperty("SsoProviderName")
    public String getSsoProviderName() {
        return this.SsoProviderName;
    }

    @JsonProperty("SsoProviderName")
    public void setSsoProviderName(String SsoProviderName) {
        this.SsoProviderName = SsoProviderName;
    }

    // IsNotSsoUsable
    private Boolean IsNotSsoUsable;

    @JsonProperty("IsNotSsoUsable")
    public Boolean getIsNotSsoUsable() {
        return this.IsNotSsoUsable;
    }

    @JsonProperty("IsNotSsoUsable")
    public void setIsNotSsoUsable(Boolean IsNotSsoUsable) {
        this.IsNotSsoUsable = IsNotSsoUsable;
    }

}
