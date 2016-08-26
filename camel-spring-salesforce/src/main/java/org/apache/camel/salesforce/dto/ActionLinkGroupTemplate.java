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
 * Salesforce DTO for SObject ActionLinkGroupTemplate
 */
@XStreamAlias("ActionLinkGroupTemplate")
public class ActionLinkGroupTemplate extends AbstractSObjectBase {

    // DeveloperName
    private String DeveloperName;

    @JsonProperty("DeveloperName")
    public String getDeveloperName() {
        return this.DeveloperName;
    }

    @JsonProperty("DeveloperName")
    public void setDeveloperName(String DeveloperName) {
        this.DeveloperName = DeveloperName;
    }

    // Language
    @XStreamConverter(PicklistEnumConverter.class)
    private LanguageEnum Language;

    @JsonProperty("Language")
    public LanguageEnum getLanguage() {
        return this.Language;
    }

    @JsonProperty("Language")
    public void setLanguage(LanguageEnum Language) {
        this.Language = Language;
    }

    // MasterLabel
    private String MasterLabel;

    @JsonProperty("MasterLabel")
    public String getMasterLabel() {
        return this.MasterLabel;
    }

    @JsonProperty("MasterLabel")
    public void setMasterLabel(String MasterLabel) {
        this.MasterLabel = MasterLabel;
    }

    // NamespacePrefix
    private String NamespacePrefix;

    @JsonProperty("NamespacePrefix")
    public String getNamespacePrefix() {
        return this.NamespacePrefix;
    }

    @JsonProperty("NamespacePrefix")
    public void setNamespacePrefix(String NamespacePrefix) {
        this.NamespacePrefix = NamespacePrefix;
    }

    // ExecutionsAllowed
    @XStreamConverter(PicklistEnumConverter.class)
    private ExecutionsAllowedEnum ExecutionsAllowed;

    @JsonProperty("ExecutionsAllowed")
    public ExecutionsAllowedEnum getExecutionsAllowed() {
        return this.ExecutionsAllowed;
    }

    @JsonProperty("ExecutionsAllowed")
    public void setExecutionsAllowed(ExecutionsAllowedEnum ExecutionsAllowed) {
        this.ExecutionsAllowed = ExecutionsAllowed;
    }

    // HoursUntilExpiration
    private Integer HoursUntilExpiration;

    @JsonProperty("HoursUntilExpiration")
    public Integer getHoursUntilExpiration() {
        return this.HoursUntilExpiration;
    }

    @JsonProperty("HoursUntilExpiration")
    public void setHoursUntilExpiration(Integer HoursUntilExpiration) {
        this.HoursUntilExpiration = HoursUntilExpiration;
    }

    // Category
    @XStreamConverter(PicklistEnumConverter.class)
    private CategoryEnum Category;

    @JsonProperty("Category")
    public CategoryEnum getCategory() {
        return this.Category;
    }

    @JsonProperty("Category")
    public void setCategory(CategoryEnum Category) {
        this.Category = Category;
    }

    // IsPublished
    private Boolean IsPublished;

    @JsonProperty("IsPublished")
    public Boolean getIsPublished() {
        return this.IsPublished;
    }

    @JsonProperty("IsPublished")
    public void setIsPublished(Boolean IsPublished) {
        this.IsPublished = IsPublished;
    }

}
