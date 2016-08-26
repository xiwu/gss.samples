/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.apache.camel.component.salesforce.api.PicklistEnumConverter;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Salesforce DTO for SObject ConnectedApplication
 */
@XStreamAlias("ConnectedApplication")
public class ConnectedApplication extends AbstractSObjectBase {

    // OptionsAllowAdminApprovedUsersOnly
    private Boolean OptionsAllowAdminApprovedUsersOnly;

    @JsonProperty("OptionsAllowAdminApprovedUsersOnly")
    public Boolean getOptionsAllowAdminApprovedUsersOnly() {
        return this.OptionsAllowAdminApprovedUsersOnly;
    }

    @JsonProperty("OptionsAllowAdminApprovedUsersOnly")
    public void setOptionsAllowAdminApprovedUsersOnly(Boolean OptionsAllowAdminApprovedUsersOnly) {
        this.OptionsAllowAdminApprovedUsersOnly = OptionsAllowAdminApprovedUsersOnly;
    }

    // OptionsRefreshTokenValidityMetric
    private Boolean OptionsRefreshTokenValidityMetric;

    @JsonProperty("OptionsRefreshTokenValidityMetric")
    public Boolean getOptionsRefreshTokenValidityMetric() {
        return this.OptionsRefreshTokenValidityMetric;
    }

    @JsonProperty("OptionsRefreshTokenValidityMetric")
    public void setOptionsRefreshTokenValidityMetric(Boolean OptionsRefreshTokenValidityMetric) {
        this.OptionsRefreshTokenValidityMetric = OptionsRefreshTokenValidityMetric;
    }

    // OptionsHasSessionLevelPolicy
    private Boolean OptionsHasSessionLevelPolicy;

    @JsonProperty("OptionsHasSessionLevelPolicy")
    public Boolean getOptionsHasSessionLevelPolicy() {
        return this.OptionsHasSessionLevelPolicy;
    }

    @JsonProperty("OptionsHasSessionLevelPolicy")
    public void setOptionsHasSessionLevelPolicy(Boolean OptionsHasSessionLevelPolicy) {
        this.OptionsHasSessionLevelPolicy = OptionsHasSessionLevelPolicy;
    }

    // MobileSessionTimeout
    @XStreamConverter(PicklistEnumConverter.class)
    private MobileSessionTimeoutEnum MobileSessionTimeout;

    @JsonProperty("MobileSessionTimeout")
    public MobileSessionTimeoutEnum getMobileSessionTimeout() {
        return this.MobileSessionTimeout;
    }

    @JsonProperty("MobileSessionTimeout")
    public void setMobileSessionTimeout(MobileSessionTimeoutEnum MobileSessionTimeout) {
        this.MobileSessionTimeout = MobileSessionTimeout;
    }

    // PinLength
    @XStreamConverter(PicklistEnumConverter.class)
    private PinLengthEnum PinLength;

    @JsonProperty("PinLength")
    public PinLengthEnum getPinLength() {
        return this.PinLength;
    }

    @JsonProperty("PinLength")
    public void setPinLength(PinLengthEnum PinLength) {
        this.PinLength = PinLength;
    }

    // StartUrl
    private String StartUrl;

    @JsonProperty("StartUrl")
    public String getStartUrl() {
        return this.StartUrl;
    }

    @JsonProperty("StartUrl")
    public void setStartUrl(String StartUrl) {
        this.StartUrl = StartUrl;
    }

    // MobileStartUrl
    private String MobileStartUrl;

    @JsonProperty("MobileStartUrl")
    public String getMobileStartUrl() {
        return this.MobileStartUrl;
    }

    @JsonProperty("MobileStartUrl")
    public void setMobileStartUrl(String MobileStartUrl) {
        this.MobileStartUrl = MobileStartUrl;
    }

    // RefreshTokenValidityPeriod
    private Integer RefreshTokenValidityPeriod;

    @JsonProperty("RefreshTokenValidityPeriod")
    public Integer getRefreshTokenValidityPeriod() {
        return this.RefreshTokenValidityPeriod;
    }

    @JsonProperty("RefreshTokenValidityPeriod")
    public void setRefreshTokenValidityPeriod(Integer RefreshTokenValidityPeriod) {
        this.RefreshTokenValidityPeriod = RefreshTokenValidityPeriod;
    }

}
