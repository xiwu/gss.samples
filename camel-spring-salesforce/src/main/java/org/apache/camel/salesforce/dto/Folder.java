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
 * Salesforce DTO for SObject Folder
 */
@XStreamAlias("Folder")
public class Folder extends AbstractSObjectBase {

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

    // AccessType
    @XStreamConverter(PicklistEnumConverter.class)
    private AccessTypeEnum AccessType;

    @JsonProperty("AccessType")
    public AccessTypeEnum getAccessType() {
        return this.AccessType;
    }

    @JsonProperty("AccessType")
    public void setAccessType(AccessTypeEnum AccessType) {
        this.AccessType = AccessType;
    }

    // IsReadonly
    private Boolean IsReadonly;

    @JsonProperty("IsReadonly")
    public Boolean getIsReadonly() {
        return this.IsReadonly;
    }

    @JsonProperty("IsReadonly")
    public void setIsReadonly(Boolean IsReadonly) {
        this.IsReadonly = IsReadonly;
    }

    // Type
    @XStreamConverter(PicklistEnumConverter.class)
    private TypeEnum Type;

    @JsonProperty("Type")
    public TypeEnum getType() {
        return this.Type;
    }

    @JsonProperty("Type")
    public void setType(TypeEnum Type) {
        this.Type = Type;
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

}
