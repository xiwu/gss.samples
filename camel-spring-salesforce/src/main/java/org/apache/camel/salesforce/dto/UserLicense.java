/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Salesforce DTO for SObject UserLicense
 */
@XStreamAlias("UserLicense")
public class UserLicense extends AbstractSObjectBase {

    // LicenseDefinitionKey
    private String LicenseDefinitionKey;

    @JsonProperty("LicenseDefinitionKey")
    public String getLicenseDefinitionKey() {
        return this.LicenseDefinitionKey;
    }

    @JsonProperty("LicenseDefinitionKey")
    public void setLicenseDefinitionKey(String LicenseDefinitionKey) {
        this.LicenseDefinitionKey = LicenseDefinitionKey;
    }

    // MonthlyLoginsUsed
    private Integer MonthlyLoginsUsed;

    @JsonProperty("MonthlyLoginsUsed")
    public Integer getMonthlyLoginsUsed() {
        return this.MonthlyLoginsUsed;
    }

    @JsonProperty("MonthlyLoginsUsed")
    public void setMonthlyLoginsUsed(Integer MonthlyLoginsUsed) {
        this.MonthlyLoginsUsed = MonthlyLoginsUsed;
    }

    // MonthlyLoginsEntitlement
    private Integer MonthlyLoginsEntitlement;

    @JsonProperty("MonthlyLoginsEntitlement")
    public Integer getMonthlyLoginsEntitlement() {
        return this.MonthlyLoginsEntitlement;
    }

    @JsonProperty("MonthlyLoginsEntitlement")
    public void setMonthlyLoginsEntitlement(Integer MonthlyLoginsEntitlement) {
        this.MonthlyLoginsEntitlement = MonthlyLoginsEntitlement;
    }

}
