/*
 * Salesforce DTO generated by camel-salesforce-maven-plugin
 * Generated on: Mon Aug 15 13:54:43 CST 2016
 */
package org.apache.camel.salesforce.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.camel.component.salesforce.api.dto.AbstractSObjectBase;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Salesforce DTO for SObject EntityDefinition
 */
@XStreamAlias("EntityDefinition")
public class EntityDefinition extends AbstractSObjectBase {

    // DurableId
    private String DurableId;

    @JsonProperty("DurableId")
    public String getDurableId() {
        return this.DurableId;
    }

    @JsonProperty("DurableId")
    public void setDurableId(String DurableId) {
        this.DurableId = DurableId;
    }

    // QualifiedApiName
    private String QualifiedApiName;

    @JsonProperty("QualifiedApiName")
    public String getQualifiedApiName() {
        return this.QualifiedApiName;
    }

    @JsonProperty("QualifiedApiName")
    public void setQualifiedApiName(String QualifiedApiName) {
        this.QualifiedApiName = QualifiedApiName;
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

    // Label
    private String Label;

    @JsonProperty("Label")
    public String getLabel() {
        return this.Label;
    }

    @JsonProperty("Label")
    public void setLabel(String Label) {
        this.Label = Label;
    }

    // PluralLabel
    private String PluralLabel;

    @JsonProperty("PluralLabel")
    public String getPluralLabel() {
        return this.PluralLabel;
    }

    @JsonProperty("PluralLabel")
    public void setPluralLabel(String PluralLabel) {
        this.PluralLabel = PluralLabel;
    }

    // DefaultCompactLayoutId
    private String DefaultCompactLayoutId;

    @JsonProperty("DefaultCompactLayoutId")
    public String getDefaultCompactLayoutId() {
        return this.DefaultCompactLayoutId;
    }

    @JsonProperty("DefaultCompactLayoutId")
    public void setDefaultCompactLayoutId(String DefaultCompactLayoutId) {
        this.DefaultCompactLayoutId = DefaultCompactLayoutId;
    }

    // IsCustomizable
    private Boolean IsCustomizable;

    @JsonProperty("IsCustomizable")
    public Boolean getIsCustomizable() {
        return this.IsCustomizable;
    }

    @JsonProperty("IsCustomizable")
    public void setIsCustomizable(Boolean IsCustomizable) {
        this.IsCustomizable = IsCustomizable;
    }

    // IsApexTriggerable
    private Boolean IsApexTriggerable;

    @JsonProperty("IsApexTriggerable")
    public Boolean getIsApexTriggerable() {
        return this.IsApexTriggerable;
    }

    @JsonProperty("IsApexTriggerable")
    public void setIsApexTriggerable(Boolean IsApexTriggerable) {
        this.IsApexTriggerable = IsApexTriggerable;
    }

    // IsWorkflowEnabled
    private Boolean IsWorkflowEnabled;

    @JsonProperty("IsWorkflowEnabled")
    public Boolean getIsWorkflowEnabled() {
        return this.IsWorkflowEnabled;
    }

    @JsonProperty("IsWorkflowEnabled")
    public void setIsWorkflowEnabled(Boolean IsWorkflowEnabled) {
        this.IsWorkflowEnabled = IsWorkflowEnabled;
    }

    // IsCompactLayoutable
    private Boolean IsCompactLayoutable;

    @JsonProperty("IsCompactLayoutable")
    public Boolean getIsCompactLayoutable() {
        return this.IsCompactLayoutable;
    }

    @JsonProperty("IsCompactLayoutable")
    public void setIsCompactLayoutable(Boolean IsCompactLayoutable) {
        this.IsCompactLayoutable = IsCompactLayoutable;
    }

    // KeyPrefix
    private String KeyPrefix;

    @JsonProperty("KeyPrefix")
    public String getKeyPrefix() {
        return this.KeyPrefix;
    }

    @JsonProperty("KeyPrefix")
    public void setKeyPrefix(String KeyPrefix) {
        this.KeyPrefix = KeyPrefix;
    }

    // IsDeletable
    private Boolean IsDeletable;

    @JsonProperty("IsDeletable")
    public Boolean getIsDeletable() {
        return this.IsDeletable;
    }

    @JsonProperty("IsDeletable")
    public void setIsDeletable(Boolean IsDeletable) {
        this.IsDeletable = IsDeletable;
    }

    // IsCreatable
    private Boolean IsCreatable;

    @JsonProperty("IsCreatable")
    public Boolean getIsCreatable() {
        return this.IsCreatable;
    }

    @JsonProperty("IsCreatable")
    public void setIsCreatable(Boolean IsCreatable) {
        this.IsCreatable = IsCreatable;
    }

    // IsQueryable
    private Boolean IsQueryable;

    @JsonProperty("IsQueryable")
    public Boolean getIsQueryable() {
        return this.IsQueryable;
    }

    @JsonProperty("IsQueryable")
    public void setIsQueryable(Boolean IsQueryable) {
        this.IsQueryable = IsQueryable;
    }

}
